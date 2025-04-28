package tripleo.vendor.antlr277.debug;

import tripleo.vendor.antlr277.*;

import java.util.*;

public class DebuggingInputBuffer extends InputBuffer {
    private InputBuffer buffer;
    private InputBufferEventSupport inputBufferEventSupport;
    private boolean debugMode = true;

    public DebuggingInputBuffer(InputBuffer buffer) {
        this.buffer = buffer;
        inputBufferEventSupport = new InputBufferEventSupport(this);
    }

    public void addInputBufferListener(InputBufferListener l) {
        inputBufferEventSupport.addInputBufferListener(l);
    }

    public void consume() {
        char la = ' ';
        try {
            la = buffer.LA(1);
        } catch (CharStreamException e) {
        } // vaporize it...
        buffer.consume();
        if (debugMode) inputBufferEventSupport.fireConsume(la);
    }

    public boolean isMarked() {
        return buffer.isMarked();
    }

    public char LA(int i) throws CharStreamException {
        char la = buffer.LA(i);
        if (debugMode) inputBufferEventSupport.fireLA(la, i);
        return la;
    }

    public void fill(int a) throws CharStreamException {
        buffer.fill(a);
    }

    public int mark() {
        int m = buffer.mark();
        inputBufferEventSupport.fireMark(m);
        return m;
    }

    public void rewind(int mark) {
        buffer.rewind(mark);
        inputBufferEventSupport.fireRewind(mark);
    }

    public Vector getInputBufferListeners() {
        return inputBufferEventSupport.getInputBufferListeners();
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean value) {
        debugMode = value;
    }

    public void removeInputBufferListener(InputBufferListener l) {
        if (inputBufferEventSupport != null) inputBufferEventSupport.removeInputBufferListener(l);
    }
}
