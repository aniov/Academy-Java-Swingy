package aniov.company.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Marius on 6/18/2017.
 */
@Entity
@Data
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer level;
    @Enumerated(EnumType.STRING)
    private ArtifactType type;
    private Integer attack;
    private Integer defence;
    private Integer hitPoints;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Hero hero;
}
