/*
 * Created by JFormDesigner on Sat Jul 01 21:56:08 EEST 2017
 */

package aniov.company.view.swingView;

import aniov.company.model.artifact.Artifact;
import aniov.company.model.character.hero.Hero;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Marius Voina
 */
public class GamePlayFrame extends JPanel {

    private GameFrame gameFrame;
    @Setter
    private Hero hero;
    private String[][] map;

    private JPanel mapPanel;
    private JPanel actionPanel;
    private JScrollPane scrollPane1;
    private JTextArea statsTextArea;
    private JLabel heroLabel;
    private JLabel infoLabel;
    private JButton fightButton;
    private JButton runButton;
    private JButton upButton;
    private JPanel vSpacer1;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JButton quitButton;

    public GamePlayFrame(GameFrame gameFrame, Hero hero) {
        this.gameFrame = gameFrame;
        this.hero = hero;
        gameFrame.getSwingView().getObservers().get(0).createMapAndStartGame(hero);
        map = gameFrame.getSwingView().getObservers().get(0).getMap();
        initComponents();
    }

    private void quitButtonActionPerformed(ActionEvent e) {
        gameFrame.openHeroSelectPanel();
    }

    private void upButtonActionPerformed(ActionEvent e) {
        gameFrame.getSwingView().getObservers().get(0).moveHeroUp();
        repaintUi();
    }

    private void rightButtonActionPerformed(ActionEvent e) {
        gameFrame.getSwingView().getObservers().get(0).moveHeroRight();
        repaintUi();
    }

    private void leftButtonActionPerformed(ActionEvent e) {
        gameFrame.getSwingView().getObservers().get(0).moveHeroLeft();
        repaintUi();
    }

    private void downButtonActionPerformed(ActionEvent e) {
        gameFrame.getSwingView().getObservers().get(0).moveHeroDown();
        repaintUi();
    }

    private boolean fightButtonActionPerformed(ActionEvent e) {
        fightButton.setVisible(false);
        runButton.setVisible(false);
        return true;
    }

    private void runButtonActionPerformed(ActionEvent e) {
        fightButton.setVisible(false);
        runButton.setVisible(false);
    }

