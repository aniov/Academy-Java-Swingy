package aniov.company.view.swingView;

import aniov.company.controller.ObserverOfTheView;
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
        mainWindow = new MainWindow(controllerObserver);
        mainWindow.createMainWindow();
    }

    @Override
    public boolean wantToFight(String villainType) {
        return mainWindow.getGameFrame().getGamePlayPanel().playerWantToFight(villainType);
    }

    @Override
    public void heroWon() {
        mainWindow.getGameFrame().getGamePlayPanel().heroWonOnMap();
    }

    @Override
    public void heroWonTheFight() {
        mainWindow.getGameFrame().getGamePlayPanel().heroWonTheFight();
    }

    @Override
    public void heroCouldNotEscape() {
        mainWindow.getGameFrame().getGamePlayPanel().heroCouldNotRun();
    }

    @Override
    public void heroEscapedVillain() {
        mainWindow.getGameFrame().getGamePlayPanel().heroEscaped();
    }

    @Override
    public void heroLostTheFight() {
        mainWindow.getGameFrame().getGamePlayPanel().heroLostTheFight();
    }

    @Override
    public boolean keepThisArtifact(String artifact) {
        return mainWindow.getGameFrame().getGamePlayPanel().keepArtifact(artifact);
    }
}
