package tripleo.vendor.com_github_romanqed.jeflect.parsers;

import tripleo.vendor.com_github_romanqed.jeflect.ByteAnnotation;
import tripleo.vendor.com_github_romanqed.jeflect.ByteClass;
import tripleo.vendor.com_github_romanqed.jeflect.ByteField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AsmField extends ByteField {
	final List<ByteAnnotation> annotations;

	AsmField(ByteClass parent, String descriptor, Object value, String name, int modifiers) {
		super(parent, descriptor, value, name, modifiers);
		this.annotations = new ArrayList<>();
	}

	@Override
	public List<ByteAnnotation> getAnnotations() {
		return Collections.unmodifiableList(annotations);
	}
}
