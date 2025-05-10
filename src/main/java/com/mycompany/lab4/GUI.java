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
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author nsoko
 */
public class GUI extends JFrame {

    private final JButton backButton = new JButton("< Назад в меню");
    private final JPanel cardPanelOption1 = GUIDesign.createCardPanel();
//    private final JPanel mainPanel = new JPanel(new GridBagLayout());
    private final JPanel mainPanel = new BackgroundPanel("back.png");
//    private final JPanel mainPanel = new BackgroundPanel("meoww.gif");
//    private final JPanel tablePanel = new JPanel(); //панель для нажатия на кнопку просмотреть информацию

    public GUI() {
        super("Лабораторная работа 4");
        mainPanel.setLayout(new GridBagLayout());
        ParticlePanel particlePanel = new ParticlePanel();
        setContentPane(particlePanel);

        JLabel welcomeText = new JLabel("<html><div style='text-align:center;width:500px;'>Добро пожаловать в учетную систему магазина волшебных палочек \"Оливандеры\"</div></html>");
        JButton startButton = new JButton("Приступить к работе");
        JButton exitButton = new JButton("Выход");

        startButton.addActionListener((ActionEvent e) -> {
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
        currentFrame.dispose();

        JFrame frame = new JFrame("Учетная система магазина волшебных палочек");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Центрирование окна

        mainPanel.setBackground(Color.WHITE);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setPreferredSize(new Dimension(1280, 120));

        JLabel questionLabel = new JLabel("Что Вы хотите сделать?");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton showInfoButton = new JButton("Просмотреть информацию...");
        JButton makeWandButton = new JButton("Выставить на продажу...");
        JButton sellWandButton = new JButton("Продать палочку");
        JButton orderDeliveryButton = new JButton("Заказать поставку...");
        JButton rebootButton = new JButton("Начать с чистого листа");
        JButton exitButton = new JButton("Выход");

        JButton showWandSailsButton = new JButton("Палочки");
        JButton showInventoryButton = new JButton("Состояние склада");
        JButton showCustomersButton = new JButton("Покупатели магазина");

        showInfoButton.addActionListener(e -> {
            clearPanel(mainPanel);
            GUIDesign.setFooterPanelDesign(footerPanel, exitButton, backButton);
            GUIDesign.setMainPanelDesign(mainPanel, questionLabel, showInfoButton, null, showWandSailsButton, showInventoryButton, showCustomersButton);
            showInfoButton.setEnabled(false);
        });
        makeWandButton.addActionListener(e -> {
            clearPanel(mainPanel);
            makeWandPanel(mainPanel);
            GUIDesign.setFooterPanelDesign(footerPanel, exitButton, backButton);
        });
        sellWandButton.addActionListener(e -> {
            clearPanel(mainPanel);
            sellWandPanel(mainPanel);
            GUIDesign.setFooterPanelDesign(footerPanel, exitButton, backButton);

        });
        orderDeliveryButton.addActionListener(e -> {
            clearPanel(mainPanel);
            orderDeliveryPanel(mainPanel);
            GUIDesign.setFooterPanelDesign(footerPanel, exitButton, backButton);
        });
        rebootButton.addActionListener(e -> {

        });
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        showWandSailsButton.addActionListener(e -> {
            clearPanel(mainPanel);
            JPanel tablePanel = new JPanel();
            tablePanel.setPreferredSize(new Dimension(900, 400));
            JTable table = pasteTableInto(tablePanel);
            mainPanel.add(tablePanel);
        });
        showInventoryButton.addActionListener(e -> {
            clearPanel(mainPanel);
            BackgroundPanel gifPanel = new BackgroundPanel("gif.gif");
            gifPanel.setPreferredSize(new Dimension(900, 400));
            mainPanel.add(gifPanel);
            GUIDesign.insertTable(mainPanel, "таблица которую нужно отобразить");
        });
        showCustomersButton.addActionListener(e -> {
            clearPanel(mainPanel);
            GUIDesign.insertTable(mainPanel, "таблица которую нужно отобразить");
        });

        backButton.addActionListener(e -> {
            clearPanel(footerPanel);
            clearPanel(mainPanel);
            showInfoButton.setEnabled(true);
            GUIDesign.setMainPanelDesign(mainPanel, questionLabel, showInfoButton, makeWandButton, sellWandButton, orderDeliveryButton, rebootButton);
            GUIDesign.setFooterPanelDesign(footerPanel, exitButton);
        });

        footerPanel.add(exitButton);
        GUIDesign.setMainPanelDesign(mainPanel, questionLabel, showInfoButton, makeWandButton, sellWandButton, orderDeliveryButton, rebootButton);
        GUIDesign.setFooterPanelDesign(footerPanel, exitButton);
        GUIDesign.buttonDesign(showInfoButton, 18, "#fff8e7");
        GUIDesign.buttonDesign(showWandSailsButton, 18, "#fff8e7");
        GUIDesign.buttonDesign(showInventoryButton, 18, "#fff8e7");
        GUIDesign.buttonDesign(showCustomersButton, 18, "#fff8e7");

        GUIDesign.buttonDesign(makeWandButton, 18, "#fff8e7");
        GUIDesign.buttonDesign(sellWandButton, 18, "#fff8e7");
        GUIDesign.buttonDesign(orderDeliveryButton, 18, "#fff8e7");
        GUIDesign.buttonDesign(rebootButton, 18, "#ffe1da");
        GUIDesign.buttonDesign(exitButton, 18, "#ffb8b3");
        GUIDesign.buttonDesign(backButton, 15, "#e6d0ff");

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(mainPanel, BorderLayout.CENTER); // Верхняя часть
        contentPane.add(footerPanel, BorderLayout.SOUTH); // Нижняя часть

        frame.add(contentPane);

        frame.setVisible(true);
    }

    private void clearPanel(JPanel panelToClear) {
        panelToClear.removeAll();
        panelToClear.revalidate();
        panelToClear.repaint();
    }

    private void makeWandPanel(JPanel panel) {
        String[] woodOptions = {"Дуб", "Орех", "Бук", "Кедр"};
        String[] coreOptions = {"Перо феникса", "Хвост единорога", "Сердечина дракона"};
        JComboBox<String> woodComboBox = new JComboBox<>(woodOptions);
        JComboBox<String> coreComboBox = new JComboBox<>(coreOptions);

        JButton setForSaleButton = new JButton("Выставить на продажу");

        setForSaleButton.addActionListener(e -> {
            System.out.println(woodComboBox.getSelectedItem().toString() + " " + coreComboBox.getSelectedItem().toString());
        });

        GUIDesign.createWandPanelDesign(panel, woodOptions, coreOptions, woodComboBox, coreComboBox, setForSaleButton);
    }

    private void sellWandPanel(JPanel panel) {
        JLabel titleLabel = new JLabel("Продать палочку");
        JLabel selectWandLabel = new JLabel("Выберите палочку для продажи");
        JPanel tablePanel = new JPanel();
        JLabel selectCustomerLabel = new JLabel("Выберите покупателя");

        String[] customerOptions = {"1", "2", "3"};

        JComboBox<String> customerComboBox = new JComboBox<>(customerOptions);
        JButton sellButton = new JButton("Продать");
        JTable table = pasteTableInto(tablePanel);
        sellButton.addActionListener(e -> {
            try {
                if (table.getSelectedRow() == -1) {
                    throw new NullPointerException();
                }
                System.out.println(table.getSelectedColumn());
                System.out.println((table.getSelectedRow() + 1) + " " + customerComboBox.getSelectedItem().toString());
            } catch (NullPointerException ex) {

                JOptionPane.showMessageDialog(null, "Выберите строку");
            }
        });

        GUIDesign.sellWandPanelDesign(panel, titleLabel, selectWandLabel, tablePanel, selectCustomerLabel, customerOptions, customerComboBox, sellButton);
    }
    
    private JTable prepareTable(){
        //метод для сбора данных для таблицы (???)
        return new JTable();
    }

    private JTable pasteTableInto(JPanel panel) {
        String[] columnNames = {"ID", "Древесина", "Сердцевина"};
        Object[][] data = new Object[10][3];

        String[] woodOptions = {"Дуб", "Орех", "Бук", "Кедр", "Ясень", "Махагон", "Гикори", "Вишня", "Красное дерево", "Ель"};
        String[] coreOptions = {"Перо феникса", "Хвост единорога", "Сердечина дракона", "Чешуя василиска"};

        for (int i = 0; i < 10; i++) {
            data[i][0] = i + 1; // ID
            data[i][1] = woodOptions[i % woodOptions.length]; // Древесина
            data[i][2] = coreOptions[i % coreOptions.length]; // Сердцевина
        }

        // Создаем таблицу
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Запрещаем редактирование всех ячеек
            }
        };
        table.setFillsViewportHeight(true); // Растягиваем таблицу по высоте

        // Настройка шрифта для таблицы
        Font customFont = CustomFontLoader.loadCustomFont(18, "pixy.ttf");
        table.setFont(customFont);
        table.getTableHeader().setFont(customFont);

        // Добавляем таблицу в JScrollPane для прокрутки
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Размер таблицы

        // Добавляем JScrollPane в панель
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        return table;
    }

