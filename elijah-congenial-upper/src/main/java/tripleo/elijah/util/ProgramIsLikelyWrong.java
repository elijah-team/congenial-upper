package tripleo.elijah.util;

public class ProgramIsLikelyWrong extends RuntimeException {
	public ProgramIsLikelyWrong(final String message) {
		super(message);
	}

	public ProgramIsLikelyWrong() {
		super();
	}
}
