package Jdbc.Demo1;

import java.sql.*;
public class JdbcDemo1 {

  private static final String url="jdbc:mysql://localhost:3306/mydb";    
  private static final  String username="root";
  private static final   String password="Ashish@99";
   
    public static void main(String[] args) {
        try
        {
            
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();
            
            String query= String.format("insert into students(name,age,marks)values('%s',%o,%f)","Ayush",21,87.09);
         int rows= statement.executeUpdate(query);
           
           
           if(rows>0)
           {
               System.out.println("Data inserted succesfuly");
               
           } else
           {
               System.out.println("Data not inserted!");
           }
            
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
}
