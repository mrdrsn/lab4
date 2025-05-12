package com.mycompany.lab4;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryHandler {

    public String[] getWoodNames() {
        String query = "SELECT name FROM Wood_type";
        ArrayList<String> woodNamesList = new ArrayList<>();
        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String woodName = resultSet.getString("name");
                woodNamesList.add(woodName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return woodNamesList.toArray(new String[0]);
    }

    public String[] getCoreNames() {
        String query = "SELECT name FROM Core_type";
        ArrayList<String> coreNamesList = new ArrayList<>();
        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String coreName = resultSet.getString("name");
                coreNamesList.add(coreName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coreNamesList.toArray(new String[0]);
    }

    public String[] getCustomerNames() {
        String query = "SELECT name FROM Customers";
        ArrayList<String> customerNamesList = new ArrayList<>();
        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String customerName = resultSet.getString("name");
                customerNamesList.add(customerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerNamesList.toArray(new String[0]);
    }

    public boolean checkStockAvailability(String woodName, String coreName) {
        String query = "SELECT "
                + "SUM(CASE WHEN di.component = 'wood' AND wt.name = ? THEN di.quantity ELSE 0 END) AS wood_stock, "
                + "SUM(CASE WHEN di.component = 'core' AND ct.name = ? THEN di.quantity ELSE 0 END) AS core_stock "
                + "FROM Delivery_item di "
                + "LEFT JOIN Wood_type wt ON di.type_id = wt.id AND di.component = 'wood' "
                + "LEFT JOIN Core_type ct ON di.type_id = ct.id AND di.component = 'core'";

        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, woodName);
            statement.setString(2, coreName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int woodStock = resultSet.getInt("wood_stock");
                    int coreStock = resultSet.getInt("core_stock");
                    return woodStock > 0 && coreStock > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void createWandAndDeductStock(String woodName, String coreName) throws SQLException {
        Connection connection = null;
        try {
            connection = DataBaseHandler.getConnection();
            connection.setAutoCommit(false);

            int woodId = getTypeIdByName(woodName, "wood");
            int coreId = getTypeIdByName(coreName, "core");

            String insertWandQuery = "INSERT INTO Wands (wood, core, status) VALUES (?, ?, 'на продаже')";
            try (PreparedStatement insertWandStatement = connection.prepareStatement(insertWandQuery)) {
                insertWandStatement.setInt(1, woodId);
                insertWandStatement.setInt(2, coreId);
                insertWandStatement.executeUpdate();
            }
            String updateStockQuery = "UPDATE Delivery_item "
                    + "SET quantity = quantity - 1 "
                    + "WHERE component = ? AND type_id = ?";
            try (PreparedStatement updateWoodStatement = connection.prepareStatement(updateStockQuery)) {
                updateWoodStatement.setString(1, "wood");
                updateWoodStatement.setInt(2, woodId);
                updateWoodStatement.executeUpdate();
            }
            try (PreparedStatement updateCoreStatement = connection.prepareStatement(updateStockQuery)) {
                updateCoreStatement.setString(1, "core");
                updateCoreStatement.setInt(2, coreId);
                updateCoreStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw ex;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    private int getTypeIdByName(String name, String component) throws SQLException {
        String query = "SELECT id FROM " + (component.equals("wood") ? "Wood_type" : "Core_type") + " WHERE name = ?";
        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                } else {
                    throw new SQLException("Тип компонента '" + name + "' не найден.");
                }
            }
        }
    }

    public JTable getWandsTable() {
        String query = "SELECT w.id AS wand_id, wt.name AS wood_name, ct.name AS core_name, w.status, w.sale_date "
                + "FROM Wands w "
                + "JOIN Wood_type wt ON w.wood = wt.id "
                + "JOIN Core_type ct ON w.core = ct.id";

        Object[][] data = null;
        String[] columnNames = {"ID палочки", "Древесина", "Сердцевина", "Статус", "Дата продажи"};

        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY); ResultSet resultSet = statement.executeQuery()) {

            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            data = new Object[rowCount][5];
            int rowIndex = 0;

            while (resultSet.next()) {
                int wandId = resultSet.getInt("wand_id");
                String woodName = resultSet.getString("wood_name");
                String coreName = resultSet.getString("core_name");
                String status = resultSet.getString("status");
                String saleDate = resultSet.getDate("sale_date") != null ? resultSet.getDate("sale_date").toString() : "Не продана";

                data[rowIndex][0] = wandId;
                data[rowIndex][1] = woodName;
                data[rowIndex][2] = coreName;
                data[rowIndex][3] = status;
                data[rowIndex][4] = saleDate;

                rowIndex++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public JTable getStockTable() {
        String query = "SELECT di.component, "
                + "CASE "
                + "WHEN di.component = 'wood' THEN wt.name "
                + "WHEN di.component = 'core' THEN ct.name "
                + "END AS type, "
                + "SUM(di.quantity) AS total_quantity "
                + "FROM Delivery_item di "
                + "LEFT JOIN Wood_type wt ON di.type_id = wt.id AND di.component = 'wood' "
                + "LEFT JOIN Core_type ct ON di.type_id = ct.id AND di.component = 'core' "
                + "GROUP BY di.component, type "
                + "ORDER BY di.component DESC ";
        Object[][] data = null;
        String[] columnNames = {"Компонент", "Тип", "Количество"};

        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY); ResultSet resultSet = statement.executeQuery()) {
            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            data = new Object[rowCount][3];
            int rowIndex = 0;

            while (resultSet.next()) {
                String component = resultSet.getString("component").equals("wood") ? "Древесина" : "Сердцевина";
                String type = resultSet.getString("type");
                int quantity = resultSet.getInt("total_quantity");
                data[rowIndex][0] = component;
                data[rowIndex][1] = type;
                data[rowIndex][2] = quantity;
                rowIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public JTable getCustomersTable() {
        String query = "SELECT c.id AS customer_id, c.name AS customer_name, c.wand_id AS wand_id, w.sale_date AS purchase_date "
                + "FROM Customers c "
                + "LEFT JOIN Wands w ON c.wand_id = w.id "
                + "WHERE c.wand_id IS NOT NULL "
                + "ORDER BY c.id ";
        Object[][] data = null;
        String[] columnNames = {"ID покупателя", "Имя покупателя", "ID палочки", "Дата покупки"};

        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY); ResultSet resultSet = statement.executeQuery()) {
            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            data = new Object[rowCount][4];
            int rowIndex = 0;
            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                Integer wandId = resultSet.getObject("wand_id", Integer.class);
                String purchaseDate = resultSet.getDate("purchase_date") != null ? resultSet.getDate("purchase_date").toString() : "Не куплена";
                data[rowIndex][0] = customerId;
                data[rowIndex][1] = customerName;
                data[rowIndex][2] = wandId != null ? wandId : "Нет";
                data[rowIndex][3] = purchaseDate;
                rowIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public JTable getAvailableWandsTable() {
        String query = "SELECT w.id AS wand_id, wt.name AS wood_name, ct.name AS core_name, w.status, w.sale_date "
                + "FROM Wands w "
                + "JOIN Wood_type wt ON w.wood = wt.id "
                + "JOIN Core_type ct ON w.core = ct.id "
                + "WHERE w.status = 'на продаже'"
                + "ORDER BY w.id";
        Object[][] data = null;
        String[] columnNames = {"ID палочки", "Древесина", "Сердцевина", "Статус", "Дата продажи"};

        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY); ResultSet resultSet = statement.executeQuery()) {
            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            data = new Object[rowCount][5];
            int rowIndex = 0;
            while (resultSet.next()) {
                int wandId = resultSet.getInt("wand_id");
                String woodName = resultSet.getString("wood_name");
                String coreName = resultSet.getString("core_name");
                String status = resultSet.getString("status");
                String saleDate = resultSet.getDate("sale_date") != null ? resultSet.getDate("sale_date").toString() : "Не продана";
                data[rowIndex][0] = wandId;
                data[rowIndex][1] = woodName;
                data[rowIndex][2] = coreName;
                data[rowIndex][3] = status;
                data[rowIndex][4] = saleDate;
                rowIndex++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public void sellWandAndUpdateCustomer(int wandId, String customerName) throws SQLException {
        Connection connection = null;
        try {
            connection = DataBaseHandler.getConnection();
            connection.setAutoCommit(false);
            String updateWandQuery = "UPDATE Wands SET status = 'продана', sale_date = CURRENT_DATE WHERE id = ?";
            try (PreparedStatement updateWandStatement = connection.prepareStatement(updateWandQuery)) {
                updateWandStatement.setInt(1, wandId);
                updateWandStatement.executeUpdate();
            }
            String insertCustomerQuery = "INSERT INTO Customers (name, wand_id) VALUES (?, ?)";
            try (PreparedStatement insertCustomerStatement = connection.prepareStatement(insertCustomerQuery)) {
                insertCustomerStatement.setString(1, customerName);
                insertCustomerStatement.setInt(2, wandId);
                insertCustomerStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw ex;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public int createDeliveryAndGetId() throws SQLException {
        String insertDeliveryQuery = "INSERT INTO Delivery (delivery_date) VALUES (CURRENT_DATE) RETURNING id";
        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(insertDeliveryQuery); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new SQLException("Не удалось получить ID новой поставки.");
            }
        }
    }

    public void addDeliveryItem(int deliveryId, String selectedComponent, String typeName, int quantity) throws SQLException {
        String component = selectedComponent.equals("Древесина") ? "wood" : "core";
        int typeId = getTypeIdByName(typeName, component);
        String insertDeliveryItemQuery = "INSERT INTO Delivery_item (delivery_id, component, type_id, quantity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(insertDeliveryItemQuery)) {
            statement.setInt(1, deliveryId);
            statement.setString(2, component);
            statement.setInt(3, typeId);
            statement.setInt(4, quantity);
            statement.executeUpdate();
        }
    }

    public void clearAllData() {
        Connection connection = null;
        try {
            connection = DataBaseHandler.getConnection();
            connection.setAutoCommit(false);
            String deleteDeliveryItemQuery = "DELETE FROM Delivery_item";
            try (PreparedStatement deleteDeliveryItemStatement = connection.prepareStatement(deleteDeliveryItemQuery)) {
                deleteDeliveryItemStatement.executeUpdate();
            }
            String deleteDeliveryQuery = "DELETE FROM Delivery";
            try (PreparedStatement deleteDeliveryStatement = connection.prepareStatement(deleteDeliveryQuery)) {
                deleteDeliveryStatement.executeUpdate();
            }
            String deleteWandsQuery = "DELETE FROM Wands";
            try (PreparedStatement deleteWandsStatement = connection.prepareStatement(deleteWandsQuery)) {
                deleteWandsStatement.executeUpdate();
            }
            String deleteCustomersQuery = "DELETE FROM Customers";
            try (PreparedStatement deleteCustomersStatement = connection.prepareStatement(deleteCustomersQuery)) {
                deleteCustomersStatement.executeUpdate();
            }
            String resetSequenceQuery = "ALTER SEQUENCE delivery_id_seq RESTART WITH 1;";
            try (PreparedStatement resetSequenceStatement = connection.prepareStatement(resetSequenceQuery)) {
                resetSequenceStatement.executeUpdate();
            }
            resetSequenceQuery = "ALTER SEQUENCE delivery_item_id_seq RESTART WITH 1;";
            try (PreparedStatement resetSequenceStatement = connection.prepareStatement(resetSequenceQuery)) {
                resetSequenceStatement.executeUpdate();
            }
            resetSequenceQuery = "ALTER SEQUENCE wands_id_seq RESTART WITH 1;";
            try (PreparedStatement resetSequenceStatement = connection.prepareStatement(resetSequenceQuery)) {
                resetSequenceStatement.executeUpdate();
            }
            resetSequenceQuery = "ALTER SEQUENCE customers_id_seq RESTART WITH 1;";
            try (PreparedStatement resetSequenceStatement = connection.prepareStatement(resetSequenceQuery)) {
                resetSequenceStatement.executeUpdate();
            }
            String updateCustomersQuery = "INSERT INTO Customers (name, wand_id) VALUES "
                    + "('Гарри Поттер', NULL), "
                    + "('Гермиона Грейнджер', NULL), "
                    + "('Рон Уизли', NULL), "
                    + "('Драко Малфой', NULL), "
                    + "('Невилл Долгопупс', NULL); ";
            try (PreparedStatement updateCustomersStatement = connection.prepareStatement(updateCustomersQuery)) {
                updateCustomersStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public void addNewCustomer(String customerName) throws SQLException {
        String insertCustomerQuery = "INSERT INTO Customers (name) VALUES (?)";
        try (Connection connection = DataBaseHandler.getConnection(); PreparedStatement statement = connection.prepareStatement(insertCustomerQuery)) {
            statement.setString(1, customerName);
            statement.executeUpdate();
        }
    }
}
