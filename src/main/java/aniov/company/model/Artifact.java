package aniov.company.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Marius on 6/18/2017.
 */
@Entity
@Data
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Integer level;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ArtifactType type;
    @NotNull
    private Integer attack;
    @NotNull
    private Integer defence;
    @NotNull
    private Integer hitPoints;
    @NotNull
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Hero hero;
}
