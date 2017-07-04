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


public class GamePlayPanel extends JPanel {

    private GameFrame gameFrame;
    @Setter
    private Hero hero;
    private String[][] map;

    private JPanel panel2;
    private JScrollPane scrollPane;
    private JTextArea statsTextArea;
    private JLabel heroLabel;
    private JLabel infoLabel;
    private JLabel infoLabel2;
    private JButton fightButton;
    private JButton runButton;
    private JButton keepButton;
    private JButton backButton;
    private JButton quitButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JButton dropButton;
    private JScrollPane scrollPane2;
    private JPanel mapPanel;

    private Object obj = new Object();
    private boolean wantToFight;
    private boolean tryToRun;
    private boolean keepArtifact;
    private boolean dropArtifact;

    public GamePlayPanel(GameFrame gameFrame, Hero hero) {
        this.gameFrame = gameFrame;
        this.hero = hero;
        gameFrame.getObserver().createMapAndStartGame(hero);
        map = gameFrame.getObserver().getMap();
        initComponents();
    }

    private void quitButtonActionPerformed(ActionEvent e) {
        gameFrame.openHeroSelectPanel();
    }

    private void upButtonActionPerformed(ActionEvent e) {
        Thread t = new Thread(() -> {
            clearInfoLabels();
            gameFrame.getObserver().moveHeroUp();
            repaintUi();
        });
        t.start();
    }

    private void rightButtonActionPerformed(ActionEvent e) {
        Thread t = new Thread(() -> {
            clearInfoLabels();
            gameFrame.getObserver().moveHeroRight();
            repaintUi();
        });
        t.start();
    }

    private void leftButtonActionPerformed(ActionEvent e) {
        Thread t = new Thread(() -> {
            clearInfoLabels();
            gameFrame.getObserver().moveHeroLeft();
            repaintUi();
        });
        t.start();
    }

    private void downButtonActionPerformed(ActionEvent e) {
        Thread t = new Thread(() -> {
            clearInfoLabels();
            gameFrame.getObserver().moveHeroDown();
            repaintUi();
        });
        t.start();
    }

    private void fightButtonActionPerformed(ActionEvent e) {
        fightButton.setEnabled(false);
        runButton.setEnabled(false);
        wantToFight = true;
        synchronized (obj) {
            obj.notify();
        }
    }

    private void runButtonActionPerformed(ActionEvent e) {
        fightButton.setEnabled(false);
        runButton.setEnabled(false);
        tryToRun = true;
        synchronized (obj) {
            obj.notify();
        }
    }

    private void backButtonActionPerformed(ActionEvent e) {
        gameFrame.openHeroSelectPanel();
    }

    private void keepButtonActionPerformed(ActionEvent e) {
        keepButton.setEnabled(false);
        dropButton.setEnabled(false);
        keepArtifact = true;
        synchronized (obj) {
            obj.notify();
        }
    }

    private void dropButtonActionPerformed(ActionEvent e) {
        keepButton.setEnabled(false);
        dropButton.setEnabled(false);
        dropArtifact = true;
        synchronized (obj) {
            obj.notify();
        }
    }

