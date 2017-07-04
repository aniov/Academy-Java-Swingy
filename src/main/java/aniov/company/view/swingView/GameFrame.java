package aniov.company.view.swingView;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.character.hero.Hero;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marius on 6/29/2017.
 */
@Getter
public class GameFrame extends JFrame {

    private Hero currentHero;

    private HeroSelectPanel heroSelectPanel;
    private CreateHeroPanel createHeroPanel;
    private GamePlayPanel gamePlayPanel;
    private ObserverOfTheView observer;

    public GameFrame(ObserverOfTheView observer) throws HeadlessException {
        this.observer = observer;

        setSize(900, 600);
        setTitle("RPG Game @Academy+Plus");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        openHeroSelectPanel();
        setVisible(true);
    }

    public void openHeroSelectPanel() {
        if (gamePlayPanel != null) {
            gamePlayPanel.setVisible(false);
            remove(gamePlayPanel);
        }
        if (createHeroPanel != null) {
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
        gamePlayPanel = new GamePlayPanel(this, currentHero);
        gamePlayPanel.setVisible(false);
    }

}