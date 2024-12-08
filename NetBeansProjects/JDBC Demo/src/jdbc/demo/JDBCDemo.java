package jdbc.demo;

import java.sql.*;
public class JDBCDemo {

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
            
            String query= "select * from students";
           ResultSet resultset = statement.executeQuery(query);
           
           while(resultset.next())
           {
               int id=resultset.getInt("id");
               String name=resultset.getString("name");
               int age =resultset.getInt("age");
               double marks=resultset.getDouble("marks");
               System.out.println("ID:"+id);
               System.out.println("NAME:"+name);
               System.out.println("AGE:"+age);
               System.out.println("MARKS:"+marks);
               
           }
           
            
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
}
