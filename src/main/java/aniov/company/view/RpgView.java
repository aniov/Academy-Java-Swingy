package aniov.company.view;

import aniov.company.model.hero.Hero;

import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */
public interface RpgView {

    public void displayAllHeroes(List<Hero> heroes);
    public void displayHero(Hero hero);
    public String readInput();
}
