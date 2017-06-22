package aniov.company.model.character.hero;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marius on 6/18/2017.
 */
@AllArgsConstructor
@Getter
public enum HeroType {

    PALADIN(10, 8, 8, 4), WORRIOR(10, 8, 8, 4), KNIGHT(10, 8, 8, 4), WARLOCK(10, 8, 8, 4);

    private Integer health;
    private Integer attack;
    private Integer defence;
    private Integer hitPoints;

}
