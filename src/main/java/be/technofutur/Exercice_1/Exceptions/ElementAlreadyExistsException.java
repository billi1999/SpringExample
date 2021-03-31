package be.technofutur.Exercice_1.Exceptions;

public class ElementAlreadyExistsException extends Exception{
    public ElementAlreadyExistsException() {
        super("L'élément existe déjà dans la db");
    }
}
