package be.technofutur.Exercice_1.constraints;

import be.technofutur.Exercice_1.dto.StudentDTO;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;

public class OldTimeResultValidator implements ConstraintValidator<OldTimeResult, StudentDTO> {

    @Override
    public boolean isValid(StudentDTO value, ConstraintValidatorContext context) {

        if (value.getBirthDate().isBefore(LocalDateTime.now().minusYears(50)) && (value.getYearResult()>10 || value.getYearResult()<0)) {
            // va désactiver le message par défaut
            context.disableDefaultConstraintViolation();
            if (value.getYearResult()<0){
                context.buildConstraintViolationWithTemplate("{ +50 ans et result < 0} ")
                        .addConstraintViolation();
                return false;
            }
            else if (value.getYearResult()>10){
                context.buildConstraintViolationWithTemplate("{ +50 ans et result > 10} ")
                        .addConstraintViolation();
                return false;
            }
        }


        return true;
    }
}
