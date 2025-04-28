package tripleo.vendor.com_github_romanqed.jeflect.fields;

import tripleo.vendor.com_github_romanqed.jeflect.DefineClassLoader;
import tripleo.vendor.com_github_romanqed.jeflect.DefineLoader;
import tripleo.vendor.com_github_romanqed.jeflect.DefineObjectFactory;
import tripleo.vendor.com_github_romanqed.jeflect.ObjectFactory;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * <p>A class representing a factory that creates
 * {@link FieldAccessor} instances for subsequent access to the field.</p>
 * <p>Access occurs at almost native speed, minus the time to call the proxy class method.</p>
 */
public final class FieldAccessorFactory {
	private static final String                       ACCESSOR = "Accessor";
	private final        ObjectFactory<FieldAccessor> factory;

	public FieldAccessorFactory(ObjectFactory<FieldAccessor> factory) {
		this.factory = Objects.requireNonNull(factory);
	}

	public FieldAccessorFactory(DefineLoader loader) {
		this(new DefineObjectFactory<>(loader));
	}

	public FieldAccessorFactory() {
		this(new DefineClassLoader());
	}

	/**
	 * Creates a proxy implementation of the {@link FieldAccessor} interface for the specified field.
	 *
	 * @param field the target field
	 * @return object of the generated proxy class implementing the {@link FieldAccessor} interface
	 */
	public FieldAccessor packField(Field field) {
		var toHash = field.getDeclaringClass().getName() + field.getName();
		var name   = ACCESSOR + toHash.hashCode();
		return factory.create(name, () -> FieldUtil.createAccessor(name, field));
	}
}
