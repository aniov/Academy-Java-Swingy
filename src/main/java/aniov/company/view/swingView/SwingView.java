package aniov.company.view.swingView;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.character.hero.Hero;
import aniov.company.view.RpgView;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Marius on 6/19/2017.
 */
@Getter
@Setter
public class SwingView extends RpgView {

    private ObserverOfTheView controllerObserver;
    private MainWindow mainWindow;
    private boolean fightIsLost;
    private boolean fightIsWon;

    @Override
    public void addObserver(ObserverOfTheView observer) {
        super.addObserver(observer);
        controllerObserver = observers.get(0);
    }

    @Override
    public void showMainInterface() {
        mainWindow = new MainWindow(this);
        mainWindow.createMainWindow();
    }

    @Override
    public boolean wantToFight(String villainType) {
        //return mainWindow.getGameFrame().getGamePlayPanel().wantToFight(villainType);
        return true;
    }

    @Override
    public void heroWon() {
        System.out.println("WON");
    }

    @Override
    public void heroWonTheFight() {
        System.out.println("WON THE FIGHT");
    }

    @Override
    public void heroCouldNotEscape() {

    }

    @Override
    public void heroEscapedVillain() {

    }

    @Override
    public void heroLostTheFight() {
        System.out.println("LOST THE FIGHT");

    }

    @Override
    public boolean keepThisArtifact(String artifact) {
        return true;
    }
}
