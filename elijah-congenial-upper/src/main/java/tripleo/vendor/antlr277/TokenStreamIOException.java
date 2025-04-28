package tripleo.vendor.antlr277;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.cs.usfca.edu
 * Software rights: http://www.tripleo.vendor.antlr277.org/license.html
 *
 * $Id: //depot/code/org.antlr/release/antlr-2.7.7/antlr/TokenStreamIOException.java#2 $
 */

import java.io.*;

/**
 * Wraps an IOException in a TokenStreamException
 */
public class TokenStreamIOException extends TokenStreamException {
    public IOException io;

    /**
     * TokenStreamIOException constructor comment.
     * @param s java.lang.String
     */
    public TokenStreamIOException(IOException io) {
        super(io.getMessage());
        this.io = io;
    }
}
