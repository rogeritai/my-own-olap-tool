/* Generated By:JJTree: Do not edit this line. Negation.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package br.com.bi.language.filter;

public
class Negation extends SimpleNode {
  public Negation(int id) {
    super(id);
  }

  public Negation(FilterParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(FilterParserVisitor visitor, StringBuilder data) {
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=c973d3d1f57e2541f0cb2603e4f8c02c (do not edit this line) */
