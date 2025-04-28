package tripleo.vendor.com_github_romanqed.jeflect.parsers;

import tripleo.vendor.com_github_romanqed.jeflect.ByteAnnotation;
import tripleo.vendor.com_github_romanqed.jeflect.ByteClass;
import tripleo.vendor.com_github_romanqed.jeflect.ByteMethod;
import tripleo.vendor.com_github_romanqed.jeflect.ByteParameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AsmMethod extends ByteMethod {
	final List<AsmParameter>   parameters;
	final List<ByteAnnotation> annotations;

	AsmMethod(ByteClass parent, String descriptor, String[] exceptions, String name, int modifiers) {
		super(parent, descriptor, exceptions, name, modifiers);
		this.parameters  = new ArrayList<>();
		this.annotations = new ArrayList<>();
	}

	@Override
	public List<ByteAnnotation> getAnnotations() {
		return Collections.unmodifiableList(annotations);
	}

	@Override
	public List<ByteParameter> getParameters() {
		return Collections.unmodifiableList(parameters);
	}
}
