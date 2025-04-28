package tripleo.vendor.antlr277.debug;

import tripleo.vendor.antlr277.*;
import tripleo.vendor.antlr277.collections.impl.*;

public abstract class DebuggingCharScanner extends CharScanner implements DebuggingParser {
    protected String ruleNames[];
    protected String semPredNames[];
    private ParserEventSupport parserEventSupport = new ParserEventSupport(this);
    private boolean _notDebugMode = false;

    public DebuggingCharScanner(InputBuffer cb) {
        super(cb);
    }

    public DebuggingCharScanner(LexerSharedInputState state) {
        super(state);
    }

    public void addMessageListener(MessageListener l) {
        parserEventSupport.addMessageListener(l);
    }

    public void addNewLineListener(NewLineListener l) {
        parserEventSupport.addNewLineListener(l);
    }

    public void addParserListener(ParserListener l) {
        parserEventSupport.addParserListener(l);
    }

    public void addParserMatchListener(ParserMatchListener l) {
        parserEventSupport.addParserMatchListener(l);
    }

    public void addParserTokenListener(ParserTokenListener l) {
        parserEventSupport.addParserTokenListener(l);
    }

    public void addSemanticPredicateListener(SemanticPredicateListener l) {
        parserEventSupport.addSemanticPredicateListener(l);
    }

    public void addSyntacticPredicateListener(SyntacticPredicateListener l) {
        parserEventSupport.addSyntacticPredicateListener(l);
    }

    public void addTraceListener(TraceListener l) {
        parserEventSupport.addTraceListener(l);
    }

    protected void fireEnterRule(int num, int data) {
        if (isDebugMode()) parserEventSupport.fireEnterRule(num, inputState.guessing, data);
    }    public void consume() throws CharStreamException {
        int la_1 = -99;
        try {
            la_1 = LA(1);
        } catch (CharStreamException ignoreAnIOException) {
        }
        super.consume();
        parserEventSupport.fireConsume(la_1);
    }

    public boolean isDebugMode() {
        return !_notDebugMode;
    }    public char LA(int i) throws CharStreamException {
        char la = super.LA(i);
        parserEventSupport.fireLA(i, la);
        return la;
    }

    public void setDebugMode(boolean value) {
        _notDebugMode = !value;
    }    protected Token makeToken(int t) {
        // do something with char buffer???
        //		try {
        //			Token tok = (Token)tokenObjectClass.newInstance();
        //			tok.setType(t);
        //			// tok.setText(getText()); done in generated lexer now
        //			tok.setLine(line);
        //			return tok;
        //		}
        //		catch (InstantiationException ie) {
        //			panic("can't instantiate a Token");
        //		}
        //		catch (IllegalAccessException iae) {
        //			panic("Token class is not accessible");
        //		}
        return super.makeToken(t);
    }

    protected void fireExitRule(int num, int ttype) {
        if (isDebugMode()) parserEventSupport.fireExitRule(num, inputState.guessing, ttype);
    }    public void match(char c) throws MismatchedCharException, CharStreamException {
        char la_1 = LA(1);
        try {
            super.match(c);
            parserEventSupport.fireMatch(c, inputState.guessing);
        } catch (MismatchedCharException e) {
            if (inputState.guessing == 0) parserEventSupport.fireMismatch(la_1, c, inputState.guessing);
            throw e;
        }
    }

    protected boolean fireSemanticPredicateEvaluated(int type, int num, boolean condition) {
        if (isDebugMode())
            return parserEventSupport.fireSemanticPredicateEvaluated(type, num, condition, inputState.guessing);
        else return condition;
    }    public void match(BitSet b) throws MismatchedCharException, CharStreamException {
        String text = this.text.toString();
        char la_1 = LA(1);
        try {
            super.match(b);
            parserEventSupport.fireMatch(la_1, b, text, inputState.guessing);
        } catch (MismatchedCharException e) {
            if (inputState.guessing == 0) parserEventSupport.fireMismatch(la_1, b, text, inputState.guessing);
            throw e;
        }
    }

