package aniov.company.view.swingView;

import aniov.company.model.character.hero.Hero;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by Marius on 6/30/2017.
 */

public class HeroSelectPanel extends JPanel {

    @Setter
    private List<Hero> heroes;
    private JScrollPane heroScrollPane;
    private JList<String> heroList;
    private JButton selectButton;
    private JButton createButton;
    private JScrollPane statsScrollPane;
    private JTextArea statsTextArea;
    private JLabel heroListLabel;
    private JLabel statsListLabel;

    public HeroSelectPanel(List<Hero> heroes) {
        this.heroes = heroes;
        initComponents();
    }

    private void selectButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void createButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void heroListValueChanged(ListSelectionEvent e) {

        if (! e.getValueIsAdjusting()) {
            System.out.println(heroList.getSelectedValue() + "index: " + heroList.getSelectedIndex());
        }
    }

    private void initComponents() {

        heroScrollPane = new JScrollPane();
        heroList = new JList<>();
        selectButton = new JButton();
        createButton = new JButton();
        statsScrollPane = new JScrollPane();
        statsTextArea = new JTextArea();
        heroListLabel = new JLabel();
        statsListLabel = new JLabel();

        //======== heroScrollPane ========
        {
            //---- heroList ----
            String[] heroNames = getHeroesName();
            heroList.setListData(heroNames);
            heroList.addListSelectionListener(e -> heroListValueChanged(e));
            heroScrollPane.setViewportView(heroList);
        }

        //---- selectButton ----
        selectButton.setText("select");
        selectButton.addActionListener(e -> selectButtonActionPerformed(e));

        //---- createButton ----
        createButton.setText("new hero");
        createButton.addActionListener(e -> createButtonActionPerformed(e));

        //======== statsScrollPane ========
        {

            //---- statsTextArea ----
            statsTextArea.setText("Demo Text");
            statsTextArea.setEditable(false);
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
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(172, Short.MAX_VALUE)
                                                .addComponent(selectButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
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
                                        .addComponent(selectButton))
                                .addGap(26, 26, 26))
        );
    }

    private String[] getHeroesName() {
        String[] heroesArray = new String[heroes.size()];

        for (int i = 0; i < heroes.size(); i++) {
            heroesArray[i] = heroes.get(i).getName();
        }
        return heroesArray;
    }
}
