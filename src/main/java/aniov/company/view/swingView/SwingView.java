package aniov.company.view.swingView;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.character.hero.Hero;
import aniov.company.view.RpgView;

import javax.swing.*;

/**
 * Created by Marius on 6/19/2017.
 */
public class SwingView extends JFrame implements RpgView {

/*    public void displayAllHeroes() {
    }

    public void displayHero(Hero hero) {
    }

    public void readInput() {

    }*/

    @Override
    public void addObserver(ObserverOfTheView observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverOfTheView observer) {
        observers.remove(observer);
    }

    @Override
    public void showMainInterface() {

    }

    @Override
    public void enterHeroInterface() {

    }

    @Override
    public Hero choseHero(Integer heroIndex) {
        return null;
    }

    @Override
    public boolean wantToFight(String villainType) {
        return false;
    }

    @Override
    public void heroWonTheFight() {
    }

    @Override
    public void heroCouldNotEscape() {

    }

    @Override
    public void heroEscapedVillain() {

    }

    @Override
    public void heroLostTheFight() {

    }

    @Override
    public boolean keepThisArtifact(String artifact) {
        return false;
    }
}
