package tripleo.vendor.antlr277;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.cs.usfca.edu
 * Software rights: http://www.tripleo.vendor.antlr277.org/license.html
 */

public class ParseTreeToken extends ParseTree {
    protected Token token;

    public ParseTreeToken(Token token) {
        this.token = token;
    }

    protected int getLeftmostDerivation(StringBuffer buf, int step) {
        buf.append(' ');
        buf.append(toString());
        return step; // did on replacements
    }

    public String toString() {
        if (token != null) {
            return token.getText();
        }
        return "<missing token>";
    }
}
