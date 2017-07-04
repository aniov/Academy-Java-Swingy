package aniov.company.view.swingView;

import aniov.company.controller.ObserverOfTheView;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marius on 6/29/2017.
 */
@RequiredArgsConstructor
public class MainWindow extends JFrame {

    @NonNull
    private ObserverOfTheView observer;
    @Getter
    private GameFrame gameFrame;
    private JButton enterButton;

    public void createMainWindow() {

        initComponents();

        setSize(800, 300);
        setLayout(null);//using no layout managers
        setResizable(false);
        setVisible(true);//making the frame visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(enterButton);
    }

    private void initComponents(){
        enterButton = new JButton("Enter RPG Game");
        enterButton.setBounds(250, 100, 300, 100);
        enterButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        enterButton.setFocusPainted(false);
        enterButton.setIcon(new ImageIcon(getClass().getResource("/icons/enter.png")));
        enterButton.addActionListener((e) -> enterButtonActionPerformed(e));


    }

    private void enterButtonActionPerformed(ActionEvent e) {
        setVisible(false);
        this.dispose();

        gameFrame = new GameFrame(observer);
    }

}
