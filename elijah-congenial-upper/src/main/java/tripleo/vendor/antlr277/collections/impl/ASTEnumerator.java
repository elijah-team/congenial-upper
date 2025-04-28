package tripleo.vendor.antlr277.collections.impl;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.cs.usfca.edu
 * Software rights: http://www.tripleo.vendor.antlr277.org/license.html
 *
 * $Id: //depot/code/org.antlr/release/antlr-2.7.7/antlr/collections/impl/ASTEnumerator.java#2 $
 */

import tripleo.vendor.antlr277.collections.*;

import java.util.*;

public class ASTEnumerator implements ASTEnumeration {
    /** The list of root nodes for subtrees that match */
    VectorEnumerator nodes;

    int i = 0;

    public ASTEnumerator(Vector v) {
        nodes = new VectorEnumerator(v);
    }

    public boolean hasMoreNodes() {
        synchronized (nodes) {
            return i <= nodes.vector.lastElement;
        }
    }

    public AST nextNode() {
        synchronized (nodes) {
            if (i <= nodes.vector.lastElement) {
                return (AST) nodes.vector.data[i++];
            }
            throw new NoSuchElementException("ASTEnumerator");
        }
    }
}
