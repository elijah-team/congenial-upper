package tripleo.vendor.antlr277.debug;

import java.util.*;

public interface ListenerBase extends EventListener {

    public void doneParsing(TraceEvent e);

    public void refresh();
}
