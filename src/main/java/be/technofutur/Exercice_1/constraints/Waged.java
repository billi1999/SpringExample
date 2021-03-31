package be.technofutur.Exercice_1.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {WageValidator.class})
public @interface Waged {
    String message() default "{wage invalide}";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default { };
}
