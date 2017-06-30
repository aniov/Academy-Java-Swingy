package aniov.company.view.swingView;

import aniov.company.model.character.hero.Hero;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Marius on 6/29/2017.
 */
public class GameFrame extends JFrame {

    private List<Hero> heroes;
    private Hero currentHero;
    private SwingView swingView;

    private HeroSelectPanel heroSelectPanel;

    public GameFrame(SwingView swingView) throws HeadlessException {
        this.swingView = swingView;

        setSize(600, 400);
        setTitle("RPG Game @Academy+Plus");

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createHeroPanel();
    }

    public void openHeroSelectPanel() {
        heroSelectPanel.setVisible(true);
    }

    private void createHeroPanel() {

        heroes = swingView.getObservers().get(0).getAllHeroes();
        heroSelectPanel = new HeroSelectPanel(heroes);

        add(heroSelectPanel);
        heroSelectPanel.setVisible(false);
    }


}