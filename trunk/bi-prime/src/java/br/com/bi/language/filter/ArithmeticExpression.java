/* Generated By:JJTree: Do not edit this line. ArithmeticExpression.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package br.com.bi.language.filter;

public
class ArithmeticExpression extends SimpleNode {
  public ArithmeticExpression(int id) {
    super(id);
  }

  public ArithmeticExpression(FilterParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(FilterParserVisitor visitor, StringBuilder data) {
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=4c787070c44aed1ecec47c0a3688a90a (do not edit this line) */
