package aniov.company.view;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.hero.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */

/**
 * Observable Interface or Subject
 */
public interface RpgView {

    List<ObserverOfTheView> observers = new ArrayList<>();

    void displayAllHeroes(List<Hero> heroes);

    void displayHero(Hero hero);

    void readInput();

    void addObserver(ObserverOfTheView observer);

    void removeObserver(ObserverOfTheView observer);
}
