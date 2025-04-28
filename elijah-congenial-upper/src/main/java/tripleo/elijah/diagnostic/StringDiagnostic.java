package tripleo.elijah.diagnostic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tripleo.elijah_fluffy_congenial.diagnostic.Diagnostic;
import tripleo.elijah_fluffy_congenial.diagnostic.Locatable;

import java.io.PrintStream;
import java.util.List;

public class StringDiagnostic implements Diagnostic {
	private final String   code;
	private final String   message;
	private final Severity severity;

	public StringDiagnostic(final String aCode, final String aMessage) {
		this(aCode, aMessage, Severity.WARN);
	}

	public StringDiagnostic(final String aCode, final String aMessage, final Severity aSeverity) {
		code     = aCode;
		message  = aMessage;
		severity = aSeverity;
	}

	@Override
	public @Nullable String code() {
		return code;
	}

	@Override
	public @NotNull Locatable primary() {
		return null;
	}

	@Override
	public void report(final PrintStream stream) {
		stream.println(code);
		stream.println(" ");
		stream.println(message);
	}

	@Override
	public @NotNull List<Locatable> secondary() {
		return null;
	}

	@Override
	public @Nullable Severity severity() {
		return severity;
	}
}
