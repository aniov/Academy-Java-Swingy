package aniov.company.model.character.hero;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marius on 6/18/2017.
 */
@AllArgsConstructor
@Getter
public enum HeroType {

    PALADIN(10, 8, 10, 6), WARRIOR(12, 6, 12, 4), KNIGHT(8, 8, 8, 8), WARLOCK(7, 10, 8, 8);

    private Integer health;
    private Integer attack;
    private Integer defence;
    private Integer hitPoints;

}
