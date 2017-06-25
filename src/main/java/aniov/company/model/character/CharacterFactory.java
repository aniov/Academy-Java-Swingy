package aniov.company.model.character;

import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.hero.HeroType;
import aniov.company.model.character.villain.Villain;
import aniov.company.model.character.villain.VillainType;

/**
 * Created by Marius on 6/19/2017.
 */
public class CharacterFactory {

    private static CharacterFactory characterFactory = new CharacterFactory();

    private void CharacterFactory() {
    }

    public static CharacterFactory getInstance() {
        return characterFactory;
    }

    public Character createNewCharacter(String charName, CharacterType type, Integer level) {

        if (type instanceof HeroType) {
            Hero hero = new Hero();

            HeroType heroType = (HeroType) type;

            hero.setLevel(1);
            hero.setName(charName);
            hero.setHeroType(heroType);
            hero.setAttack(heroType.getAttack());
            hero.setDefence(heroType.getDefence());
            hero.setHealth(heroType.getHealth());
            hero.setHitPoints(heroType.getHitPoints());
            return hero;
        } else if (type instanceof VillainType) {
            Villain villain = new Villain();

            VillainType villainType = (VillainType) type;

            villain.setLevel(level);
            villain.setType(villainType);
            villain.setHealth(villainType.getHealth() * level);
            villain.setAttack(villainType.getAttack() * level);
            villain.setDefence(villainType.getDefence() * level);
            villain.setHitPoints(villainType.getHitPoints() * level);

            return villain;
        }
        return null;
    }
}

