package jdbc.demo1;

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo4 {
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
        try (Connection connection = DriverManager.getConnection(url, username, password);
                
             Scanner sc = new Scanner(System.in)) {
            String query = "INSERT INTO students(name, age, marks) VALUES (?,?,?)";
             PreparedStatement pr=connection.prepareStatement(query);
          //  Statement statement = connection.createStatement();
          
            while (true) {
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter age: ");
                int age = sc.nextInt();
                System.out.print("Enter marks: ");
                double marks = sc.nextDouble();
                
//                String query = String.format("INSERT INTO students(name, age, marks) VALUES ('%s', %d, %f)", name, age, marks);
  
             //   statement.addBatch(query);

                System.out.print("Enter more Details (Y/N): ");
                String choice = sc.next();
                pr.setString(1,name);
                pr.setInt(2, age);
                pr.setDouble(3,marks);
                
                pr.addBatch();
                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }
            int[] arr = pr.executeBatch();
            System.out.println("Batch execution completed. Number of statements: " + arr.length);
            
            
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]==0)
                {
                    System.out.println("Query:"+i +" Query not Excecuted");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
