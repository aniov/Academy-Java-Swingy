package aniov.company.view.swingView;

import aniov.company.model.artifact.Artifact;
import aniov.company.model.character.hero.Hero;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by Marius on 6/30/2017.
 */

public class HeroSelectPanel extends JPanel {

    private GameFrame gameFrame;
    private List<Hero> heroes;
    private JScrollPane heroScrollPane;
    private JList<String> heroList;
    private JButton deleteButton;
    private JButton selectButton;
    private JButton createButton;
    private JScrollPane statsScrollPane;
    private JTextArea statsTextArea;
    private JLabel heroListLabel;
    private JLabel statsListLabel;
    private JButton exitButton;

    public HeroSelectPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        updateHeroes();
        initComponents();
    }

    private void selectButtonActionPerformed(ActionEvent e) {
        Integer selectedHeroIndex = heroList.getSelectedIndex();
        if (selectedHeroIndex >= 0) {
            Hero hero = getSelectedHero(selectedHeroIndex);
            if (hero != null) {
                gameFrame.openGamePlayPanel(hero);
            }
        }
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        gameFrame.setVisible(false);
        gameFrame.dispose();
        System.exit(0);
    }

    private void createButtonActionPerformed(ActionEvent e) {
        gameFrame.openCreateHeroPanel();
    }

    private void deleteButtonActionPerformed(ActionEvent e) {
        Integer selectedHeroIndex = heroList.getSelectedIndex();
        if (selectedHeroIndex >= 0) {
            heroList.clearSelection();
            gameFrame.getObserver().deleteHero(getSelectedHero(selectedHeroIndex));
            updateHeroes();
            heroList.setListData(getHeroesName());
            statsTextArea.setText("");
        }
    }

    private void heroListValueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            String stats = getStatsFromHero(heroList.getSelectedIndex());
            statsTextArea.setText(stats);
        }
    }

    private void updateHeroes() {
        heroes = gameFrame.getObserver().getAllHeroes();
        getHeroesName();
    }

    private String[] getHeroesName() {
        String[] heroesArray = new String[heroes.size()];

        for (int i = 0; i < heroes.size(); i++) {
            heroesArray[i] = heroes.get(i).getName();
        }
        return heroesArray;
    }

    private String getStatsFromHero(int selectedIndex) {

        Hero hero = heroes.get(selectedIndex);
        StringBuilder sb = new StringBuilder();
        sb.append("Class: " + hero.getHeroType() + "\nLevel: " + hero.getLevel() + "\nExperience: " + hero.getExperience()
                + "\nTotal stats:\n  Attack: " + hero.getTotalDefence() + "\n  Defence: " + hero.getTotalDefence()
                + "\n  Hit points: " + hero.getTotalHitPoints() + "\nArtifacts:\n");
        StringBuilder sArtifacts = new StringBuilder();
        for (Artifact artifact : hero.getArtifacts()) {
            sArtifacts.append("  " + artifact + "\n");
        }
        sb.append(sArtifacts);
        return sb.toString();
    }

    private Hero getSelectedHero(int selectedIndex) {
        return heroes.get(selectedIndex);
    }

    private void initComponents() {

        heroScrollPane = new JScrollPane();
        heroList = new JList<>();
        selectButton = new JButton();
        createButton = new JButton();
        deleteButton = new JButton();
        statsScrollPane = new JScrollPane();
        statsTextArea = new JTextArea();
        heroListLabel = new JLabel();
        statsListLabel = new JLabel();
        exitButton = new JButton();

        //======== heroScrollPane ========
        {
            //---- heroList ----
            heroList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            heroList.setListData(getHeroesName());
            heroList.setFont(new Font("Segoe UI", Font.BOLD, 20));
            heroList.addListSelectionListener(e -> heroListValueChanged(e));
            heroScrollPane.setViewportView(heroList);
        }

        //---- selectButton ----
        selectButton.setText("select");
        selectButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        selectButton.setIcon(new ImageIcon(getClass().getResource("/icons/select.png")));
        selectButton.addActionListener(e -> selectButtonActionPerformed(e));

        //---- createButton ----
        createButton.setText("new hero");
        createButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        createButton.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
        createButton.addActionListener(e -> createButtonActionPerformed(e));

        //---- deleteButton ----
        deleteButton.setText("delete");
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        deleteButton.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));
        deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));

        //======== statsScrollPane ========
        {

            //---- statsTextArea ----
            statsTextArea.setEditable(false);
            statsTextArea.setMargin(new Insets(5, 5, 5, 5));
            statsTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            statsScrollPane.setViewportView(statsTextArea);

        }

        //---- heroListLabel ----
        heroListLabel.setText("Hero list");
        heroListLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- statsListLabel ----
        statsListLabel.setText("Stats list");
        statsListLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- exitButton ----
        exitButton.setText("exit");
        exitButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        exitButton.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
        exitButton.addActionListener(e -> exitButtonActionPerformed(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(heroListLabel, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(heroScrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(214, 214, 214)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(statsListLabel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(statsScrollPane, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(selectButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addGap(85, 85, 85)
                                                .addComponent(exitButton)))
                                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(heroListLabel)
                                        .addComponent(statsListLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(heroScrollPane)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(statsScrollPane, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
                                        .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(76, Short.MAX_VALUE))
        );
    }


}
