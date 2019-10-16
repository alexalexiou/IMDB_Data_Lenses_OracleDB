/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poppulate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class DBCon {
    
     public static Connection openConnection(){ 
         Connection conDriver=null;
         try {
             // Load the Oracle database driver
             DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
             
             /*
             Here is the information needed when connecting to a database
             server. These values are now hard-coded in the program. In
             general, they should be stored in some configuration file and
             read at run time.
             */
             String host = "localhost";
             String port = "1521";
             String dbName = "orcl";
             String userName = "Scott";
             String password = "tiger";
             
             // Construct the JDBC URL
             String dbURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName; 
             return  conDriver = DriverManager.getConnection(dbURL, userName, password);
             
         } catch (SQLException ex) {
             Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
         }
         return conDriver;
      } 
   
      /** 
       * Close the database connection 
       * @param con 
       */ 
      public static void closeConnection(Connection con) { 
          try { 
              con.close(); 
          } catch (SQLException e) { 
              System.err.println("Cannot close connection: " + e.getMessage()); 
          } 
      } 
}
