package aniov.company.model.character.villain;

import aniov.company.model.character.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marius on 6/18/2017.
 */
@Getter
@AllArgsConstructor
public enum VillainType implements CharacterType {

    PUSSY_VILLAIN(1, 2, 1), BAD_VILLAIN(2, 4, 3), SUPER_BAD_VILLAIN(3, 5, 6), BOSS(5, 5, 7); //Stats at lvl 1

    private Integer attack;
    private Integer defence;
    private Integer hitPoints;
}
