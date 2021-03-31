package be.technofutur.Exercice_1.Exceptions;

public class PageSizeInvalidException extends Exception{

    private final String acceptableSizes;

    public PageSizeInvalidException(String acceptableSizes){
        super("la taille donnée pour la page est invalide. Tailles acceptables :"+acceptableSizes);
        this.acceptableSizes=acceptableSizes;
    }

    public String getAcceptableSizes() {
        return acceptableSizes;
    }
}
