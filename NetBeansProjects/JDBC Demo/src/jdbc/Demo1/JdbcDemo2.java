package Jdbc.Demo1;

import java.sql.*;
public class JdbcDemo2 {

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
            
//            String query= String.format("update students set marks=%f where id=%d",23.6,2);
           String query= "delete from students where id=2";
         int rows= statement.executeUpdate(query);
           
           
           if(rows>0)
           {
//               System.out.println("Data Updated Succesfuly");
               System.out.println("Data Deleted Succesfuly");
               
           } else
           {
//               System.out.println("Data not Updated Inserted!");
               System.out.println("Data not Deleted Inserted!");
           }
            
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
}
