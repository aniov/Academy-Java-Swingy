package aniov.company.model.hero;

import aniov.company.model.Character;
import aniov.company.model.artifact.Artifact;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
@RequiredArgsConstructor
public class Hero extends Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private HeroType heroType;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Artifact> artifacts = new HashSet<Artifact>();

    public void addArtifact(Artifact artifact) {
        artifacts.add(artifact);
    }

    public void deleteArtifact(Artifact artifact) {
        artifacts.remove(artifact);
    }

}