    protected void fireSyntacticPredicateFailed() {
        if (isDebugMode()) parserEventSupport.fireSyntacticPredicateFailed(inputState.guessing);
    }    public void match(String s) throws MismatchedCharException, CharStreamException {
        StringBuffer la_s = new StringBuffer("");
        int len = s.length();
        // peek at the next len worth of characters
        try {
            for (int i = 1; i <= len; i++) {
                la_s.append(super.LA(i));
            }
        } catch (Exception ignoreMe) {
        }

        try {
            super.match(s);
            parserEventSupport.fireMatch(s, inputState.guessing);
        } catch (MismatchedCharException e) {
            if (inputState.guessing == 0) parserEventSupport.fireMismatch(la_s.toString(), s, inputState.guessing);
            throw e;
        }
    }

    protected void fireSyntacticPredicateStarted() {
        if (isDebugMode()) parserEventSupport.fireSyntacticPredicateStarted(inputState.guessing);
    }    public void matchNot(char c) throws MismatchedCharException, CharStreamException {
        char la_1 = LA(1);
        try {
            super.matchNot(c);
            parserEventSupport.fireMatchNot(la_1, c, inputState.guessing);
        } catch (MismatchedCharException e) {
            if (inputState.guessing == 0) parserEventSupport.fireMismatchNot(la_1, c, inputState.guessing);
            throw e;
        }
    }

    protected void fireSyntacticPredicateSucceeded() {
        if (isDebugMode()) parserEventSupport.fireSyntacticPredicateSucceeded(inputState.guessing);
    }    public void matchRange(char c1, char c2) throws MismatchedCharException, CharStreamException {
        char la_1 = LA(1);
        try {
            super.matchRange(c1, c2);
            parserEventSupport.fireMatch(la_1, "" + c1 + c2, inputState.guessing);
        } catch (MismatchedCharException e) {
            if (inputState.guessing == 0) parserEventSupport.fireMismatch(la_1, "" + c1 + c2, inputState.guessing);
            throw e;
        }
    }

    public String getRuleName(int num) {
        return ruleNames[num];
    }    public void newline() {
        super.newline();
        parserEventSupport.fireNewLine(getLine());
    }

    public String getSemPredName(int num) {
        return semPredNames[num];
    }

    public synchronized void goToSleep() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public void removeMessageListener(MessageListener l) {
        parserEventSupport.removeMessageListener(l);
    }

    public void removeNewLineListener(NewLineListener l) {
        parserEventSupport.removeNewLineListener(l);
    }

    public void removeParserListener(ParserListener l) {
        parserEventSupport.removeParserListener(l);
    }

    public void removeParserMatchListener(ParserMatchListener l) {
        parserEventSupport.removeParserMatchListener(l);
    }

    public void removeParserTokenListener(ParserTokenListener l) {
        parserEventSupport.removeParserTokenListener(l);
    }

    public void removeSemanticPredicateListener(SemanticPredicateListener l) {
        parserEventSupport.removeSemanticPredicateListener(l);
    }

    public void removeSyntacticPredicateListener(SyntacticPredicateListener l) {
        parserEventSupport.removeSyntacticPredicateListener(l);
    }

    public void removeTraceListener(TraceListener l) {
        parserEventSupport.removeTraceListener(l);
    }

    /** Report exception errors caught in nextToken() */
    public void reportError(MismatchedCharException e) {
        parserEventSupport.fireReportError(e);
        super.reportError(e);
    }

    public void setupDebugging() {}

    public synchronized void wakeUp() {
        notify();
    }











    /** Parser error-reporting function can be overridden in subclass */
    public void reportError(String s) {
        parserEventSupport.fireReportError(s);
        super.reportError(s);
    }
    /** Parser warning-reporting function can be overridden in subclass */
    public void reportWarning(String s) {
        parserEventSupport.fireReportWarning(s);
        super.reportWarning(s);
    }






}
