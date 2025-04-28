package tripleo.vendor.com_github_romanqed.jeflect.parsers;

import tripleo.vendor.com_github_romanqed.jeflect.ByteAnnotation;
import tripleo.vendor.com_github_romanqed.jeflect.ByteParameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AsmParameter extends ByteParameter {
	final List<ByteAnnotation> annotations;

	AsmParameter(String descriptor, int modifiers) {
		super(descriptor, modifiers);
		this.annotations = new ArrayList<>();
	}

	@Override
	public List<ByteAnnotation> getAnnotations() {
		return Collections.unmodifiableList(annotations);
	}
}
