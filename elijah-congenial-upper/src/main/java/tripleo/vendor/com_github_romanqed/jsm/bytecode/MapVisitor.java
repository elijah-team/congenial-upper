package tripleo.vendor.com_github_romanqed.jsm.bytecode;

import tripleo.vendor.com_github_romanqed.jsm.model.RangeToken;
import tripleo.vendor.com_github_romanqed.jsm.model.SetToken;
import tripleo.vendor.com_github_romanqed.jsm.model.SingleToken;
import tripleo.vendor.com_github_romanqed.jsm.model.TokenVisitor;

import java.util.Map;

final class MapVisitor implements TokenVisitor {
	private final Map<Integer, Integer> transitions;
	private final int                   target;

	MapVisitor(Map<Integer, Integer> transitions, int target) {
		this.transitions = transitions;
		this.target      = target;
	}

	@Override
	public <T> void visit(SingleToken<T> token) {
		transitions.put(token.getValue().hashCode(), target);
	}

	@Override
	public <T> void visit(RangeToken<T> token) {
		throw new IllegalArgumentException("Bytecode machine factory does not support range tokens");
	}

	@Override
	public <T> void visit(SetToken<T> token) {
		for (var value : token.getValues()) {
			transitions.put(value.hashCode(), target);
		}
	}
}
