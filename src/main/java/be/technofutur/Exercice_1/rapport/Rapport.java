package be.technofutur.Exercice_1.rapport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rapport {

    private String message;
    private int code;
    private String chemin;

    public Rapport(String message, int code, String chemin) {
        this.message = message;
        this.code = code;
        this.chemin = chemin;
    }
}
