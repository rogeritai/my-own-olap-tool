/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bi.language.translator;

import br.com.bi.language.filter.Addition;
import br.com.bi.language.filter.ArithmeticExpression;
import br.com.bi.language.filter.Comparison;
import br.com.bi.language.filter.Conjunction;
import br.com.bi.language.filter.Date;
import br.com.bi.language.filter.Disjunction;
import br.com.bi.language.filter.Filter;
import br.com.bi.language.filter.FilterExpression;
import br.com.bi.language.filter.FilterParser;
import br.com.bi.language.filter.FilterParserTreeConstants;
import br.com.bi.language.filter.FilterParserVisitor;
import br.com.bi.language.filter.LevelOrMeasure;
import br.com.bi.language.filter.Measure;
import br.com.bi.language.filter.Multiplication;
import br.com.bi.language.filter.Negation;
import br.com.bi.language.filter.Node;
import br.com.bi.language.filter.Number;
import br.com.bi.language.filter.ParseException;
import br.com.bi.language.filter.Property;
import br.com.bi.language.filter.RelationalOperator;
import br.com.bi.language.filter.SimpleNode;
import br.com.bi.language.filter.StringLiteral;
import br.com.bi.model.Application;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author luiz
 */
public class FilterSqlTranslator implements FilterParserVisitor {

    public static void main(String args[]) {
        String olapql = "[Total] > 100 ou [Recursos Humanos] e [Produto].[Código] = 3";

        InputStream in = new ByteArrayInputStream(
                (olapql).getBytes());

        FilterParser parser = new FilterParser(in);

        try {
            SimpleNode node = parser.filterExpression();

            node.dump(" ");

            StringBuilder sb = new StringBuilder();

            FilterSqlTranslator translator = new FilterSqlTranslator();
            translator.visit(node, sb);

            System.out.println("OLAPQL = " + olapql);
            System.out.println("SQL = " + sb.toString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

    public void visit(Node node, StringBuilder data) {
        if (node instanceof FilterExpression) {
            visit((FilterExpression) node, data);
        }

        if (node instanceof Measure) {
            visit((Measure) node, data);
        }

        if (node instanceof Filter) {
            visit((Filter) node, data);
        }

        if (node instanceof Disjunction) {
            visit((Disjunction) node, data);
        }

        if (node instanceof Conjunction) {
            visit((Conjunction) node, data);
        }

        if (node instanceof Negation) {
            visit((Negation) node, data);
        }

        if (node instanceof Comparison) {
            visit((Comparison) node, data);
        }

        if (node instanceof LevelOrMeasure) {
            visit((LevelOrMeasure) node, data);
        }

        if (node instanceof Property) {
            visit((Property) node, data);
        }

        if (node instanceof RelationalOperator) {
            visit((RelationalOperator) node, data);
        }

        if (node instanceof ArithmeticExpression) {
            visit((ArithmeticExpression) node, data);
        }

        if (node instanceof Date) {
            visit((Date) node, data);
        }

        if (node instanceof StringLiteral) {
            visit((StringLiteral) node, data);
        }

        if (node instanceof Addition) {
            visit((Addition) node, data);
        }

        if (node instanceof Multiplication) {
            visit((Multiplication) node, data);
        }

        if (node instanceof Number) {
            visit((Number) node, data);
        }
    }

    public void visit(SimpleNode node, StringBuilder data) {
        visit((Node) node, data);
    }

    public void visit(Filter node, StringBuilder data) {
        try {
            br.com.bi.model.entity.metadata.Filter filter =
                    Application.getFilterDao().findByName(extractName(node.jjtGetValue().toString()));

            InputStream in = new ByteArrayInputStream(
                    (filter.getExpression()).getBytes());

            FilterParser parser = new FilterParser(in);

            visit(parser.filterExpression(), data);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public void visit(Disjunction node, StringBuilder data) {
        visitOperation(node, "or", data);
    }

    public void visit(Conjunction node, StringBuilder data) {
        visitOperation(node, "and", data);
    }

    public void visit(Negation node, StringBuilder data) {
        if (data.charAt(data.length() - 1) != ' ') {
            data.append(" not");
        } else {
            data.append("not ");
        }

        visit(node.jjtGetChild(0), data);
    }

    public void visit(Comparison node, StringBuilder data) {
        visitChildren(node, data);
    }

    public void visit(Property node, StringBuilder data) {
        String[] str = node.jjtGetValue().toString().split("\\.");

        br.com.bi.model.entity.metadata.Level level =
                Application.getLevelDao().findByName(extractName(str[0]));

        br.com.bi.model.entity.metadata.Property property = level.getProperty(extractName(str[1]));

        data.append(level.getSchemaName()).append(".").
                append(level.getTableName()).append(".").
                append(property.getColumnName());
    }

    public void visit(RelationalOperator node, StringBuilder data) {
        data.append(" ").append(node.jjtGetValue()).append(" ");
    }

    public void visit(ArithmeticExpression node, StringBuilder data) {
        visitChildren(node, data);
    }

    public void visit(Date node, StringBuilder data) {
        data.append(node.jjtGetValue());
    }

    public void visit(StringLiteral node, StringBuilder data) {
        data.append(node.jjtGetValue());
    }

    public void visit(Addition node, StringBuilder data) {
        visitOperation(node, data);
    }

    public void visit(Multiplication node, StringBuilder data) {
        visitOperation(node, data);
    }

    public void visit(Number node, StringBuilder data) {
        data.append(node.jjtGetValue());
    }

    public void visit(LevelOrMeasure node, StringBuilder data) {
        br.com.bi.model.entity.metadata.Measure measure =
                Application.getMeasureDao().findByName(extractName(node.jjtGetValue().toString()));

        if (measure != null) {
            Measure m = new Measure(FilterParserTreeConstants.JJTMEASURE);
            m.jjtSetValue(node.jjtGetValue());

            visit(m, data);
        } else {
            br.com.bi.model.entity.metadata.Level level =
                    Application.getLevelDao().findByName(extractName(node.jjtGetValue().toString()));

            data.append(level.getSchemaName()).append(".").
                    append(level.getTableName()).append(".").
                    append(level.getCodeProperty().getColumnName());
        }
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

    private void visitChildren(SimpleNode node, StringBuilder data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            visit(node.jjtGetChild(i), data);
        }
    }

    public void visit(FilterExpression node, StringBuilder data) {
        visitChildren(node, data);
    }

    public void visit(Measure node, StringBuilder data) {
        MeasureSqlTranslator translator = new MeasureSqlTranslator();

        data.append(translator.translate(node.jjtGetValue().toString()));
    }

    private String extractName(String expression) {
        String patternStr = "\\[(.*)\\]";

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
