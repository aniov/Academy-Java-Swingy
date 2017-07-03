package aniov.company.view.swingView;

import aniov.company.model.character.hero.HeroType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Marius Voina
 */
public class CreateHeroPanel extends JPanel {

    private GameFrame gameFrame;
    private JScrollPane scrollPane1;
    private JTextArea statsTextArea;
    private JLabel statsLabel;
    private JComboBox<String> heroTypeComboBox;
    private JTextField heroNameTextField;
    private JButton createButton;
    private JButton cancelButton;
    private JLabel heroTypelabel;
    private JLabel nameLabel;
    private JLabel errorLabel;
    private JPanel errorVerticalSpacer;

    public CreateHeroPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        initComponents();
    }

    private void heroTypeComboBoxActionPerformed(ActionEvent e) {
        String typeSelected = heroTypeComboBox.getSelectedItem().toString();
        statsTextArea.setText(getHeroTypeStats(typeSelected));
    }

    private void heroNameTextFieldKeyTyped(KeyEvent e) {

        if (isHeroNameValid()) {
            errorLabel.setText("valid name");
            errorLabel.setForeground(new Color(55, 148, 55));
        } else {
            errorLabel.setText("invalid name, only letters <min 3 - max 20>");
            errorLabel.setForeground(new Color(255, 51, 51));
        }
        errorLabel.setVisible(true);
        if (heroNameTextField.getText().isEmpty()) {
            errorLabel.setVisible(false);
        }
    }

    private void createButtonActionPerformed(ActionEvent e) {

        if (isHeroNameValid()) {
            String heroName = heroNameTextField.getText();
            String heroType = heroTypeComboBox.getSelectedItem().toString();
            gameFrame.getObserver().createNewHero(heroName, heroType);
            resetInputName();
            gameFrame.openHeroSelectPanel();
        } else  if (heroNameTextField.getText().isEmpty()) {
            errorLabel.setText("enter a name");
            errorLabel.setForeground(new Color(255, 51, 51));
            errorLabel.setVisible(true);
        }
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        resetInputName();
        gameFrame.openHeroSelectPanel();
    }

    private void initComponents() {

        scrollPane1 = new JScrollPane();
        statsTextArea = new JTextArea();
        statsLabel = new JLabel();
        heroTypeComboBox = new JComboBox<>();
        heroNameTextField = new JTextField();
        createButton = new JButton();
        cancelButton = new JButton();
        heroTypelabel = new JLabel();
        nameLabel = new JLabel();
        errorLabel = new JLabel();
        errorVerticalSpacer = new JPanel(null);

        //---- statsLabel ----
        statsLabel.setText("Stats");
        statsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- heroTypeComboBox ----
        heroTypeComboBox.setModel(new DefaultComboBoxModel(getHeroTypes()));
        heroTypeComboBox.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heroTypeComboBox.setToolTipText("Hero Type");
        heroTypeComboBox.addActionListener(e -> heroTypeComboBoxActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- statsTextArea ----
            statsTextArea.setEditable(false);
            statsTextArea.setBackground(new Color(204, 204, 204));
            String selectedHeroType = heroTypeComboBox.getSelectedItem().toString();
            statsTextArea.setText(getHeroTypeStats(selectedHeroType));
            statsTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            statsTextArea.setToolTipText("Hero stats at Level 1");
            statsTextArea.setMargin(new Insets(5, 5, 5, 5));
            scrollPane1.setViewportView(statsTextArea);
        }

        //---- heroNameTextField ----
        heroNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        heroNameTextField.setToolTipText("Only letters (min 3, max 20))");
        heroNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                heroNameTextFieldKeyTyped(e);
            }
        });

        //---- createButton ----
        createButton.setText("Create");
        createButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        createButton.addActionListener(e -> createButtonActionPerformed(e));

        //---- cancelButton ----
        cancelButton.setText("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));

        //---- heroTypelabel ----
        heroTypelabel.setText("Hero Class");
        heroTypelabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- nameLabel ----
        nameLabel.setText("Name");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- errorLabel ----
        errorLabel.setVisible(false);
        errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
                                                .addGap(92, 92, 92)
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(heroTypelabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(385, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(nameLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(heroTypeComboBox, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(heroNameTextField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(errorVerticalSpacer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(70, 70, 70)))
                                                                .addGap(0, 87, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(statsLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(814, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(statsLabel)
                                        .addComponent(heroTypelabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(heroTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(238, 238, 238)
                                                .addComponent(nameLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(heroNameTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(errorVerticalSpacer, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                                .addGap(59, 59, 59))))
        );
    }

    private HeroType[] getHeroTypes() {
        return gameFrame.getObserver().getHeroTypes();
    }

    private boolean isHeroNameValid() {
        String heroName = heroNameTextField.getText();
        return gameFrame.getObserver().isHeroNameValid(heroName);
    }

    private String getHeroTypeStats(String type) {
        HeroType[] types = getHeroTypes();
        StringBuilder sb = new StringBuilder();
        for (HeroType heroType : types) {
            if (heroType.name().equalsIgnoreCase(type)) {
                sb.append("Class: " + heroType.name() + "\nLevel: 1\nStats:\n  Attack: " + heroType.getAttack()
                        + "\n  Defence: " + heroType.getDefence() + "\n  Hit points: " + heroType.getHitPoints());
            }
        }
        return sb.toString();
    }

    private void resetInputName() {
        heroNameTextField.setText("");
        errorLabel.setVisible(false);
        errorLabel.setText("");
    }

}
