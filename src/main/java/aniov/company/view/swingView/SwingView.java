package aniov.company.view.swingView;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.character.hero.Hero;
import aniov.company.view.RpgView;

import javax.swing.*;

/**
 * Created by Marius on 6/19/2017.
 */
public class SwingView extends RpgView {

    private JFrame jFrame;


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
        System.out.println("Main");
        jFrame = new JFrame();

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);

        jFrame.add(b);//adding button in JFrame

        jFrame.setSize(400,500);//400 width and 500 height
        jFrame.setLayout(null);//using no layout managers
        jFrame.setVisible(true);//making the frame visible

        jFrame.setVisible(true);
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
