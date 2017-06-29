package aniov.company.view;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.character.hero.Hero;
import aniov.company.model.map.GameMap;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */

/**
 * Observable Interface (abstract class) or Subject
 */
public abstract class RpgView {

    protected List<ObserverOfTheView> observers = new ArrayList<>(); //We have only one Observer

    public void addObserver(ObserverOfTheView observer){
        observers.add(observer);
    }

    public void removeObserver(ObserverOfTheView observer){
        observers.remove(observer);
    }

    public abstract void showMainInterface();

    protected abstract void enterHeroInterface();

    protected abstract Hero choseHero(Integer heroIndex);

    public abstract boolean wantToFight(String villainType);

    public abstract void heroWonTheFight();

    public abstract void heroLostTheFight();

    public abstract void heroCouldNotEscape();

    public abstract void heroEscapedVillain();

    public abstract boolean keepThisArtifact(String artifact);



}
