package tripleo.vendor.antlr277;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.cs.usfca.edu
 * Software rights: http://www.tripleo.vendor.antlr277.org/license.html
 *
 * $Id: //depot/code/org.antlr/release/antlr-2.7.7/antlr/GrammarSymbol.java#2 $
 */

/**A GrammarSymbol is a generic symbol that can be
 * added to the symbol table for a grammar.
 */
abstract class GrammarSymbol {
    protected String id;

    public GrammarSymbol() {}

    public GrammarSymbol(String s) {
        id = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String s) {
        id = s;
    }
}
