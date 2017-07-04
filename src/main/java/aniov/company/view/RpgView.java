package aniov.company.view;

import aniov.company.controller.ObserverOfTheView;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */

/**
 * Observable Interface (abstract class) or Subject
 */
@Getter
public abstract class RpgView {

    protected List<ObserverOfTheView> observers = new ArrayList<>(); //We have only one Observer

    public void addObserver(ObserverOfTheView observer) {
        observers.add(observer);
    }

    public void removeObserver(ObserverOfTheView observer) {
        observers.remove(observer);
    }

    public abstract void showMainInterface();

    public abstract boolean wantToFight(String villainType);

    public abstract void heroWonTheFight();

    public abstract void heroLostTheFight();

    public abstract void heroWon();

    public abstract void heroCouldNotEscape();

    public abstract void heroEscapedVillain();

    public abstract boolean keepThisArtifact(String artifact);


}