    private void orderDeliveryPanel(JPanel panel) {
        JLabel titleLabel = new JLabel("Заказать поставку");
        JLabel selectComponentLabel = new JLabel("Выберите компонент");
        JLabel enterQuantityLabel = new JLabel("Введите количество");
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton woodButton = new JRadioButton("Древесина");
        JRadioButton coreButton = new JRadioButton("Сердцевина");
        buttonGroup.add(woodButton);
        buttonGroup.add(coreButton);

        String[] woodOptions = {"Дуб", "Орех", "Бук", "Кедр"};
        String[] coreOptions = {"Перо феникса", "Хвост единорога", "Сердечина дракона"};
        JComboBox<String> comboBox = new JComboBox<>();
        ActionListener radioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (woodButton.isSelected()) {
                    comboBox.setModel(new DefaultComboBoxModel<>(woodOptions));
                } else if (coreButton.isSelected()) {
                    comboBox.setModel(new DefaultComboBoxModel<>(coreOptions));
                }
            }
        };
        woodButton.addActionListener(radioButtonListener);
        coreButton.addActionListener(radioButtonListener);

        woodButton.setSelected(true);
        comboBox.setModel(new DefaultComboBoxModel<>(woodOptions));
        JTextArea quantityTextArea = new JTextArea(1, 10);
        JButton orderButton = new JButton("Заказать");

        orderButton.addActionListener(e -> {
            int quantity = 0;
            try {
                String quantityText = quantityTextArea.getText().trim();
                System.out.println("Введено количество: " + quantityText); // Отладочное сообщение
                quantity = Integer.parseInt(quantityText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректное число");
                return;
            }
            String selectedButtonText = getSelectedButtonText(buttonGroup);
            System.out.println("Заказ на: " + selectedButtonText);
            System.out.println("Тип: " + comboBox.getSelectedItem().toString());
            System.out.println("Количество: " + quantity);
        });
        GUIDesign.orderDeliveryPanelDesign(panel, titleLabel, selectComponentLabel, enterQuantityLabel, buttonGroup, comboBox, woodOptions, coreOptions, quantityTextArea, orderButton);

    }

    private static String getSelectedButtonText(ButtonGroup buttonGroup) {
        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

}
