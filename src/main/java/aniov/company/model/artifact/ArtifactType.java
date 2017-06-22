package aniov.company.model.artifact;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marius on 6/18/2017.
 */
@Getter
@AllArgsConstructor
public enum ArtifactType {

    WEAPON(0, 2, 2), ARMOR(3, 1, 1), HELM(1, 1, 2);

    private Integer attack;
    private Integer defence;
    private Integer hitPoints;
}
