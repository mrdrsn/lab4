/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author nsoko
 */
public class GUIDesign {

    public static void startFrameDesign(JPanel panel, JLabel welcomeText, JButton button1, JButton button2) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font customFont2 = CustomFontLoader.loadCustomFont(60, "stezhok.otf");
        welcomeText.setFont(customFont2);
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(70, 20, 20, 20);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        panel.add(welcomeText, gbc);

        button1.setForeground(Color.decode("#062935"));
        button1.setOpaque(false);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);

        button2.setForeground(Color.decode("#062935"));
        button2.setOpaque(false);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);

        Font customFont1 = CustomFontLoader.loadCustomFont(20, "coolvetica.otf");
        button1.setFont(customFont1);
        button2.setFont(customFont1);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 20, 20, 10);
        gbc.weightx = 1.0;
        panel.add(button1, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 20, 20);
        gbc.weightx = 1.0;
        panel.add(button2, gbc);
    }

    
   
    public static void setPanelDesign(JFrame frame, JPanel panel, JLabel questionLabel,
                                     JButton button1, JButton button2, JButton button3, JButton button4, JButton button5, JButton button6) {
        // Настройка GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        Font customFont2 = CustomFontLoader.loadCustomFont(60, "stezhok.otf");
        questionLabel.setFont(customFont2);
        
        // Добавить JLabel "Что Вы хотите сделать?"
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Занимает 2 колонки
        gbc.insets = new Insets(20, 0, 70, 0); // Верхний отступ 20px, нижний отступ 70px
        gbc.weightx = 1.0; // Разрешаем растягивание по горизонтали
        gbc.anchor = GridBagConstraints.CENTER; // Выравнивание по центру
        panel.add(questionLabel, gbc);

        // Настройка кнопок
        Dimension buttonSize = new Dimension(300, 50); // Размер кнопок
        button1.setPreferredSize(buttonSize);
        button1.setMinimumSize(buttonSize);
        button1.setMaximumSize(buttonSize);

        button2.setPreferredSize(buttonSize);
        button2.setMinimumSize(buttonSize);
        button2.setMaximumSize(buttonSize);

        button3.setPreferredSize(buttonSize);
        button3.setMinimumSize(buttonSize);
        button3.setMaximumSize(buttonSize);

        button4.setPreferredSize(buttonSize);
        button4.setMinimumSize(buttonSize);
        button4.setMaximumSize(buttonSize);

        button5.setPreferredSize(buttonSize);
        button5.setMinimumSize(buttonSize);
        button5.setMaximumSize(buttonSize);

        button6.setPreferredSize(buttonSize);
        button6.setMinimumSize(buttonSize);
        button6.setMaximumSize(buttonSize);

        // Добавить кнопки в первую колонку
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Занимает 1 колонку
        gbc.insets = new Insets(0, 70, 20, 0); // Нижний отступ 20px, правый отступ 0px, левый отступ 70px (+50)
        gbc.weightx = 1.0; // Разрешаем растягивание по горизонтали
        panel.add(button1, gbc);

        gbc.gridy = 2;
        panel.add(button2, gbc);

        // Добавить кнопки во вторую колонку
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 70); // Левый отступ 200px (-50), нижний отступ 20px, правый отступ 20px
        panel.add(button4, gbc);

        gbc.gridy = 2;
        panel.add(button3, gbc);

        // Добавить кнопку "Начать с чистого листа" в нижнем правом углу
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 70); // Левый отступ 200px (-50), нижний отступ 10px, правый отступ 10px
        panel.add(button5, gbc);

        // Создать отдельный контейнер для кнопки "Выход"
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Отступы слева и сверху
        exitPanel.add(button6);

        // Добавить контейнер с кнопкой "Выход" в левый нижний угол
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 10, 0); // Нижний отступ 10px, правый отступ 0px
        panel.add(exitPanel, gbc);
    }
}
