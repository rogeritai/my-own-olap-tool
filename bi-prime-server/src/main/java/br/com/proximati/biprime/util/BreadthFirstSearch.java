/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.proximati.biprime.util;

import br.com.proximati.biprime.server.olapql.query.result.PivotTableNode;
import br.com.proximati.biprime.server.olapql.query.result.PivotTableNodeRoot;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe que faz caminhamento Breadth-First Search em uma árvore de nós,
 * partindo da raiz até as folhas.
 *
 * Como nossa árvore é um grafo direcionado acíclico, não precisamos
 * utilizar coloração (white, gray, black) nos vértices.
 * @author rnpcapes
 */
public class BreadthFirstSearch {

    private TraversingListener listener;

    public BreadthFirstSearch(TraversingListener listener) {
        this.listener = listener;
    }

    public void perform(PivotTableNodeRoot s) {
        listener.visitingRoot(s);

        Queue<PivotTableNode> Q = new LinkedList<PivotTableNode>();
        Q.offer(s);

        while (Q.size() != 0) {
            PivotTableNode u = Q.poll();

            if (u.getChildrenNodes().isEmpty())
                listener.visitingLeaf(s, u);
            else {
                listener.visitingNonLeaf(s, u);
                for (PivotTableNode v : u.getChildrenNodes())
                    Q.offer(v);
            }
        }
    }
}
