package aniov.company.view;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.character.hero.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */

/**
 * Observable Interface or Subject
 */
public interface RpgView {

    List<ObserverOfTheView> observers = new ArrayList<>(); //We have only one Observer

    void displayAllHeroes();

    void displayHero(Hero hero);

    void addObserver(ObserverOfTheView observer);

    void removeObserver(ObserverOfTheView observer);

    void showMainInterface();

    void enterHeroInterface();
}
