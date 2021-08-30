/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginModel;

import CONN.JConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;

/**
 *
 * @author o.iyogwoya
 */
public class LoginModel {
    
//   static Logger Log = Logger.getLogger(LoginModel.class);
    
Connection connect = null;
JConnect jConnect = new JConnect();
  
     public LoginBean authenticateUser(String username,String password){
        
        LoginBean bean = new  LoginBean();
        ResultSet result = null;  
        int status = -1;
        
        String loadQuery = "select * from users where username = ? AND upassword = ?";
        PreparedStatement ps = null;
        connect = jConnect.getNewConnection();
        try {
            ps = connect.prepareStatement(loadQuery);
            ps.setString(1, username);            
            ps.setString(2, password);            
            result = ps.executeQuery();
            if(result.next()){
                
                bean.setLoginStatus(0);
                bean.setUsername(username);
                bean.setUserRole(result.getString("user_role_id"));
                bean.setFirstName(result.getString("first_name"));
                bean.setLastName(result.getString("last_name"));
                bean.setGender(result.getString("gender"));
                bean.setMobile(result.getString("mobile"));
                bean.setEmail(result.getString("email"));
                bean.setUserStatus(result.getInt("user_status"));
                bean.setEntryDate(result.getString("entry_date"));
                
//             Log.info(username+": Login Successful");
             
            }
            else{
                bean.setLoginStatus(1);
//                Log.info("Login Failed");
            }
            
        } catch (SQLException ex) {           
//            Log.error("Exception Message: ",ex);
        } catch (Exception ex){            
//            Log.error("Exception Message: ",ex);
        }finally{

          if(ps!= null){
                try {
                    ps.close();
                } catch (SQLException ex) {
//                    Log.error("Exception Message: ",ex);
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
     
     public static void main(String s[]){
         
         LoginBean bean = new LoginModel().authenticateUser("tom.admin", "password");
         System.out.println("status: "+bean.getLoginStatus());
         System.out.println("status: "+bean.getUsername());
         System.out.println("status: "+bean.getUserRole());
         System.out.println("status: "+bean.getFirstName());
         System.out.println("status: "+bean.getLastName());
         System.out.println("status: "+bean.getGender());
         System.out.println("status: "+bean.getMobile());
         System.out.println("status: "+bean.getEmail());
     }
}
