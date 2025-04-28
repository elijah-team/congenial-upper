package tripleo.elijah_fluffy_congenial.util;

import tripleo.elijah_fluffy_congenial.diagnostic.Diagnostic;

public class DiagnosticException extends Throwable {
	private final Diagnostic d;

	public DiagnosticException(final Diagnostic aD) {
		d = aD;
	}
}
