package by.sam_solutions.grigorieva.olga.backend.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PatternValidator.class)
@Documented
public @interface CustomPattern {

    String message() default "{by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String patternKey();
}
