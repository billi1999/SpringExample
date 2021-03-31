package be.technofutur.Exercice_1.dto.container;

import lombok.Data;

import java.util.List;

@Data
public class PagedContainer <T>{
    // tout les éléments présent sur la page
    private List<T> elements;
    // suite à une requête de pages
    //va récupérer le nombre de page que l'on va créer
    private Integer nbrPage;
    private Integer nbrElement;
    private Integer page;

    //url de la prochaine page
    private String nextPage;
    //url de la page précédente
    private String previousPage;

    public PagedContainer(List<T> elements, Integer nbrPage, Integer nbrElement, Integer page, long totalElements) {
        this.elements = elements;
        this.nbrPage = nbrPage;
        this.nbrElement = nbrElement;
        this.page = page;
    }
}
