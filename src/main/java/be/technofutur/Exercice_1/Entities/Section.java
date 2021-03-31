package be.technofutur.Exercice_1.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    /*@Column("section_id")
    Si la propriété de classe ne porte
    pas le même nom de colonne que dans la DB,
    Mettre ce @Column(nomDbColonne)

     */
    @Column(name = "section_id")
    //@ManyToOne
    private int id;
    @Column
    private String section_name;
    @Column
    private int delegate_id;
    @OneToMany(mappedBy = "section")
    List<Student> students;
}
