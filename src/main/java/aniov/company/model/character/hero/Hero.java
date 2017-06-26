package aniov.company.model.character.hero;

import aniov.company.model.character.Character;
import aniov.company.model.artifact.Artifact;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aniov on 6/18/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hero extends Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private Integer experience = 0;

    @NotNull
    @Enumerated(EnumType.STRING)
    private HeroType heroType;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Artifact> artifacts = new HashSet<>();

    public void addArtifact(Artifact artifact) {
        artifacts.add(artifact);
    }

    public void deleteArtifact(Artifact artifact) {
        artifacts.remove(artifact);
    }

    public Integer getTotalAttack() {
        Integer totalAttack = this.getAttack();
        for (Artifact artifact : artifacts) {
            totalAttack += artifact.getAttack();
        }
        return totalAttack;
    }

    public Integer getTotalDefence() {
        Integer totalDefence = this.getDefence();
        for (Artifact artifact : artifacts) {
            totalDefence += artifact.getDefence();
        }
        return totalDefence;
    }

    public Integer getTotalHitPoints() {
        Integer totalHitPoints = this.getHitPoints();
        for (Artifact artifact : artifacts) {
            totalHitPoints += artifact.getHitPoints();
        }
        return totalHitPoints;
    }

}
