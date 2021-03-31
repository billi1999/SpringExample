package be.technofutur.Exercice_1.Exceptions;

public class ElementNotFoundException extends Exception{
    //On mets Object pour pouvoir travailler avec n'importe quel type d'id
    private Object id;
    public ElementNotFoundException(Object id) {
        super("L'élément d'id"+id+"en question n'a pas été trouvé");
    }
}
