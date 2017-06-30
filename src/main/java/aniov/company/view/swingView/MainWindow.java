package aniov.company.view.swingView;

import lombok.AllArgsConstructor;

import javax.swing.*;

/**
 * Created by Marius on 6/29/2017.
 */
@AllArgsConstructor
public class MainWindow extends JFrame {

    private SwingView swingView;

    public void createMainWindow() {
        JButton b = new JButton("Enter RPG Game");
        b.setBounds(300, 200, 200, 30);

        add(b);
        setSize(800, 300);
        setLayout(null);//using no layout managers
        setVisible(true);//making the frame visible

        b.addActionListener((event) -> {
            setVisible(false);
            this.dispose();

            swingView.enterHeroInterface();
        });
    }


}
