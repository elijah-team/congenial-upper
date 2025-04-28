package tripleo.elijah.util;

import tripleo.elijah_fluffy_congenial.diagnostic.Diagnostic;

public interface CompletableProcess<T> {
	void add(T item);

	void complete();

	void error(Diagnostic d);

	void preComplete();

	void start();
}
