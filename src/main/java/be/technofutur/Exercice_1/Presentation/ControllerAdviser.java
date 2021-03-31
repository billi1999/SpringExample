package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.rapport.Rapport;
import be.technofutur.Exercice_1.rapport.RapportValidation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    // Ne sera appelé que sur les test lorsque les Bean sont sur les objets DTO
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RapportValidation rapportGlobal = new RapportValidation();
        if (ex.getGlobalErrorCount()>0){
            for (ObjectError globalError : ex.getBindingResult().getGlobalErrors()) {
                rapportGlobal.getGlobalErrors().add(globalError.getObjectName()+" - "+globalError.getDefaultMessage());
            }
        if (ex.getFieldErrorCount()>0){
            for (ObjectError fieldError : ex.getBindingResult().getFieldErrors()) {
                rapportGlobal.getFieldErrors().add(fieldError.getObjectName()+" - "+fieldError.getDefaultMessage());
            }
        }
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("- global errors --------------"+rapportGlobal.getGlobalErrors()
                        +"\n- field errors --------------"+rapportGlobal.getFieldErrors());
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<Rapport> handleElementAlreadyExist(ElementAlreadyExistsException ex, HttpServletRequest request){
        Rapport rapport = new Rapport(ex.getMessage(),400,request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rapport);
    }
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<Rapport> handleElementNotFound(ElementNotFoundException ex, HttpServletRequest request){
        //Rapport rapport = new Rapport(ex.getMessage(),400,request.getRequestURI());
        Rapport rapport = new Rapport(ex.getMessage(),HttpStatus.NOT_FOUND.value(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rapport);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Rapport> ConstraintViolation(ConstraintViolationException ex, HttpServletRequest request){

        RapportValidation rv= new RapportValidation(
                "Contrainte invalidées",
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );

        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            rv.getGlobalErrors().add(constraintViolation.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rv);
        //Rapport rapport = new Rapport(ex.getMessage(),400,request.getRequestURI());
        /*ex.
        Rapport rapport = new Rapport(ex.getMessage(),400,request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rapport);
    */}

}
