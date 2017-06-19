package aniov.company.view.consoleView;

import aniov.company.model.hero.Hero;
import aniov.company.view.RpgView;

import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */
public class ConsoleView implements RpgView {

    public void displayAllHeroes(List<Hero> heroes) {
        System.out.println("Heroes:");

        for (Hero hero : heroes) {
            System.out.println(hero.getId() + ". Name: " + hero.getName() + ", Type: " + hero.getHeroType() + ", Artifacts: " + hero.getArtifacts());
        }
    }

    public void displayHero(Hero hero) {

    }

    public String readInput() {
        return null;
    }
}
