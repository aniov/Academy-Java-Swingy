package aniov.company.view.swingView;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import javax.swing.*;

/**
 * Created by Marius on 6/29/2017.
 */
@AllArgsConstructor
public class MainWindow extends JFrame {

    //@NonNull
    //private ObserverOfTheView observer;
    @NonNull
    private SwingView swingView;

    public void createMainWindow() {
        JButton b = new JButton("Enter RPG Game");
        b.setBounds(300, 100, 200, 30);

        add(b);
        setSize(800, 300);
        setLayout(null);//using no layout managers
        setResizable(false);
        setVisible(true);//making the frame visible

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b.addActionListener((event) -> {
            setVisible(false);
            this.dispose();

            GameFrame gameFrame = new GameFrame(swingView);
            gameFrame.openHeroSelectPanel();
        });
    }


}
