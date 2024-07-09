package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

import static java.sql.DriverManager.getConnection;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        executeStatement(String.format("CREATE TABLE %s ();", tableName));
    }

    public void dropTable(String tableName) {
        executeStatement(String.format("DROP TABLE %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        executeStatement(String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        executeStatement(String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executeStatement(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        String url = config.getProperty("url");
        String login = config.getProperty("username");
        String password = config.getProperty("password");
        try (var tableEditor = new TableEditor(config)) {
            tableEditor.connection = DriverManager.getConnection(url, login, password);
            String tableName = "test";

            tableEditor.createTable(tableName);
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.addColumn(tableName, "name", "VARCHAR(255)");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.renameColumn(tableName, "name", "first_name");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.dropColumn(tableName, "first_name");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.dropTable(tableName);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}