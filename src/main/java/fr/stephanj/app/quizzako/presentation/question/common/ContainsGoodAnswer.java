/**
 * 
 */
package fr.stephanj.app.quizzako.presentation.question.common;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ContainsGoodAnswerValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
/**
 * Check if the field of type Map contains a key names "goodAnswer" 
 */
public @interface ContainsGoodAnswer {
	String message() default "Validation answers failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
