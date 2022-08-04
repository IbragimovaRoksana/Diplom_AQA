package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/app";
    private static String username = "app";
    private static String password = "pass";

    private DatabaseConnection() {
    }

    public static String getStatus(String status) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String result;
        try (Connection conn = DriverManager.getConnection(url, username, password);
        ) {
            result = runner.query(conn, status, new ScalarHandler<>());
        }
        return result;
    }

    public static String getCreditStatus() throws SQLException {
        String statusCredit = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        return getStatus(statusCredit);
    }

    public static String getPaymentStatus() throws SQLException {
        String statusPayment = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        return getStatus(statusPayment);
    }

    public static void deleteTables() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String creditTable = "DELETE FROM credit_request_entity";
        String paymentTable = "DELETE FROM payment_entity";
        String orderTable = "DELETE FROM order_entity";
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            runner.update(conn, orderTable);
            runner.update(conn, creditTable);
            runner.update(conn, paymentTable);
        }
    }
}
