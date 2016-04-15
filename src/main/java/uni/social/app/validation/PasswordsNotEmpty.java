package uni.social.app.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsNotEmptyValidator.class)
@Documented
public @interface PasswordsNotEmpty {

	String message() default "PasswordsNotEmpty";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String triggerFieldName() default "";

	String passwordFieldName() default "";

	String passwordVerificationFieldName() default "";
}