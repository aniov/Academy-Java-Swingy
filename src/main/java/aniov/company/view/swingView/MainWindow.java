package aniov.company.view.swingView;

import aniov.company.controller.ObserverOfTheView;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

/**
 * Created by Marius on 6/29/2017.
 */
@RequiredArgsConstructor
public class MainWindow extends JFrame {

    @NonNull
    private ObserverOfTheView observer;
    @Getter
    private GameFrame gameFrame;

    public void createMainWindow() {
        JButton b = new JButton("Enter RPG Game");
        b.setBounds(300, 100, 200, 90);

        add(b);
        setSize(800, 300);
        setLayout(null);//using no layout managers
        setResizable(false);
        setVisible(true);//making the frame visible

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b.addActionListener((event) -> {
            setVisible(false);
            this.dispose();


            gameFrame = new GameFrame(observer);
           // gameFrame.openHeroSelectPanel();

        });
    }

}
