/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ukegbu Azubuike
 */
public class JConnect {
   
    public Connection getNewConnection(){
        
            //Localhost
            Connection conn = null;
            String connectionString ="jdbc:mysql://localhost:3306/exam_system";
            String driver = "com.mysql.jdbc.Driver";
            String username = "root", password = "password";
             
            //Server connection String
            
            try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(connectionString,username,password);
            } catch (SQLException ex) {
                Logger.getLogger(JConnect.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(JConnect.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JConnect.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex){
                Logger.getLogger(JConnect.class.getName()).log(Level.SEVERE, null, ex);
            } catch(Exception ex){
              Logger.getLogger(JConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          return conn;
    }
    
    public static void main(String args []){
        
        JConnect connect = new JConnect();
        System.out.println("Connection Instance: "+connect.getNewConnection());
    } 
}

