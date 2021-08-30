/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.DAO;

import CONN.JConnect;
import ems.beans.UserRoleBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrator
 */
public class UserRoleDAO {

    Connection connect = null;
    JConnect jconnect = new JConnect();

    public int addUserRole(UserRoleBean bean) {

        int status = -1;
        String query = "insert into user_roles values(?,?,?,?)";
        PreparedStatement ps = null;

        connect = jconnect.getNewConnection();

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, bean.getUserRoleId());
            ps.setString(2, bean.getUserRole());
            ps.setString(3, bean.getDescription());
            ps.setString(4, bean.getEntryDate());

            int count = ps.executeUpdate();
            
            if (count == 1) {
                status = 0;
            } else {
                status = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public int updateUserRoleRecord(UserRoleBean bean) {

        int status = -1;
        String query = "update user_roles set user_role = ?, user_role_desc = ? where user_role_id = ?";
        PreparedStatement ps = null;

        connect = jconnect.getNewConnection();

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, bean.getUserRole());
            ps.setString(2, bean.getDescription());
            ps.setString(3, bean.getUserRoleId());

            int count = ps.executeUpdate();
            if (count == 1) {
                status = 0;
                
            } else {
                status = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public int deleteUserRole(String userRoleId) {

        int status = -1;
        String query = "delete from user_roles where user_role_id = ?";
        PreparedStatement ps = null;

        connect = jconnect.getNewConnection();

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, userRoleId);

            int count = ps.executeUpdate();
            if (count == 1) {
                status = 0;
            } else {
                status = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public UserRoleBean getUserRoleRecord(String userRoleId) {

        UserRoleBean bean = new UserRoleBean();

        ResultSet result = null;

        String query = "select * from user_roles where user_role_id = ?";
        PreparedStatement ps = null;

        connect = jconnect.getNewConnection();

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, userRoleId);

            result = ps.executeQuery();
            if (result.next()) {

                bean.setUserRoleId(result.getString("user_role_id"));
                bean.setUserRole(result.getString("user_role"));
                bean.setDescription(result.getString("user_role_desc"));
                bean.setEntryDate(result.getString("entry_date"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bean;
    }
    
     public String getUserRoleName(String userRoleId) {

        String name = "";
         
        ResultSet result = null;

        String query = "select user_role from user_roles where user_role_id = ?";
        PreparedStatement ps = null;

        connect = jconnect.getNewConnection();

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, userRoleId);

            result = ps.executeQuery();
            if (result.next()) {

                name = (result.getString("user_role"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return name;
    }

    //creating an array list to return multiple values
    public List<UserRoleBean> getUserRoles() {

        List<UserRoleBean> userRoles = new ArrayList<UserRoleBean>();

        ResultSet result = null;

        String query = "select * from user_roles";
        Statement statement = null;
        //calling the connection class 
        connect = jconnect.getNewConnection();

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                UserRoleBean bean = new UserRoleBean();

                bean.setUserRoleId(result.getString("user_role_id"));
                bean.setUserRole(result.getString("user_role"));
                bean.setDescription(result.getString("user_role_desc"));
                bean.setEntryDate(result.getString("entry_date"));

                userRoles.add(bean);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userRoles;
    }
    
    public LinkedHashMap getUserRoleMap(){

        LinkedHashMap map = new LinkedHashMap();

        ResultSet result = null;

        String loadQuery = "select * from user_roles";
        Statement statement = null;

        connect = jconnect.getNewConnection();

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(loadQuery);
            while(result.next()){

                String userRoleId = result.getString("user_role_id");
                String userRole = result.getString("user_role");

                map.put(userRoleId, userRole);
            }

        } catch (SQLException ex) {

            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){

            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{

          if(statement!= null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
          if(connect!=null){
                try {
                    connect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
        }
         return map;
    }

    public static void main(String[] args) {

        UserRoleDAO UDAO = new UserRoleDAO();
        UserRoleBean URbean = new UserRoleBean();

        //To Insert Records
        URbean.setUserRoleId("6");
        URbean.setUserRole("User");
        URbean.setDescription("Perform Activities");
        URbean.setEntryDate("2018-12-12");

        int status = UDAO.addUserRole(URbean);
        System.out.println("\tStatus: " + status);

        //To Update Record
//        URbean.setUserRoleId("1");
//        URbean.setUserRole("Admin");
//        URbean.setDescription("Supervise");
//      
//        
//        int status = UDAO.updateUserRoleRecord(URbean);
//        System.out.println("\tStatus: "+status);


        //To Delete Record
//        int status = UDAO.deleteUserRole("1");
//        System.out.println("\tStatus: "+status);        
     

        //To get Record
//        URbean = UDAO.getUserRoleRecord("1");
//
//        System.out.println("\tUser Role Id: "+URbean.getUserRoleId());
//        System.out.println("\tUser Role: "+URbean.getUserRole());
//        System.out.println("\tDescription: "+URbean.getDescription());
//        System.out.println("\tEntry Date: "+URbean.getEntryDate());
//        
//        //To Get List Of Record
//        List<UserRoleBean> userrole = UDAO.getUserRoles();      
//        for (UserRoleBean user : userrole) {
//            
//        System.out.println("\tUser Role Id: "+user.getUserRoleId());
//        System.out.println("\tUser Role: "+user.getUserRole());
//        System.out.println("\tDescription: "+user.getDescription());
//        System.out.println("\tEntry Date: "+user.getEntryDate());
//            
//        System.out.println("*****************************************");
//        System.out.println("");
    }
}

