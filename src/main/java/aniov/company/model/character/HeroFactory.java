package aniov.company.model.character;

import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.hero.HeroType;

/**
 * Created by Marius on 6/19/2017.
 */
public class HeroFactory {

    private static HeroFactory heroFactory = new HeroFactory();

    private void HeroFactory() {
    }

    public static HeroFactory getInstance() {
        return heroFactory;
    }

    public Hero createNewHero(String name, HeroType type) {

        Hero hero = new Hero();

        hero.setLevel(1);
        hero.setName(name);
        hero.setHeroType(type);
        hero.setAttack(type.getAttack());
        hero.setDefence(type.getDefence());
        hero.setHealth(type.getHealth());
        hero.setHitPoints(type.getHitPoints());

        return hero;
    }
}

