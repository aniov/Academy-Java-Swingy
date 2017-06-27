package aniov.company.model.character.hero;

import aniov.company.model.character.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marius on 6/18/2017.
 */
@AllArgsConstructor
@Getter
public enum HeroType implements CharacterType {

    PALADIN(8, 10, 6), WARRIOR(6, 12, 4), KNIGHT(8, 8, 8), WARLOCK(10, 8, 8); //Stats at lvl 1

    private Integer attack;
    private Integer defence;
    private Integer hitPoints;
}