    private void initComponents() {

        mapPanel = new JPanel();
        actionPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        statsTextArea = new JTextArea();
        heroLabel = new JLabel();
        infoLabel = new JLabel();
        fightButton = new JButton();
        runButton = new JButton();
        upButton = new JButton();
        vSpacer1 = new JPanel(null);
        leftButton = new JButton();
        rightButton = new JButton();
        downButton = new JButton();
        quitButton = new JButton();

        //======== mapPanel ========
        {
            mapPanel.setLayout(new GridLayout(map.length, map.length));
            createUiMap();
        }

        //======== actionPanel ========
        {

            //======== scrollPane1 ========
            {

                //---- statsTextArea ----
                statsTextArea.setEditable(false);
                statsTextArea.setFont(new Font("Dialog", Font.PLAIN, 10));
                statsTextArea.setText(getHeroStats());
                statsTextArea.setMargin(new Insets(1, 5, 1, 5));
                scrollPane1.setViewportView(statsTextArea);
            }

            //---- heroLabel ----
            heroLabel.setText(hero.getName());

            //---- infoLabel ----
            infoLabel.setText("text");

            //---- fightButton ----
            fightButton.setText("fig");
            fightButton.addActionListener(e -> fightButtonActionPerformed(e));
            fightButton.setVisible(false);

            //---- runButton ----
            runButton.setText("run");
            runButton.addActionListener(e -> runButtonActionPerformed(e));
            fightButton.setVisible(false);

            //---- upButton ----
            upButton.setText("x");
            upButton.addActionListener(e -> upButtonActionPerformed(e));

            //---- leftButton ----
            leftButton.setText("x");
            leftButton.addActionListener(e -> leftButtonActionPerformed(e));

            //---- rightButton ----
            rightButton.setText("x");
            rightButton.addActionListener(e -> rightButtonActionPerformed(e));

            //---- downButton ----
            downButton.setText("x");
            downButton.addActionListener(e -> downButtonActionPerformed(e));

            //---- quitButton ----
            quitButton.setText("quit");
            quitButton.addActionListener(e -> quitButtonActionPerformed(e));

            GroupLayout actionPanelLayout = new GroupLayout(actionPanel);
            actionPanel.setLayout(actionPanelLayout);
            actionPanelLayout.setHorizontalGroup(
                    actionPanelLayout.createParallelGroup()
                            .addGroup(actionPanelLayout.createSequentialGroup()
                                    .addGroup(actionPanelLayout.createParallelGroup()
                                            .addGroup(actionPanelLayout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addGroup(actionPanelLayout.createParallelGroup()
                                                            .addComponent(scrollPane1)
                                                            .addComponent(heroLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(infoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(actionPanelLayout.createSequentialGroup()
                                                                    .addComponent(vSpacer1, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addGroup(actionPanelLayout.createParallelGroup()
                                                                            .addComponent(fightButton)
                                                                            .addComponent(leftButton))
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                                                    .addGroup(actionPanelLayout.createParallelGroup()
                                                                            .addComponent(rightButton)
                                                                            .addComponent(runButton)))))
                                            .addGroup(actionPanelLayout.createSequentialGroup()
                                                    .addGroup(actionPanelLayout.createParallelGroup()
                                                            .addGroup(actionPanelLayout.createSequentialGroup()
                                                                    .addGap(58, 58, 58)
                                                                    .addGroup(actionPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                            .addComponent(downButton)
                                                                            .addComponent(upButton)))
                                                            .addGroup(actionPanelLayout.createSequentialGroup()
                                                                    .addContainerGap()
                                                                    .addComponent(quitButton)))
                                                    .addGap(0, 71, Short.MAX_VALUE)))
                                    .addContainerGap())
            );
            actionPanelLayout.setVerticalGroup(
                    actionPanelLayout.createParallelGroup()
                            .addGroup(actionPanelLayout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(heroLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(actionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(fightButton)
                                            .addComponent(runButton)
                                            .addComponent(vSpacer1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(upButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(actionPanelLayout.createParallelGroup()
                                            .addComponent(leftButton)
                                            .addComponent(rightButton))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(downButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(quitButton)
                                    .addContainerGap())
            );
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mapPanel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(mapPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(actionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void createUiMap() {
        mapPanel.removeAll();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                JLabel label = new JLabel(map[i][j], JLabel.CENTER);
                label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                label.setPreferredSize(new Dimension(20, 20));
                mapPanel.add(label);
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");

        mapPanel.updateUI();
    }

    private String getHeroStats() {

        StringBuilder sb = new StringBuilder();
        sb.append("Class: " + hero.getHeroType() + "\nLevel: " + hero.getLevel() + "\nExperience: " + hero.getExperience()
                + "\nTotal stats:\n  Attack: " + hero.getTotalDefence() + "\n  Defence: " + hero.getTotalDefence()
                + "\n  Hit points: " + hero.getTotalHitPoints() + "\nArtifacts:\n");
        StringBuilder sArtifacts = new StringBuilder();
        for (Artifact artifact : hero.getArtifacts()) {
            sArtifacts.append("  " + artifact + "\n");
        }
        Point heroCoords = getHeroCoords();
        sb.append(sArtifacts);
        sb.append("Coords:\n" + "  x: " + heroCoords.x + "; y: " + heroCoords.y + "; map size: " + map.length + "x" + map.length);

        return sb.toString();
    }

    private Point getHeroCoords() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].equalsIgnoreCase("H")) {
                    return new Point(i, j);
                }
            }
        }
        return new Point(0, 0);
    }

    private void repaintUi() {
        map = gameFrame.getSwingView().getObservers().get(0).getMap();
        createUiMap();
        statsTextArea.setText(getHeroStats());

    }

    public boolean wantToFight(String villain) {
        fightButton.setVisible(true);
        runButton.setVisible(true);
        infoLabel.setText("Ecounter " + villain);

        return true;
    }

}
