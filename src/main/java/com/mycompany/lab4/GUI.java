/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author nsoko
 */
public class GUI extends JFrame {

    public GUI() {
        super("Лабораторная работа 4");
//        BackgroundPanel backgroundPanel = new BackgroundPanel("background_start.png");
//        setContentPane(backgroundPanel);
        ParticlePanel particlePanel = new ParticlePanel();
        setContentPane(particlePanel);
        JLabel welcomeText = new JLabel("<html><div style='text-align:center;width:600px;'>Добро пожаловать в учетную систему магазина волшебных палочек \"Оливандеры\"</div></html>");
        JButton startButton = new JButton("Приступить к работе");
        JButton exitButton = new JButton("Выход");
        startButton.addActionListener((ActionEvent e) -> {
            //че делать\
            startProgram(this);
        });
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        GUIDesign.startFrameDesign(particlePanel, welcomeText, startButton, exitButton);
        setBounds(150, 50, 1280, 720);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void startProgram(JFrame currentFrame) {
        // Закрыть текущее окно
        currentFrame.dispose();

        // Создать новое окно
        JFrame frame = new JFrame("Учетная система магазина волшебных палочек");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Центрирование окна

        // Создать JPanel для размещения компонентов
//        JPanel panel = new JPanel();
        BackgroundPanel panel = new BackgroundPanel("background_start.png");
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        // Создать JLabel
        JLabel questionLabel = new JLabel("Что Вы хотите сделать?");
//        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Создать кнопки
        JButton button1 = new JButton("Просмотреть информацию о...");
        JButton button2 = new JButton("Выставить палочку на продажу");
        JButton button3 = new JButton("Продать палочку");
        JButton button4 = new JButton("Заказать поставку компонентов");
        JButton button5 = new JButton("Начать с чистого листа");
        JButton button6 = new JButton("Выход");

        // Установить ActionListener для каждой кнопки (пустой)
        button1.addActionListener(e -> {
        });
        button2.addActionListener(e -> {
        });
        button3.addActionListener(e -> {
        });
        button4.addActionListener(e -> {
        });
        button5.addActionListener(e -> {
        });
        button6.addActionListener(e -> {
        });

        // Установить дизайн панели
        GUIDesign.setPanelDesign(frame, panel, questionLabel, button1, button2, button3, button4, button5, button6);
        // Отобразить окно
        frame.setVisible(true);
    }

}
