/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author nsoko
 */
public class GUIDesign {

    public static void startFrameDesign(JPanel panel, JLabel welcomeText, JButton button1, JButton button2) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font customFont2 = CustomFontLoader.loadCustomFont(60, "NeedleteethSP_0.otf");
        welcomeText.setFont(customFont2.deriveFont(Font.LAYOUT_LEFT_TO_RIGHT));
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(70, 20, 20, 20);
        gbc.weightx = 0.0;
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

        Font customFont1 = CustomFontLoader.loadCustomFont(25, "pixy.ttf");
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

    public static void setMainPanelDesign(JPanel panel, JLabel questionLabel,
            JButton button1, JButton button2, JButton button3, JButton button4, JButton button5) {
        GridBagConstraints gbc = new GridBagConstraints();
        Font customFont2 = CustomFontLoader.loadCustomFont(45, "pixy.ttf");
        questionLabel.setFont(customFont2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(70, 0, 70, 0);
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(questionLabel, gbc);

        Dimension buttonSize = new Dimension(300, 50);
        button1.setPreferredSize(buttonSize);
        button1.setMinimumSize(buttonSize);
        button1.setMaximumSize(buttonSize);

        if (button2 != null) {
            button2.setPreferredSize(buttonSize);
            button2.setMinimumSize(buttonSize);
            button2.setMaximumSize(buttonSize);
        }
        button3.setPreferredSize(buttonSize);
        button3.setMinimumSize(buttonSize);
        button3.setMaximumSize(buttonSize);

        button4.setPreferredSize(buttonSize);
        button4.setMinimumSize(buttonSize);
        button4.setMaximumSize(buttonSize);

        button5.setPreferredSize(buttonSize);
        button5.setMinimumSize(buttonSize);
        button5.setMaximumSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 120, 20, 0); //нижний отступ 20px, правый отступ 0px, левый отступ 120px
        gbc.weightx = 1.0;
        panel.add(button1, gbc);

        gbc.gridy = 2;
        if (button2 != null) {
            panel.add(button2, gbc);
        }

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 200, 20, 120); //левый отступ 200px, нижний отступ 20px, правый отступ 120px
        panel.add(button3, gbc);

        gbc.gridy = 2;
        panel.add(button4, gbc);

