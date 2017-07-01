package aniov.company.view.swingView;

import aniov.company.model.character.hero.Hero;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Marius on 6/29/2017.
 */
@Setter
@Getter
public class GameFrame extends JFrame {

    //private List<Hero> heroes;
    private Hero currentHero;
    private SwingView swingView;

    private HeroSelectPanel heroSelectPanel;
    private CreateHeroPanel createHeroPanel;

    public GameFrame(SwingView swingView) throws HeadlessException {
        this.swingView = swingView;

        setSize(600, 400);
        setTitle("RPG Game @Academy+Plus");

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createSelectHeroPanel();
        createNewHeroPanel();
    }

    public void openHeroSelectPanel() {
        createHeroPanel.setVisible(false);
        remove(createHeroPanel);

        add(heroSelectPanel);
        heroSelectPanel.setVisible(true);
    }

    public void openCreateHeroPanel() {
        heroSelectPanel.setVisible(false);
        remove(heroSelectPanel);

        add(createHeroPanel);
        createHeroPanel.setVisible(true);
    }

    private void createSelectHeroPanel() {
        heroSelectPanel = new HeroSelectPanel(this);
        heroSelectPanel.setVisible(false);
    }

    private void createNewHeroPanel() {
        createHeroPanel = new CreateHeroPanel(this);
        createHeroPanel.setVisible(false);

    }

}