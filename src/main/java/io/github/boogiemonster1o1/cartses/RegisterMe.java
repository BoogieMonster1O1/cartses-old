package io.github.boogiemonster1o1.cartses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegisterMe {
	Predicate<Field> PREDICATE = ((field) -> Modifier.isPublic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) && (field.isAnnotationPresent(RegisterMe.class)));

	String value();
}
