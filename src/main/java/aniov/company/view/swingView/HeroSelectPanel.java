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

/*    @Override
    public void addNotify() {
        super.addNotify();
        updateHeroes();
    }

    @Override
    public boolean isVisible() {
        updateHeroes();
        return super.isVisible();

    }*/

    public HeroSelectPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        updateHeroes();
        initComponents();
    }

    private void selectButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void createButtonActionPerformed(ActionEvent e) {
        gameFrame.openCreateHeroPanel();
    }

    private void deleteButtonActionPerformed(ActionEvent e) {
        Integer selectedHeroIndex = heroList.getSelectedIndex();
        if (selectedHeroIndex >= 0) {
            heroList.clearSelection();
            gameFrame.getSwingView().getObservers().get(0).deleteHero(getSelectedHero(selectedHeroIndex));
            updateHeroes();
            heroList.setListData(getHeroesName());
        }
    }

    private void heroListValueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            String stats = getStatsFromHero(heroList.getSelectedIndex());
            statsTextArea.setText(stats);
        }
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

        //======== heroScrollPane ========
        {
            //---- heroList ----
            heroList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            heroList.setListData(getHeroesName());
            heroList.addListSelectionListener(e -> heroListValueChanged(e));
            heroScrollPane.setViewportView(heroList);
        }

        //---- selectButton ----
        selectButton.setText("select");
        selectButton.addActionListener(e -> selectButtonActionPerformed(e));

        //---- createButton ----
        createButton.setText("new hero");
        createButton.addActionListener(e -> createButtonActionPerformed(e));

        //---- deleteButton ----
        deleteButton.setText("delete");
        deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));

        //======== statsScrollPane ========
        {

            //---- statsTextArea ----
            statsTextArea.setEditable(false);
            statsTextArea.setMargin(new Insets(5, 5, 5, 5));
            statsScrollPane.setViewportView(statsTextArea);

        }

        //---- heroListLabel ----
        heroListLabel.setText("Hero list");

        //---- statsListLabel ----
        statsListLabel.setText("Stats list");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(deleteButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                                .addComponent(selectButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(heroListLabel)
                                                        .addComponent(heroScrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(82, 82, 82)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(statsListLabel)
                                                        .addComponent(statsScrollPane, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))))
                                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(heroListLabel)
                                        .addComponent(statsListLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(heroScrollPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(statsScrollPane))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(createButton)
                                        .addComponent(selectButton)
                                        .addComponent(deleteButton))
                                .addGap(26, 26, 26))
        );
    }

    private void updateHeroes() {
        heroes = gameFrame.getSwingView().getObservers().get(0).getAllHeroes();
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
            sArtifacts.append("  " + artifact);
        }
        sb.append(sArtifacts);
        return sb.toString();
    }

    private Hero getSelectedHero(int selectedIndex) {
        return heroes.get(selectedIndex);
    }
}