    private void createUiMap() {
        mapPanel.removeAll();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                JLabel label = new JLabel(" ",JLabel.CENTER);
                label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                label.setPreferredSize(new Dimension(40, 40));

                label.setOpaque(true);
                if (map[i][j].equals("H")) { // hero
                    label.setBackground(Color.GREEN);
                    label.setText("H");
                } else if (map[i][j].equals("*")){ //hero passed
                    label.setBackground(Color.ORANGE);
                } else if (map[i][j].equals("?")){ //not discovered yet
                    label.setBackground(Color.LIGHT_GRAY);
                }
                mapPanel.add(label);
            }
        }
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
        map = gameFrame.getObserver().getMap();
        createUiMap();
        statsTextArea.setText(getHeroStats());
    }

    boolean playerWantToFight(String villain) {

        fightButton.setEnabled(true);
        runButton.setEnabled(true);
        infoLabel.setText("Encounter a: " + villain);
        deactivateMoveButtons();

        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        activateMoveButtons();
        if (wantToFight) {
            wantToFight = false;
            return true;
        } else if (tryToRun) {
            tryToRun = false;
            return false;
        }
        return true;
    }

    boolean keepArtifact(String artifact) {

        keepButton.setEnabled(true);
        dropButton.setEnabled(true);
        infoLabel2.setText("Keep ? " + artifact);
        deactivateMoveButtons();
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        activateMoveButtons();
        infoLabel2.setText(" ");
        infoLabel.setText(" ");
        if (keepArtifact) {
            keepArtifact = false;
            return true;
        } else if (dropArtifact) {
            dropArtifact = false;
            return false;
        }
        return true;
    }

    void heroWonOnMap() {
        infoLabel.setText("You WON !");
        deactivateButtons();
        quitButton.setEnabled(false);
        backButton.setEnabled(true);
    }

    void heroWonTheFight() {
        infoLabel.setText("You won the fight");
        fightButton.setEnabled(false);
        runButton.setEnabled(false);
        activateMoveButtons();
    }

    void heroLostTheFight() {
        infoLabel.setText("You lost the fight. You are dead.");
        deactivateButtons();
        quitButton.setEnabled(false);
        backButton.setEnabled(true);
    }

    void heroCouldNotRun() {
        infoLabel2.setText("You could Not Escape");
    }

    void heroEscaped() {
        infoLabel.setText("You are Lucky, you Escaped");
    }

    private void deactivateButtons() {
        fightButton.setEnabled(false);
        runButton.setEnabled(false);
        keepButton.setEnabled(false);
        dropButton.setEnabled(false);
        deactivateMoveButtons();
    }

    private void deactivateMoveButtons() {
        upButton.setEnabled(false);
        downButton.setEnabled(false);
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
    }

    private void activateMoveButtons() {
        upButton.setEnabled(true);
        downButton.setEnabled(true);
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);
    }

    private void clearInfoLabels() {
        infoLabel.setText(" ");
        infoLabel2.setText(" ");
    }

    private void initComponents() {

        panel2 = new JPanel();
        scrollPane = new JScrollPane();
        statsTextArea = new JTextArea();
        heroLabel = new JLabel();
        infoLabel = new JLabel();
        infoLabel2 = new JLabel();
        fightButton = new JButton();
        runButton = new JButton();
        keepButton = new JButton();
        backButton = new JButton();
        quitButton = new JButton();
        upButton = new JButton();
        leftButton = new JButton();
        rightButton = new JButton();
        downButton = new JButton();
        dropButton = new JButton();
        scrollPane2 = new JScrollPane();
        mapPanel = new JPanel();


        //======== scrollPane2 ========
        {
            //======== mapPanel ========
            {
                mapPanel.setLayout(new GridLayout(map.length, map.length));
            }
            scrollPane2.setViewportView(mapPanel);
            createUiMap();
        }

        {

            //======== scrollPane1 ========
            {

                //---- statsTextArea ----
                statsTextArea.setEditable(false);
                statsTextArea.setFont(new Font("Dialog", Font.PLAIN, 13));
                statsTextArea.setText(getHeroStats());
                statsTextArea.setMargin(new Insets(1, 5, 1, 5));
                scrollPane.setViewportView(statsTextArea);
            }

            //---- heroLabel ----
            heroLabel.setText(hero.getName());
            heroLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

            //---- infoLabel ----
            infoLabel.setText(" ");

            //---- infoLabel2 ----
            infoLabel2.setText(" ");

            //---- fightButton ----
            fightButton.setText("fight");
            fightButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\svg-6-16.png"));
            fightButton.setToolTipText("fight with villain");
            fightButton.setEnabled(false);
            fightButton.addActionListener(e -> fightButtonActionPerformed(e));

            //---- runButton ----
            runButton.setText("run  ");
            runButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\svg-5-16.png"));
            runButton.setToolTipText("try to run, you have 50% chance");
            runButton.setEnabled(false);
            runButton.addActionListener(e -> runButtonActionPerformed(e));

            //---- keepButton ----
            keepButton.setText("keep");
            keepButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\svg-2-16.png"));
            keepButton.setToolTipText("keep the artifact");
            keepButton.setEnabled(false);
            keepButton.addActionListener(e -> keepButtonActionPerformed(e));

            //---- dropButton ----
            dropButton.setText("drop");
            dropButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\svg-8-16.png"));
            dropButton.setToolTipText("droop the artifact");
            dropButton.setEnabled(false);
            dropButton.addActionListener(e -> dropButtonActionPerformed(e));

            //---- backButton ----
            backButton.setText("Back");
            backButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\011_yes-16.png"));
            backButton.setToolTipText("back to previous window");
            backButton.setEnabled(false);
            backButton.addActionListener(e -> backButtonActionPerformed(e));

            //---- upButton ----
            upButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\291-32.png"));
            upButton.addActionListener(e -> upButtonActionPerformed(e));

            //---- leftButton ----
            leftButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\288-32.png"));
            leftButton.addActionListener(e -> leftButtonActionPerformed(e));

            //---- rightButton ----
            rightButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\290-32.png"));
            rightButton.addActionListener(e -> rightButtonActionPerformed(e));

            //---- downButton ----
            downButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\289-32.png"));
            downButton.addActionListener(e -> downButtonActionPerformed(e));

            //---- quitButton ----
            quitButton.setText("Quit");
            quitButton.setIcon(new ImageIcon("C:\\Users\\Marius\\Desktop\\Swingy-Icons\\07_minus-16.png"));
            quitButton.setToolTipText("quit current game");
            quitButton.addActionListener(e -> quitButtonActionPerformed(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup()
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addGroup(panel2Layout.createParallelGroup()
                                                            .addGroup(panel2Layout.createSequentialGroup()
                                                                    .addComponent(fightButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                                                                    .addComponent(keepButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(scrollPane)
                                                            .addComponent(heroLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(infoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(infoLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(panel2Layout.createSequentialGroup()
                                                                    .addComponent(quitButton)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                                                                    .addComponent(backButton))
                                                            .addGroup(panel2Layout.createSequentialGroup()
                                                                    .addComponent(runButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                                                                    .addComponent(dropButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(panel2Layout.createSequentialGroup()
                                                                    .addGap(69, 69, 69)
                                                                    .addComponent(leftButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(31, 31, 31)
                                                                    .addComponent(rightButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(0, 106, Short.MAX_VALUE))))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                    .addGap(115, 115, 115)
                                                    .addComponent(upButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 135, Short.MAX_VALUE)))
                                    .addContainerGap())
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(117, 117, 117)
                                    .addComponent(downButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(139, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(heroLabel)
                                    .addGap(7, 7, 7)
                                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(infoLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(infoLabel2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(fightButton)
                                            .addComponent(keepButton))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel2Layout.createParallelGroup()
                                            .addComponent(dropButton)
                                            .addComponent(runButton))
                                    .addGap(7, 7, 7)
                                    .addComponent(upButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel2Layout.createParallelGroup()
                                            .addComponent(leftButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rightButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(downButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel2Layout.createParallelGroup()
                                            .addComponent(backButton)
                                            .addComponent(quitButton))
                                    .addContainerGap(27, Short.MAX_VALUE))
            );
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 588, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                                .addContainerGap())
        );
    }

}
