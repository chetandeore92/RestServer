package com.appd.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

public class JdbcUtil implements  AutoCloseable{
   public Connection con ;
   private Properties properties = null;

   public void open(){

       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
       }
       catch (Exception ex){
           ex.printStackTrace();
       }

   }

   
   public void close() throws Exception {
       //System.out.println("Inside close()");
       try {
           con.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public void getConnection() throws Exception{

	   properties = new Properties();
       try {
           properties.load(getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
       } catch (IOException e) {
          // Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
    	 e.printStackTrace();
       }
        String  url = properties.getProperty("jdbc.url");//"jdbc:mysql://localhost:3306/appd?useSSL=false";
        String user =properties.getProperty("jdbc.username");// "root";
        String password =properties.getProperty("jdbc.password");//"12345678";
       this.con =  DriverManager.getConnection(url,user,password);
   }
}
