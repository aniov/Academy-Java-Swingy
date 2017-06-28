package aniov.company.model.artifact;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marius on 6/18/2017.
 */
@Getter
@AllArgsConstructor
public enum ArtifactType {

    WEAPON(2, 0, 0), ARMOR(0, 2, 0), HELM(0, 0, 2);

    private Integer attack;
    private Integer defence;
    private Integer hitPoints;
}
