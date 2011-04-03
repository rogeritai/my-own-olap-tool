/* Generated By:JJTree&JavaCC: Do not edit this line. QueryParser.java */
package br.com.bi.language.query;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class QueryParser/*@bgen(jjtree)*/implements QueryParserTreeConstants, QueryParserConstants {/*@bgen(jjtree)*/
  protected JJTQueryParserState jjtree = new JJTQueryParserState();private boolean rows = false;
    private boolean columns = false;

    public static void main(String args[]) {
        InputStream in = new ByteArrayInputStream(
("selecione {[teste], ([teste], [teste])} nas colunas, [teste] nas linhas do cubo [teste] onde [teste] e n\u00e3o [teste] > ((-1 / (100.8 + 2)) * [teste])").getBytes());

        QueryParser parser = new QueryParser(in);

        try {
            SimpleNode node = parser.instruction();
            node.dump(" ");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void fixChildren(Node node) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtSetParent(node);

            fixChildren(node.jjtGetChild(i));
        }
    }

  final public SimpleNode instruction() throws ParseException {
 /*@bgen(jjtree) Instruction */
  Instruction jjtn000 = new Instruction(JJTINSTRUCTION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      select();
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public void select() throws ParseException {
 /*@bgen(jjtree) Select */
  Select jjtn000 = new Select(JJTSELECT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(SELECT);
      axis();
      jj_consume_token(31);
      axis();
      jj_consume_token(FROM);
      jj_consume_token(CUBE);
      cube();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WHERE:
        jj_consume_token(WHERE);
        filterExpression();
        break;
      default:
        jj_la1[0] = jj_gen;
        ;
      }
      jj_consume_token(0);
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void axis() throws ParseException {
 /*@bgen(jjtree) Axis */
    Axis jjtn000 = new Axis(JJTAXIS);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);java.util.List<SimpleNode> nodes = new java.util.ArrayList<SimpleNode>();
    String axis;
    try {
      set(nodes);
      jj_consume_token(ON);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ROWS:
        axis = rows();
        break;
      case COLUMNS:
        axis = columns();
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        // desconsidera os children adicionados pelo jjtree através do método closeNodeScope
        jjtn000.children = null;

        for (SimpleNode node: nodes) {
            jjtn000.jjtAddChild(node, jjtn000.jjtGetNumChildren());
            node.jjtSetParent(jjtn000);
        }

        fixChildren(jjtn000);

        jjtn000.value = axis;
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public String rows() throws ParseException {
        if (rows)
            {if (true) throw new ParseException("O eixo das linhas foi duplicado");}
        else
            rows = true;
    jj_consume_token(ROWS);
        {if (true) return "ROWS";}
    throw new Error("Missing return statement in function");
  }

  final public String columns() throws ParseException {
        if (columns)
            {if (true) throw new ParseException("O eixo das colunas foi duplicado");}
        else
            columns = true;
    jj_consume_token(COLUMNS);
        {if (true) return "COLUMNS";}
    throw new Error("Missing return statement in function");
  }

  final public void set(java.util.List<SimpleNode> nodes) throws ParseException {
    SimpleNode node = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METADATA_OBJECT_NAME:
    case 34:
      node = node();
                        nodes.add(node);
      break;
    case 32:
      if (jj_2_1(3)) {
        jj_consume_token(32);
        node = node();
                                             nodes.add(node);
        label_1:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 31:
            ;
            break;
          default:
            jj_la1[2] = jj_gen;
            break label_1;
          }
          jj_consume_token(31);
          set(nodes);
        }
        jj_consume_token(33);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 32:
          jj_consume_token(32);
          set(nodes);
          jj_consume_token(33);
          break;
        default:
          jj_la1[3] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public SimpleNode node() throws ParseException {
    SimpleNode node;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METADATA_OBJECT_NAME:
      node = metadataObject();
      break;
    case 34:
      node = crossJoin();
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
        {if (true) return node;}
    throw new Error("Missing return statement in function");
  }

  final public SimpleNode metadataObject() throws ParseException {
    SimpleNode node = null;
    if (jj_2_2(2)) {
      node = propertyNode();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case METADATA_OBJECT_NAME:
        node = levelOrMeasureOrFilter();
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
        {if (true) return node;}
    throw new Error("Missing return statement in function");
  }

  final public SimpleNode levelOrMeasureOrFilter() throws ParseException {
 /*@bgen(jjtree) LevelOrMeasureOrFilter */
    LevelOrMeasureOrFilter jjtn000 = new LevelOrMeasureOrFilter(JJTLEVELORMEASUREORFILTER);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(METADATA_OBJECT_NAME);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.value = t.image;
        {if (true) return jjtn000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public SimpleNode propertyNode() throws ParseException {
 /*@bgen(jjtree) PropertyNode */
    PropertyNode jjtn000 = new PropertyNode(JJTPROPERTYNODE);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    String property;
    try {
      t = jj_consume_token(METADATA_OBJECT_NAME);
        property = t.image;
      jj_consume_token(DOT);
      t = jj_consume_token(METADATA_OBJECT_NAME);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        property += "." + t.image;

        jjtn000.value = property;

        {if (true) return jjtn000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public SimpleNode crossJoin() throws ParseException {
    SimpleNode firstArgument;
    java.util.List<SimpleNode> secondArgument = new java.util.ArrayList<SimpleNode>();
    jj_consume_token(34);
    firstArgument = metadataObject();
    jj_consume_token(31);
    set(secondArgument);
    jj_consume_token(35);
        for (SimpleNode node: secondArgument) {
            firstArgument.jjtAddChild(node, firstArgument.jjtGetNumChildren());
            node.jjtSetParent(firstArgument);
        }

        {if (true) return firstArgument;}
    throw new Error("Missing return statement in function");
  }

  final public void cube() throws ParseException {
 /*@bgen(jjtree) Cube */
    Cube jjtn000 = new Cube(JJTCUBE);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(METADATA_OBJECT_NAME);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.value = t.image;
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void filterExpression() throws ParseException {
 /*@bgen(jjtree) FilterExpression */
  FilterExpression jjtn000 = new FilterExpression(JJTFILTEREXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      disjunction();
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void disjunction() throws ParseException {
 /*@bgen(jjtree) #Disjunction(> 1) */
  Disjunction jjtn000 = new Disjunction(JJTDISJUNCTION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      conjunction();
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case OR:
          ;
          break;
        default:
          jj_la1[7] = jj_gen;
          break label_2;
        }
        jj_consume_token(OR);
        conjunction();
      }
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
      }
    }
  }

  final public void conjunction() throws ParseException {
 /*@bgen(jjtree) #Conjunction(> 1) */
  Conjunction jjtn000 = new Conjunction(JJTCONJUNCTION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      term();
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case AND:
          ;
          break;
        default:
          jj_la1[8] = jj_gen;
          break label_3;
        }
        jj_consume_token(AND);
        term();
      }
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
      }
    }
  }

  final public void term() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METADATA_OBJECT_NAME:
    case 34:
      atom();
      break;
    case NOT:
      negation();
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void atom() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METADATA_OBJECT_NAME:
      if (jj_2_3(2)) {
        comparison();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case METADATA_OBJECT_NAME:
          filter();
          break;
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
    case 34:
      jj_consume_token(34);
      filterExpression();
      jj_consume_token(35);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void negation() throws ParseException {
 /*@bgen(jjtree) Negation */
  Negation jjtn000 = new Negation(JJTNEGATION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(NOT);
      term();
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void comparison() throws ParseException {
 /*@bgen(jjtree) Comparison */
  Comparison jjtn000 = new Comparison(JJTCOMPARISON);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      if (jj_2_4(2)) {
        level();
        relationalOperator();
        operating();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case METADATA_OBJECT_NAME:
          property();
          relationalOperator();
          operating();
          break;
        default:
          jj_la1[12] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void level() throws ParseException {
 /*@bgen(jjtree) Level */
    Level jjtn000 = new Level(JJTLEVEL);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(METADATA_OBJECT_NAME);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.value = t.image;
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void filter() throws ParseException {
 /*@bgen(jjtree) Filter */
    Filter jjtn000 = new Filter(JJTFILTER);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(METADATA_OBJECT_NAME);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.value = t.image;
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public SimpleNode property() throws ParseException {
 /*@bgen(jjtree) Property */
    Property jjtn000 = new Property(JJTPROPERTY);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    String property;
    try {
      t = jj_consume_token(METADATA_OBJECT_NAME);
        property = t.image;
      jj_consume_token(DOT);
      t = jj_consume_token(METADATA_OBJECT_NAME);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        property += "." + t.image;

        jjtn000.value = property;

        {if (true) return jjtn000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public void relationalOperator() throws ParseException {
 /*@bgen(jjtree) RelationalOperator */
    RelationalOperator jjtn000 = new RelationalOperator(JJTRELATIONALOPERATOR);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(RELATIONAL_OPERATOR);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.value = t.image;
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void operating() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DATE:
      date();
      break;
    case STRING_LITERAL:
      stringLiteral();
      break;
    case MINUS:
    case METADATA_OBJECT_NAME:
    case FLOATING_POINT_LITERAL:
    case INTEGER_LITERAL:
    case 34:
                                 ArithmeticExpression jjtn001 = new ArithmeticExpression(JJTARITHMETICEXPRESSION);
                                 boolean jjtc001 = true;
                                 jjtree.openNodeScope(jjtn001);
      try {
        arithmeticExpression();
      } catch (Throwable jjte001) {
                                 if (jjtc001) {
                                   jjtree.clearNodeScope(jjtn001);
                                   jjtc001 = false;
                                 } else {
                                   jjtree.popNode();
                                 }
                                 if (jjte001 instanceof RuntimeException) {
                                   {if (true) throw (RuntimeException)jjte001;}
                                 }
                                 if (jjte001 instanceof ParseException) {
                                   {if (true) throw (ParseException)jjte001;}
                                 }
                                 {if (true) throw (Error)jjte001;}
      } finally {
                                 if (jjtc001) {
                                   jjtree.closeNodeScope(jjtn001, true);
                                 }
      }
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void date() throws ParseException {
 /*@bgen(jjtree) Date */
    Date jjtn000 = new Date(JJTDATE);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(DATE);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.value = t.image;
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void stringLiteral() throws ParseException {
 /*@bgen(jjtree) StringLiteral */
    StringLiteral jjtn000 = new StringLiteral(JJTSTRINGLITERAL);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(STRING_LITERAL);
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.value = t.image;
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void arithmeticExpression() throws ParseException {
    additiveExpression();
  }

  final public void additiveExpression() throws ParseException {
 /*@bgen(jjtree) #Addition(> 1) */
    Addition jjtn000 = new Addition(JJTADDITION);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String op = null;
    try {
      multiplicativeExpression();
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MINUS:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_4;
        }
        op = additiveOperator();
        multiplicativeExpression();
      }
      jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
      jjtc000 = false;
        jjtn000.value = op;
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
      }
    }
  }

  final public String additiveOperator() throws ParseException {
    Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      t = jj_consume_token(PLUS);
      break;
    case MINUS:
      t = jj_consume_token(MINUS);
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
        {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public void multiplicativeExpression() throws ParseException {
 /*@bgen(jjtree) #Multiplication(> 1) */
    Multiplication jjtn000 = new Multiplication(JJTMULTIPLICATION);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String op = null;
    try {
      arithmeticAtom();
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MULT:
        case DIV:
          ;
          break;
        default:
          jj_la1[16] = jj_gen;
          break label_5;
        }
        op = multiplicativeOperator();
        arithmeticAtom();
      }
      jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
      jjtc000 = false;
        jjtn000.value = op;
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
      }
    }
  }

  final public String multiplicativeOperator() throws ParseException {
    Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MULT:
      t = jj_consume_token(MULT);
      break;
    case DIV:
      t = jj_consume_token(DIV);
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
        {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public void arithmeticAtom() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 34:
      jj_consume_token(34);
      arithmeticExpression();
      jj_consume_token(35);
      break;
    case METADATA_OBJECT_NAME:
      level();
      break;
    case MINUS:
    case FLOATING_POINT_LITERAL:
    case INTEGER_LITERAL:
      number();
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void number() throws ParseException {
 /*@bgen(jjtree) Number */
    Number jjtn000 = new Number(JJTNUMBER);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token minus = null;
    Token number;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MINUS:
        minus = jj_consume_token(MINUS);
        break;
      default:
        jj_la1[19] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
        number = jj_consume_token(INTEGER_LITERAL);
        break;
      case FLOATING_POINT_LITERAL:
        number = jj_consume_token(FLOATING_POINT_LITERAL);
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
        jjtn000.value = minus == null ? number.image : minus.image + number.image;
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_3R_7() {
    if (jj_scan_token(31)) return true;
    return false;
  }

  private boolean jj_3R_12() {
    if (jj_3R_15()) return true;
    return false;
  }

  private boolean jj_3R_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  private boolean jj_3_4() {
    if (jj_3R_10()) return true;
    if (jj_3R_11()) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(32)) return true;
    if (jj_3R_6()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_7()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(33)) return true;
    return false;
  }

  private boolean jj_3R_9() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3R_14()) return true;
    }
    return false;
  }

  private boolean jj_3R_8() {
    if (jj_scan_token(METADATA_OBJECT_NAME)) return true;
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  private boolean jj_3R_18() {
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_3R_9()) return true;
    return false;
  }

  private boolean jj_3R_17() {
    if (jj_scan_token(METADATA_OBJECT_NAME)) return true;
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  private boolean jj_3R_13() {
    if (jj_3R_16()) return true;
    return false;
  }

  private boolean jj_3R_19() {
    if (jj_scan_token(METADATA_OBJECT_NAME)) return true;
    return false;
  }

  private boolean jj_3R_16() {
    if (jj_scan_token(34)) return true;
    if (jj_3R_15()) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_3R_8()) return true;
    return false;
  }

  private boolean jj_3R_15() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3R_18()) return true;
    }
    return false;
  }

  private boolean jj_3R_14() {
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3R_11() {
    if (jj_scan_token(RELATIONAL_OPERATOR)) return true;
    return false;
  }

  private boolean jj_3R_10() {
    if (jj_scan_token(METADATA_OBJECT_NAME)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public QueryParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1000000,0x300000,0x80000000,0x0,0x2000000,0x2000000,0x2000000,0x200,0x100,0x2000080,0x2000000,0x2000000,0x2000000,0x36021000,0x1800,0x1800,0x6000,0x6000,0x16001000,0x1000,0x14000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x1,0x5,0x4,0x0,0x0,0x0,0x4,0x0,0x4,0x0,0x4,0x0,0x0,0x0,0x0,0x4,0x0,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[4];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public QueryParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public QueryParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new QueryParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public QueryParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new QueryParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public QueryParser(QueryParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(QueryParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[36];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 21; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 36; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 4; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
