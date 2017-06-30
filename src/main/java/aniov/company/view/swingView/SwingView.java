package aniov.company.view.swingView;

import aniov.company.model.character.hero.Hero;
import aniov.company.view.RpgView;

/**
 * Created by Marius on 6/19/2017.
 */
public class SwingView extends RpgView {

    @Override
    public void showMainInterface() {
        MainWindow mainWindow = new MainWindow(this);
        mainWindow.createMainWindow();
    }

    @Override
    public void enterHeroInterface() {
        GameFrame gameFrame = new GameFrame(this);
        gameFrame.openHeroSelectPanel();
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
