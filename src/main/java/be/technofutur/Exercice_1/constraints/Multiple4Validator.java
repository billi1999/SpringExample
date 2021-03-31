package be.technofutur.Exercice_1.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Multiple4Validator implements ConstraintValidator<Multiple4,Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value%4==0;
    }
}
