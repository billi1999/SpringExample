package be.technofutur.Exercice_1.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { Multiple4Validator.class })
public @interface Multiple4 {
    String message() default "{La valeur n'est pas un multiple de 4}";
    Class<?>[] groups() default {};
   Class <? extends Payload>[] payload() default { };
}
