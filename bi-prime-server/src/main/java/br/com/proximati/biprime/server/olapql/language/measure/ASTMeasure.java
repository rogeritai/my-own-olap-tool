/* Generated By:JJTree: Do not edit this line. ASTMeasure.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package br.com.proximati.biprime.server.olapql.language.measure;

public
class ASTMeasure extends SimpleNode {
  public ASTMeasure(int id) {
    super(id);
  }

  public ASTMeasure(MeasureParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(MeasureParserVisitor visitor, StringBuilder data) throws Exception {
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=8afbab1458ea06875acbb310c13a2408 (do not edit this line) */
