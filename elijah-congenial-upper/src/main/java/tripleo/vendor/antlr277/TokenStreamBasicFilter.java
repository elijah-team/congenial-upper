package tripleo.vendor.antlr277;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.cs.usfca.edu
 * Software rights: http://www.tripleo.vendor.antlr277.org/license.html
 *
 * $Id: //depot/code/org.antlr/release/antlr-2.7.7/antlr/TokenStreamBasicFilter.java#2 $
 */

import tripleo.vendor.antlr277.ASdebug.*;
import tripleo.vendor.antlr277.collections.impl.*;

/** This object is a TokenStream that passes through all
 *  tokens except for those that you tell it to discard.
 *  There is no buffering of the tokens.
 */
public class TokenStreamBasicFilter implements TokenStream, IASDebugStream {
    /** The set of token types to discard */
    protected BitSet discardMask;

    /** The input stream */
    protected TokenStream input;

    public TokenStreamBasicFilter(TokenStream input) {
        this.input = input;
        discardMask = new BitSet();
    }

    public void discard(int ttype) {
        discardMask.add(ttype);
    }

    public void discard(BitSet mask) {
        discardMask = mask;
    }

    public Token nextToken() throws TokenStreamException {
        Token tok = input.nextToken();
        while (tok != null && discardMask.member(tok.getType())) {
            tok = input.nextToken();
        }
        return tok;
    }

    public String getEntireText() {
        return ASDebugStream.getEntireText(this.input);
    }

    public TokenOffsetInfo getOffsetInfo(Token token) {
        return ASDebugStream.getOffsetInfo(this.input, token);
    }
}
