package jdbc.demo1;

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo5 {
    private static final String url = "jdbc:mysql://localhost:3306/lenden";
    private static final String username = "root";
    private static final String password = "Ashish@99";

    public static void main(String[] args) {
        // Load the MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Establish the connection and handle the transaction
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Scanner sc = new Scanner(System.in)) {

            connection.setAutoCommit(false);

            // SQL Queries
            String debit_query = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
            String credit_query = "UPDATE account SET balance = balance + ? WHERE account_number = ?";

            // Prepare Statements
            try (PreparedStatement debitPreparedStatement = connection.prepareStatement(debit_query);
                 PreparedStatement creditPreparedStatement = connection.prepareStatement(credit_query)) {

                System.out.println("Enter the source account number:");
                int sourceAccount = sc.nextInt();
                System.out.println("Enter the destination account number:");
                int destinationAccount = sc.nextInt();
                System.out.println("Enter the transfer amount:");
                double amount = sc.nextDouble();

                // Check if the source account has sufficient balance
                if (isSufficient(connection, sourceAccount, amount)) {
                    // Set parameters for debit query
                    debitPreparedStatement.setDouble(1, amount);
                    debitPreparedStatement.setInt(2, sourceAccount);

                    // Set parameters for credit query
                    creditPreparedStatement.setDouble(1, amount);
                    creditPreparedStatement.setInt(2, destinationAccount);

                    // Execute updates
                    debitPreparedStatement.executeUpdate();
                    creditPreparedStatement.executeUpdate();

                    // Commit transaction
                    connection.commit();
                    System.out.println("Transaction successful!");
                } else {
                    connection.rollback();
                    System.out.println("Transaction failed: Insufficient balance.");
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean isSufficient(Connection connection, int accountNumber, double amount) {
        String query = "SELECT balance FROM account WHERE account_number = ?";
        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, accountNumber);
            try (ResultSet rs = pr.executeQuery()) {
                if (rs.next()) {
                    double currentBalance = rs.getDouble("balance");
                    return amount <= currentBalance;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
