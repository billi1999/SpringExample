package be.technofutur.Exercice_1.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Permet de déterminer sur quoi mettre l'annotation
// tableau avec les différents endroits où mettre l'annotation
// Utiliser sur la classe StudentDTO

// TYPE -> Déclaration d'un type (permettre de mettre au-dessus de sa classe)
// ANNOTATION_TYPE -> Permet de le mettre sur une autre annotation que l'on crée par exemple
// ANNOTATION_TYPE pas utile ici
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
// Retention -> va permettre de dire que l'annotation va être gardée après la compilation
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={OldTimeResultValidator.class})
public @interface OldTimeResult {
    //Par défaut va définir le message d'erreur
    String message() default "{Il y a 50 ans, les notes étaient étaient de 0 à 10}";
    // {} va définir par défaut qu'il n'y a pas de groupe
    //va grouper des annotations avec des interfaces
    Class<?>[] groups() default {};
    //Récupérer des métadonnées supplémentaires
    Class <? extends Payload>[] payload() default { };


}