        gbc.gridy = 3;
        panel.add(button5, gbc);

    }

    public static void setFooterPanelDesign(JPanel panel, JButton button6) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Dimension buttonSize = new Dimension(150, 50);
        button6.setPreferredSize(buttonSize);
        button6.setMinimumSize(buttonSize);
        button6.setMaximumSize(buttonSize);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(0, 0, 20, 20);

        panel.add(button6, gbc);

        panel.setBackground(Color.decode("#ebf3ff"));
    }

    public static void setFooterPanelDesign(JPanel panel, JButton button6, JButton backButton) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Dimension buttonSize = new Dimension(150, 50);
        button6.setPreferredSize(buttonSize);
        button6.setMinimumSize(buttonSize);
        button6.setMaximumSize(buttonSize);

        backButton.setPreferredSize(buttonSize);
        backButton.setMinimumSize(buttonSize);
        backButton.setMaximumSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(0, 20, 20, 0);
        panel.add(backButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(0, 0, 20, 20);

        panel.add(button6, gbc);

        panel.setBackground(Color.decode("#ebf4ff"));
    }

    public static void createWandPanelDesign(JPanel panel, String[] woodOptions, String[] coreOptions, JComboBox<String> woodComboBox, JComboBox<String> coreComboBox, JButton setForSaleButton) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font customFont1 = CustomFontLoader.loadCustomFont(42, "pixy.ttf");
        Font customFont2 = CustomFontLoader.loadCustomFont(20, "pixy.ttf");

        JLabel titleLabel = new JLabel("Соберите палочку для продажи");
        titleLabel.setFont(customFont1);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        woodComboBox.setPreferredSize(new Dimension(200, 30));
        woodComboBox.setBackground(Color.decode("#fff8e7"));
        woodComboBox.setFont(customFont2);
        woodComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                woodComboBox.setBackground(Color.decode("#ffe0b0"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                woodComboBox.setBackground(Color.decode("#fff8e7"));
            }
        });

        coreComboBox.setPreferredSize(new Dimension(200, 30));
        coreComboBox.setBackground(Color.decode("#fff8e7"));
        coreComboBox.setFont(customFont2);
        coreComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                coreComboBox.setBackground(Color.decode("#ffe0b0"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                coreComboBox.setBackground(Color.decode("#fff8e7"));
            }
        });
        JLabel woodLabel = new JLabel("Выберите древесину:");
        woodLabel.setFont(customFont2);

        JLabel coreLabel = new JLabel("Выберите сердцевину:");
        coreLabel.setFont(customFont2);

        buttonDesign(setForSaleButton, 25, "#faffb6");
        setForSaleButton.setPreferredSize(new Dimension(400, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 10);
        panel.add(woodLabel, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(woodComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 70, 10, 70);
        panel.add(coreLabel, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(10, 70, 10, 70);
        panel.add(coreComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 70, 20, 70);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(setForSaleButton, gbc);

    }

    public static void sellWandPanelDesign(JPanel panel, JLabel titleLabel, JLabel selectWandLabel,
            JPanel tablePanel, JLabel selectCustomerLabel, String[] customerOptions,
            JComboBox<String> customerComboBox, JButton sellButton) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font customFont1 = CustomFontLoader.loadCustomFont(42, "pixy.ttf");
        Font customFont2 = CustomFontLoader.loadCustomFont(24, "pixy.ttf");
        Font customFont3 = CustomFontLoader.loadCustomFont(20, "pixy.ttf");

        titleLabel.setFont(customFont1);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(titleLabel, gbc);

        selectWandLabel.setFont(customFont2);

        tablePanel.setBackground(Color.LIGHT_GRAY);
        tablePanel.setPreferredSize(new Dimension(600, 400));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 70, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(selectWandLabel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(tablePanel, gbc);

        selectCustomerLabel.setFont(customFont2);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 70);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(selectCustomerLabel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 5, 70);
        gbc.anchor = GridBagConstraints.NORTH;
        customerComboBox.setPreferredSize(new Dimension(200, 50));
        customerComboBox.setMaximumSize(new Dimension(200, 50));
        customerComboBox.setFont(customFont3);
        panel.add(customerComboBox, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 10, 70);
        gbc.anchor = GridBagConstraints.NORTH;
        sellButton.setPreferredSize(new Dimension(200, 50));
        buttonDesign(sellButton, 20, "#faffb6");
        panel.add(sellButton, gbc);
    }

    public static void orderDeliveryPanelDesign(JPanel panel, JLabel titleLabel, JLabel selectComponentLabel, JLabel enterQuantityLabel, ButtonGroup buttonGroup,
            JComboBox<String> comboBox, String[] woodOptions, String[] coreOptions, JTextArea quantityTextArea, JButton orderButton) {
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font customFont = CustomFontLoader.loadCustomFont(32, "pixy.ttf");
        Font customFont1 = CustomFontLoader.loadCustomFont(20, "pixy.ttf");

        //лейбл "Заказать поставку"
        titleLabel.setFont(customFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(20, 0, 20, 0); 
        gbc.weightx = 1.0; 
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        //левая колонка
        //метка "Выберите компонент"
        selectComponentLabel.setFont(customFont1);

        //метка "Выберите тип компонента"
        JLabel selectTypeLabel = new JLabel("Выберите тип компонента");
        selectTypeLabel.setFont(customFont1);

        //метка "Введите количество"
        enterQuantityLabel.setFont(customFont1);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        gbc.insets = new Insets(10, 210, 10, 10); 
        gbc.weightx = 0.0; 
        gbc.fill = GridBagConstraints.NONE; 
        panel.add(selectComponentLabel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 265, 10, 10);
        panel.add(selectTypeLabel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 210, 10, 10);
        panel.add(enterQuantityLabel, gbc);

        //правая колонка
        //радиокнопки
        JPanel radioButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioButtonsPanel.setOpaque(false);
        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            button.setOpaque(false);
            button.setFont(customFont1);
            radioButtonsPanel.add(button);
        }


        //JComboBox для выбора типа компонента
        comboBox.setPreferredSize(new Dimension(200, 30));
        comboBox.setFont(customFont1);

        //JTextArea для ввода количества
        quantityTextArea.setBackground(Color.decode("#e8e8e8"));
        quantityTextArea.setMaximumSize(new Dimension(200,25));
        quantityTextArea.setPreferredSize(new Dimension(200,25));
        quantityTextArea.setFont(customFont1);

        //кнопка "Заказать"
        buttonDesign(orderButton, 20, "#faffb6");

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 70, 10, 10); 
        gbc.weightx = 0.0; 
        gbc.fill = GridBagConstraints.NONE; 
        panel.add(radioButtonsPanel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 30);
        panel.add(comboBox, gbc);

        gbc.gridy = 3;
        panel.add(quantityTextArea, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 10, 100);
        panel.add(orderButton, gbc);
    }

 

    public static void buttonDesign(JButton button, int size, String color) {
        button.setBackground(Color.decode(color));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        Font font = CustomFontLoader.loadCustomFont(size, "pixy.ttf");
        button.setFont(font);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (color.equals("#fff7ca") || color.equals("#faffb6")) {
                    button.setBackground(Color.decode("#ffe0b0"));
                } else if (color.equals("#e6d0ff")) {
                    button.setBackground(Color.decode("#dbb8ff"));
                } else {
                    button.setBackground(Color.decode("#ffa79a"));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode(color));
            }
        });
    }


}
