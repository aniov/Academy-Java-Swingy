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

    BAD_VILLAIN(3, 2, 4, 3), SUPER_BAD_VILLAIN(3, 2, 4, 3), Z(3, 2, 4, 3), W(3, 2, 4, 3); //Stats at lvl 1

    private Integer health;
    private Integer attack;
    private Integer defence;
    private Integer hitPoints;
}
