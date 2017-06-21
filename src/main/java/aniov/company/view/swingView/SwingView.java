package aniov.company.view.swingView;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.hero.Hero;
import aniov.company.view.RpgView;

import javax.swing.*;
import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */
public class SwingView extends JFrame implements RpgView {

    public void displayAllHeroes(List<Hero> heroes) {
    }

    public void displayHero(Hero hero) {
    }

    public void readInput() {

    }

    @Override
    public void addObserver(ObserverOfTheView observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverOfTheView observer) {
        observers.remove(observer);
    }
}
