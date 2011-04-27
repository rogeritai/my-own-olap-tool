/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bi.olapql.language.measure.translator;

import br.com.bi.olapql.language.measure.Aggregation;
import br.com.bi.olapql.language.measure.Column;
import br.com.bi.olapql.language.measure.Measure;
import br.com.bi.olapql.language.measure.MeasureParser;
import br.com.bi.olapql.language.measure.ParseException;
import br.com.bi.olapql.language.measure.SimpleNode;
import br.com.bi.olapql.language.utils.TranslationUtils;
import br.com.bi.model.Application;
import br.com.bi.model.entity.metadata.Cube;
import br.com.bi.olapql.language.query.translator.QuerySqlTranslator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Stack;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author luiz
 */
public class MeasureSqlTranslator extends AbstractMeasureParserVisitor {

    private Stack<String> filterStack = new Stack<String>();
    private Cube cube;

    public MeasureSqlTranslator(Cube cube) {
        this.cube = cube;
    }

    @Override
    public void visit(br.com.bi.olapql.language.measure.Addition node, StringBuilder data) {
        visitOperation(node, data);
    }

    @Override
    public void visit(br.com.bi.olapql.language.measure.Multiplication node, StringBuilder data) {
        visitOperation(node, data);
    }

    @Override
    public void visit(Measure node, StringBuilder data) {
        try {
            br.com.bi.model.entity.metadata.Measure measure =
                    Application.getMeasureDao().findByName(TranslationUtils.extractName(node.jjtGetValue().toString()));

            if (StringUtils.isNotBlank(measure.getFilterExpression())) {
                filterStack.push(measure.getFilterExpression());
            }

            MeasureParser parser = new MeasureParser(IOUtils.toInputStream(measure.getExpression()));

            visit(parser.measureExpression(), data);

            if (StringUtils.isNotBlank(measure.getFilterExpression())) {
                filterStack.pop();
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void visit(Aggregation node, StringBuilder data) {
        if (node.jjtGetValue().equals("quantidade")) {
            data.append("count(");
        }
        if (node.jjtGetValue().equals("média")) {
            data.append("avg(");
        }
        if (node.jjtGetValue().equals("máximo")) {
            data.append("max(");
        }
        if (node.jjtGetValue().equals("mínimo")) {
            data.append("min(");
        }
        if (node.jjtGetValue().equals("soma")) {
            data.append("sum(");
        }

        if (!filterStack.empty()) {
            data.append("case when (");

            QuerySqlTranslator translator = new QuerySqlTranslator();
            data.append(translator.translateFilterExpression(filterStack.peek()));

            data.append(") then ");
            visitChildren(node, data);
            data.append(" else null end");
        } else {
            visitChildren(node, data);
        }

        data.append(")");
    }

    @Override
    public void visit(Column node, StringBuilder data) {
        data.append(cube.getTableName()).append(".").append(TranslationUtils.extractColumn(node.jjtGetValue().toString()));
    }

    @Override
    public void visit(br.com.bi.olapql.language.measure.Number node, StringBuilder data) {
        data.append(node.jjtGetValue());
    }

    private void visitOperation(SimpleNode node, StringBuilder data) {
        visitOperation(node, node.jjtGetValue().toString(), data);
    }

    private void visitOperation(SimpleNode node, String op, StringBuilder data) {
        data.append("(");

        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            visit(node.jjtGetChild(i), data);
            if (i < node.jjtGetNumChildren() - 1) {
                data.append(" ").append(op).append(" ");
            }
        }

        data.append(")");
    }

    public String translate(String expression) {
        InputStream in = new ByteArrayInputStream(
                (expression).getBytes());

        MeasureParser parser = new MeasureParser(in);

        try {
            SimpleNode node = parser.measureExpression();

            StringBuilder sb = new StringBuilder();

            MeasureSqlTranslator translator = new MeasureSqlTranslator(this.cube);
            translator.visit(node, sb);

            return sb.toString();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
