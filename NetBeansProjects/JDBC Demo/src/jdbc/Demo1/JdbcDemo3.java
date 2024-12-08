package jdbc.demo1;

import java.sql.*;

public class JdbcDemo3 {

    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "Ashish@99";

    public static void main(String[] args) {
        // Load the MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Establish the connection and fetch data
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
          //  String query = "UPDATE students SET marks=? WHERE id=?";
           String query = "DELETE FROM students WHERE id=?";
          
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                //preparedStatement.setDouble(1, 99.0);
                 preparedStatement.setDouble(1,3);
              int rows = preparedStatement.executeUpdate();

           
           
           if(rows>0)
           {
               System.out.println("Data updated succesfuly");
               
           } else
           {
               System.out.println("Data not updated!");
           }
//                // Execute the query
//                ResultSet rs = preparedStatement.executeQuery();
//
//                // Process the result
//                if (rs.next()) {
//                    double marks = rs.getDouble("marks");
//                    System.out.println("Marks: " + marks);
//                } else {
//                    System.out.println("Marks not found!");
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
