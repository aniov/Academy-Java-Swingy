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
            gameFrame.getSwingView().getObservers().get(0).createNewHero(heroName, heroType);
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

        //---- heroTypeComboBox ----
        heroTypeComboBox.setModel(new DefaultComboBoxModel(getHeroTypes()));
        heroTypeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        heroTypeComboBox.setToolTipText("Hero Type");
        heroTypeComboBox.addActionListener(e -> heroTypeComboBoxActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- statsTextArea ----
            statsTextArea.setEditable(false);
            statsTextArea.setBackground(new Color(204, 204, 204));
            String selectedHeroType = heroTypeComboBox.getSelectedItem().toString();
            statsTextArea.setText(getHeroTypeStats(selectedHeroType));
            statsTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            statsTextArea.setToolTipText("Hero stats at Level 1");
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
        createButton.addActionListener(e -> createButtonActionPerformed(e));

        //---- cancelButton ----
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));

        //---- heroTypelabel ----
        heroTypelabel.setText("Hero Class");

        //---- nameLabel ----
        nameLabel.setText("Name");

        //---- errorLabel ----
        errorLabel.setVisible(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(statsLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(514, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(172, 172, 172)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(heroTypeComboBox, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(heroTypelabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 99, Short.MAX_VALUE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(heroNameTextField, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                                                                .addGap(22, 22, 22)
                                                                                .addComponent(errorVerticalSpacer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(statsLabel)
                                                        .addComponent(heroTypelabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(heroTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(190, 190, 190)
                                                .addComponent(nameLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(heroNameTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(errorVerticalSpacer, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(errorLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(72, Short.MAX_VALUE))
        );
    }

    private HeroType[] getHeroTypes() {
        return gameFrame.getSwingView().getObservers().get(0).getHeroTypes();
    }

    private boolean isHeroNameValid() {
        String heroName = heroNameTextField.getText();
        return gameFrame.getSwingView().getObservers().get(0).isHeroNameValid(heroName);
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
