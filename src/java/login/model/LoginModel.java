/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.model;

import CONN.JConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class LoginModel {
    
    static Logger Log = Logger.getLogger(LoginModel.class);
    
    Connection connect = null;
    JConnect conn = new JConnect();
    
    public LoginBean authenticateUser(String username, String password) {

        LoginBean bean = new LoginBean();
        ResultSet result = null;
        int status = -1;

        String loadQuery = "select * from users where email = ? AND password = ?";
        PreparedStatement ps = null;
        connect = conn.getNewConnection();
        try {
            ps = connect.prepareStatement(loadQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            result = ps.executeQuery();
            if (result.next()) {

                bean.setUserId(result.getInt("user_id"));
                bean.setPassword(password);
                bean.setUserType(result.getString("user_type"));
                bean.setFirstName(result.getString("first_name"));
                bean.setLastName(result.getString("last_name"));
                bean.setUserName(result.getString("user_name"));
                bean.setEmail(username);
                bean.setContactNo(result.getString("contact_no"));
                bean.setCity(result.getString("city"));
                bean.setAddress(result.getString("address"));
                bean.setLoginStatus(result.getInt("login_status"));
                bean.setEntryDate(result.getString("entry_date"));

                System.out.println(username + ": Login Successful");

            } else {
                bean.setLoginStatus(0);
                System.out.println(username + ": Login Failed");
            }

        } catch (SQLException ex) {
            System.out.println("Exception Message: "+ ex);
        } catch (Exception ex) {
            System.out.println("Exception Message: "+ ex);
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Exception Message: "+ ex);
                }
            }
//          if(connect!=null){
//                try {
//                    connect.close();
//                } catch (SQLException ex) {
//                    Log.error("Exception Message: ",ex);
//                }
//          }
        }

        return bean;
    }
    
    public static void main(String[] args) {
        LoginBean bean = new LoginModel().authenticateUser("admin@gmail.com", "password");
         System.out.println("login status: "+bean.getLoginStatus());
         System.out.println("user id: "+bean.getUserId());
         System.out.println("user role id: "+bean.getUserType());
         System.out.println("first name: "+bean.getFirstName());
         System.out.println("last name: "+bean.getLastName());
         System.out.println("Email: "+bean.getEmail());
         System.out.println("Contact no: "+bean.getContactNo());
    }
}
