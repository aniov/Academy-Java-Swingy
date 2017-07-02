package aniov.company.view.swingView;

import aniov.company.model.character.hero.Hero;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marius on 6/29/2017.
 */
@Setter
@Getter
public class GameFrame extends JFrame {

    private Hero currentHero;
    private SwingView swingView;

    private HeroSelectPanel heroSelectPanel;
    private CreateHeroPanel createHeroPanel;
    private GamePlayFrame gamePlayPanel;

    public GameFrame(SwingView swingView) throws HeadlessException {
        this.swingView = swingView;

        setSize(900, 600);
        setTitle("RPG Game @Academy+Plus");

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createSelectHeroPanel();
    }

    public void openHeroSelectPanel() {
        if (gamePlayPanel != null) {
            gamePlayPanel.setVisible(false);
            remove(gamePlayPanel);
        } if (createHeroPanel != null ){
            createHeroPanel.setVisible(false);
            remove(createHeroPanel);
        }

        createSelectHeroPanel();
        add(heroSelectPanel);
        heroSelectPanel.setVisible(true);
    }

    public void openCreateHeroPanel() {
        if (gamePlayPanel != null) {
            gamePlayPanel.setVisible(false);
            remove(gamePlayPanel);
        }
        heroSelectPanel.setVisible(false);
        createNewHeroPanel();

        add(createHeroPanel);
        createHeroPanel.setVisible(true);
    }

    public void openGamePlayPanel(Hero hero) {
        heroSelectPanel.setVisible(false);
        remove(heroSelectPanel);
        currentHero = hero;
        createGamePlayPanel();

        add(gamePlayPanel);
        gamePlayPanel.setVisible(true);
    }

    private void createSelectHeroPanel() {
        heroSelectPanel = new HeroSelectPanel(this);
        heroSelectPanel.setVisible(false);
    }

    private void createNewHeroPanel() {
        createHeroPanel = new CreateHeroPanel(this);
        createHeroPanel.setVisible(false);

    }

    private void createGamePlayPanel() {
        gamePlayPanel = new GamePlayFrame(this, currentHero);
        gamePlayPanel.setVisible(false);
    }

}