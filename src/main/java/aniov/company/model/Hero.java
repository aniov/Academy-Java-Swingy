package aniov.company.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by aniov on 6/18/2017.
 */
@Entity
@Data
public class Hero extends Character{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Artifact> artifacts;

}
