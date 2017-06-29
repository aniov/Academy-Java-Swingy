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

    PUSSY_VILLAIN(2, 6, 3), BAD_VILLAIN(3, 10, 4), SUPER_BAD_VILLAIN(5, 16, 5), BOSS(5, 20, 6); //Stats at lvl 1

    private Integer attack;
    private Integer defence;
    private Integer hitPoints;
}
