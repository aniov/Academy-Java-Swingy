package aniov.company.model.artifact;

import aniov.company.model.character.hero.Hero;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Marius on 6/18/2017.
 */
@Entity
@Data
@NoArgsConstructor
public class Artifact implements Serializable {

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

    public Artifact(Hero hero, ArtifactType artifactType) {
        this.hero = hero;
        this.level = hero.getLevel();
        this.type = artifactType;
        this.attack = level * artifactType.getAttack();
        this.defence = level * artifactType.getDefence();
        this.hitPoints = level * artifactType.getHitPoints();
    }

    @Override
    public String toString() {

        if (type.equals(ArtifactType.ARMOR)) {
            return "Type: " + type +
                    ", level: " + level +
                    ", defence: " + defence;
        } else if (type.equals(ArtifactType.WEAPON)) {
            return "Type: " + type +
                    ", level: " + level +
                    ", attack: " + attack;
        } else if(type.equals(ArtifactType.HELM)) {
            return "Type: " + type +
                    ", level: " + level +
                    ", hitPoints: " + hitPoints;
        }
        return "";
    }
}
