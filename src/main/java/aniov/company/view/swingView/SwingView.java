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
    private GameFrame gameFrame;

    @Override
    public void addObserver(ObserverOfTheView observer) {
        super.addObserver(observer);
        controllerObserver = observers.get(0);
    }

    @Override
    public void showMainInterface() {
        //mainWindow = new MainWindow(controllerObserver);
        //mainWindow.createMainWindow();

        gameFrame = new GameFrame(controllerObserver);




    }

    @Override
    public boolean wantToFight(String villainType) {
        return gameFrame.getGamePlayPanel().playerWantToFight(villainType);
    }

    @Override
    public void heroWon() {
        gameFrame.getGamePlayPanel().heroWonOnMap();
    }

    @Override
    public void heroWonTheFight() {
        gameFrame.getGamePlayPanel().heroWonTheFight();
    }

    @Override
    public void heroCouldNotEscape() {
        gameFrame.getGamePlayPanel().heroCouldNotRun();
    }

    @Override
    public void heroEscapedVillain() {
        gameFrame.getGamePlayPanel().heroEscaped();
    }

    @Override
    public void heroLostTheFight() {
        gameFrame.getGamePlayPanel().heroLostTheFight();

    }

    @Override
    public boolean keepThisArtifact(String artifact) {
        return gameFrame.getGamePlayPanel().keepArtifact(artifact);
    }
}
