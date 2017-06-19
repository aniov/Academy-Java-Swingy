package aniov.company.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String name;
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Artifact> artifacts;

}
