package be.technofutur.Exercice_1.constraints;

import be.technofutur.Exercice_1.Entities.Professor;
import be.technofutur.Exercice_1.dto.ProfessorDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.*;

public class WageValidator implements ConstraintValidator<Waged, Professor> {
    @Override
    public boolean isValid(Professor value, ConstraintValidatorContext context) {
         long years = Period.between(value.getHireDate().toLocalDate(), LocalDate.now()).getYears();

        int min = 1500;

        if (years>20){
            min+=100*((years-20) / 5);
        }
       // boolean result = value.getWage() >= min;
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("wage min :"+min).addConstraintViolation();


        return value.getWage() >= min;

    }
}
