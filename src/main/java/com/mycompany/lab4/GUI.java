package com.mycompany.lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author nsoko
 */
public class GUI extends JFrame {

    private final JButton backButton = new JButton("< Назад в меню");
    private final QueryHandler q = new QueryHandler();
    private final JPanel mainPanel = new BackgroundPanel("back.png");

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
        frame.setLocationRelativeTo(null);

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
            GUIDesign.setMainPanelDesign(mainPanel, questionLabel, showInfoButton, null,
                    showWandSailsButton, showInventoryButton, showCustomersButton);
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
            int confirm = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите очистить все данные?", "Подтверждение", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    q.clearAllData();
                    JOptionPane.showMessageDialog(null, "Все данные успешно очищены!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка при очистке данных: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        showWandSailsButton.addActionListener(e -> {
            clearPanel(mainPanel);
            JPanel tablePanel = new JPanel();
            tablePanel.setPreferredSize(new Dimension(900, 400));
            JTable table = q.getWandsTable();
            pasteTableInto(table, tablePanel);
            mainPanel.add(tablePanel);
        });
        showInventoryButton.addActionListener(e -> {
            clearPanel(mainPanel);
            JPanel tablePanel = new JPanel();
            tablePanel.setPreferredSize(new Dimension(900, 400));
            JTable table = q.getStockTable();
            pasteTableInto(table, tablePanel);
            mainPanel.add(tablePanel);

        });
        showCustomersButton.addActionListener(e -> {
            clearPanel(mainPanel);
            JPanel tablePanel = new JPanel();
            tablePanel.setPreferredSize(new Dimension(900, 400));
            JTable table = q.getCustomersTable();
            pasteTableInto(table, tablePanel);
            mainPanel.add(tablePanel);
        });

        backButton.addActionListener(e -> {
            clearPanel(footerPanel);
            clearPanel(mainPanel);
            showInfoButton.setEnabled(true);
            GUIDesign.setMainPanelDesign(mainPanel, questionLabel, showInfoButton, makeWandButton,
                    sellWandButton, orderDeliveryButton, rebootButton);
            GUIDesign.setFooterPanelDesign(footerPanel, exitButton);
        });

        footerPanel.add(exitButton);
        GUIDesign.setMainPanelDesign(mainPanel, questionLabel, showInfoButton, makeWandButton,
                sellWandButton, orderDeliveryButton, rebootButton);
        GUIDesign.setFooterPanelDesign(footerPanel, exitButton);
        GUIDesign.buttonDesign(showInfoButton, 18, "#fff7ca");
        GUIDesign.buttonDesign(showWandSailsButton, 18, "#fff7ca");
        GUIDesign.buttonDesign(showInventoryButton, 18, "#fff7ca");
        GUIDesign.buttonDesign(showCustomersButton, 18, "#fff7ca");

        GUIDesign.buttonDesign(makeWandButton, 18, "#fff7ca");
        GUIDesign.buttonDesign(sellWandButton, 18, "#fff7ca");
        GUIDesign.buttonDesign(orderDeliveryButton, 18, "#fff7ca");
        GUIDesign.buttonDesign(rebootButton, 18, "#ffe1da");
        GUIDesign.buttonDesign(exitButton, 18, "#ffb8b3");
        GUIDesign.buttonDesign(backButton, 15, "#e6d0ff");

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(footerPanel, BorderLayout.SOUTH);

        frame.add(contentPane);
        frame.setVisible(true);
    }

    private void clearPanel(JPanel panelToClear) {
        panelToClear.removeAll();
        panelToClear.revalidate();
        panelToClear.repaint();
    }

    private void makeWandPanel(JPanel panel) {
        String[] woodOptions = q.getWoodNames();
        String[] coreOptions = q.getCoreNames();
        JComboBox<String> woodComboBox = new JComboBox<>(woodOptions);
        JComboBox<String> coreComboBox = new JComboBox<>(coreOptions);

        JButton setForSaleButton = new JButton("Выставить на продажу");

        setForSaleButton.addActionListener(e -> {
            String selectedWood = woodComboBox.getSelectedItem().toString();
            String selectedCore = coreComboBox.getSelectedItem().toString();
            boolean isAvailable = q.checkStockAvailability(selectedWood, selectedCore);

            if (isAvailable) {
                try {
                    q.createWandAndDeductStock(selectedWood, selectedCore);
                    JOptionPane.showMessageDialog(panel, "Палочка из " + selectedWood + " и " + selectedCore + " успешно выставлена на продажу.", "Успех", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Ошибка при выставлении палочки на продажу: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Недостаточно материалов на складе для создания палочки.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        GUIDesign.createWandPanelDesign(panel, woodOptions, coreOptions, woodComboBox, coreComboBox, setForSaleButton);
    }

    private void sellWandPanel(JPanel panel) {
        JLabel titleLabel = new JLabel("Продать палочку");
        JLabel selectWandLabel = new JLabel("Выберите палочку для продажи");
        JPanel tablePanel = new JPanel();
        JLabel selectCustomerLabel = new JLabel("Выберите покупателя");

        String[] customerNames = q.getCustomerNames();

        String[] customerOptions = new String[customerNames.length + 1];
        System.arraycopy(customerNames, 0, customerOptions, 0, customerNames.length);
        customerOptions[customerNames.length] = "Добавить нового покупателя";
        JComboBox<String> customerComboBox = new JComboBox<>(customerOptions);
        
        customerComboBox.addActionListener(e -> {
            String selectedOption = (String) customerComboBox.getSelectedItem();
            if ("Добавить нового покупателя".equals(selectedOption)) {
                openAddCustomerDialog(panel);
            }
        });

        JButton sellButton = new JButton("Продать");
        JTable table = q.getAvailableWandsTable();
        pasteTableInto(table, tablePanel);

        sellButton.addActionListener(e -> {
            try {
                if (table.getSelectedRow() == -1) {
                    throw new NullPointerException("Строка не выбрана.");
                }

                int selectedRow = table.getSelectedRow();
                int wandId = (int) table.getValueAt(selectedRow, 0);

                String selectedCustomerName = customerComboBox.getSelectedItem().toString();

                q.sellWandAndUpdateCustomer(wandId, selectedCustomerName);

                JOptionPane.showMessageDialog(panel, "Палочка успешно продана!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                table.setModel(q.getAvailableWandsTable().getModel());

            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(panel, "Выберите строку и покупателя.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Ошибка при продаже палочки: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        GUIDesign.sellWandPanelDesign(panel, titleLabel, selectWandLabel, tablePanel, selectCustomerLabel, customerOptions, customerComboBox, sellButton);
    }
    
    private void openAddCustomerDialog(JPanel parentPanel) {
        JDialog addCustomerDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parentPanel), "Добавить нового покупателя", true);
        addCustomerDialog.setSize(400, 200);
        addCustomerDialog.setLayout(new BorderLayout());

        JTextField nameField = new JTextField();
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Введите имя покупателя:"), BorderLayout.NORTH);
        inputPanel.add(nameField, BorderLayout.CENTER);

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = nameField.getText().trim();
                if (customerName.isEmpty()) {
                    JOptionPane.showMessageDialog(addCustomerDialog, "Имя не может быть пустым!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    q.addNewCustomer(customerName);
                    updateCustomerComboBox(parentPanel);
                    addCustomerDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(addCustomerDialog, "Ошибка при добавлении покупателя: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        addCustomerDialog.add(inputPanel, BorderLayout.CENTER);
        addCustomerDialog.add(addButton, BorderLayout.SOUTH);

        addCustomerDialog.setLocationRelativeTo(parentPanel);
        addCustomerDialog.setVisible(true);
    }
    
        private void updateCustomerComboBox(JPanel parentPanel) {
        for (Component component : parentPanel.getComponents()) {
            if (component instanceof JComboBox<?>) {
                JComboBox<String> comboBox = (JComboBox<String>) component;

                String[] updatedCustomerNames = q.getCustomerNames();
                String[] updatedCustomerOptions = new String[updatedCustomerNames.length + 1];
                System.arraycopy(updatedCustomerNames, 0, updatedCustomerOptions, 0, updatedCustomerNames.length);
                updatedCustomerOptions[updatedCustomerNames.length] = "Добавить нового покупателя";

                comboBox.setModel(new DefaultComboBoxModel<>(updatedCustomerOptions));
                break;
            }
        }
    }

    private void pasteTableInto(JTable table, JPanel panel) {
        table.setFillsViewportHeight(true);

        Font customFont = CustomFontLoader.loadCustomFont(18, "pixy.ttf");
        table.setFont(customFont);
        table.getTableHeader().setFont(customFont);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

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

        String[] woodOptions = q.getWoodNames();
        String[] coreOptions = q.getCoreNames();
        JComboBox<String> comboBox = new JComboBox<>();
        ActionListener radioButtonListener = e -> {
            if (woodButton.isSelected()) {
                comboBox.setModel(new DefaultComboBoxModel<>(woodOptions));
            } else if (coreButton.isSelected()) {
                comboBox.setModel(new DefaultComboBoxModel<>(coreOptions));
            }
        };
        woodButton.addActionListener(radioButtonListener);
        coreButton.addActionListener(radioButtonListener);

        woodButton.setSelected(true);
        comboBox.setModel(new DefaultComboBoxModel<>(woodOptions));
        JTextArea quantityTextArea = new JTextArea(1, 10);

        int[] currentDeliveryId = {0};

        JButton orderButton = new JButton("Заказать");

        orderButton.addActionListener(e -> {
            int quantity = 0;
            try {
                String quantityText = quantityTextArea.getText().trim();
                quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Введите корректное положительное число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedComponent = getSelectedButtonText(buttonGroup);
            String selectedItem = comboBox.getSelectedItem().toString();

            try {
                if (currentDeliveryId[0] == 0) {
                    currentDeliveryId[0] = q.createDeliveryAndGetId();
                }

                q.addDeliveryItem(currentDeliveryId[0], selectedComponent, selectedItem, quantity);

                JOptionPane.showMessageDialog(panel, "Поставка успешно добавлена!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Ошибка при добавлении поставки: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
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
