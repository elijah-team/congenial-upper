package tripleo.elijah.util;

import org.jdeferred2.DoneCallback;
import org.jdeferred2.Promise;

public class PromiseReadySupplier<T> implements ReadySupplier<T> {
	private final Eventual<T> p;

	public PromiseReadySupplier(final Promise<T, Void, Void> aP) {
		p = new Eventual<>();
		aP.then(p::resolve);
		// aP.fail(p::reject); // void
	}

	public PromiseReadySupplier(final Eventual<T> aP) {
		p = aP;
	}

	@Override
	public T get() {
		return null;
	}

	@Override
	public boolean ready() {
		return p.isResolved();
	}

	public void then(final DoneCallback<T> aO) {
		p.then(aO);
	}
}
