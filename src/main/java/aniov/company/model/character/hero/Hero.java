package aniov.company.model.character.hero;

import aniov.company.model.artifact.Artifact;
import aniov.company.model.character.Character;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
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
    private Set<Artifact> artifacts = new LinkedHashSet<>();

    public void addArtifact(Artifact artifact) {
        if (artifacts.contains(artifact)) {
            artifacts.remove(artifact);
        }
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

    public void addXpAndLvlUp(Integer xp) {
        Integer maxXp = (int) (getLevel() * 1000 + Math.pow((getLevel() - 1), 2) * 450);

        if (experience + xp >= maxXp) {
            experience = experience + xp - maxXp;
            setLevel(getLevel() + 1);
        } else {
            experience += xp;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Class: " + heroType + ", Level: " + getLevel() + ", Xp: " + experience + "\tTotal Stats:"
                + " Attack: " + getTotalAttack() + ", Defence: " + getTotalDefence() + ", Hit points: " + getTotalHitPoints()
                + "\n\t\tArtifacts:\n" + artifacts();
    }

    private String artifacts() {
        StringBuilder sb = new StringBuilder();
        for (Artifact artifact : artifacts) {
            sb.append("\t\t\t" + artifact + "\n");
        }
        return sb.toString();
    }
}
