/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.DAO;

import CONN.JConnect;
import ems.beans.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class UserDAO {

    JConnect jconnect = new JConnect();
    Connection conn = null;

    public List<UserBean> getAllUsers() {
        List<UserBean> list = new ArrayList<>();

        Statement pstm;
        String query = "select * from users";
        conn = jconnect.getNewConnection();

        try {
            pstm = conn.createStatement();
            ResultSet result = pstm.executeQuery(query);
            while (result.next()) {
                UserBean bean = new UserBean();

                bean.setUserId(result.getInt("user_id"));
                bean.setUserRoleId(result.getString("user_type"));
                bean.setFirstName(result.getString("first_name"));
                bean.setLastName(result.getString("last_name"));
                bean.setUserName(result.getString("user_name"));
                bean.setEmail(result.getString("email"));
                bean.setPassword(result.getString("password"));
                bean.setContact(result.getString("contact_no"));
                bean.setCity(result.getString("city"));
                bean.setAddress(result.getString("address"));
                bean.setLoginStatus(result.getInt("login_status"));
                bean.setEntryDate(result.getString("entry_date"));

                list.add(bean);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return list;
    }

    public String getUserType(String userId) {
        String str = "";
        PreparedStatement pstm;
        conn = jconnect.getNewConnection();

        try {
            pstm = conn.prepareStatement("Select * from users where user_id=?");
            pstm.setInt(1, Integer.parseInt(userId));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                str = rs.getString("user_type");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            str = "error";
        }
        return str;
    }

    public int getUserId(String userName) {
        int str = 0;
        PreparedStatement pstm;
        conn = jconnect.getNewConnection();
        try {
            pstm = conn.prepareStatement("Select * from users where user_name=?");
            pstm.setString(1, userName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                str = rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return str;
    }
    
    public UserBean getUserRecord(String userId) {

        UserBean bean = new UserBean();

        ResultSet result = null;

        String query = "select * from users where user_id = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);

            result = ps.executeQuery();
            if (result.next()) {

                bean.setUserId(result.getInt("user_id"));
                bean.setUserRoleId(result.getString("user_type"));
                bean.setFirstName(result.getString("first_name"));
                bean.setLastName(result.getString("last_name"));
                bean.setUserName(result.getString("user_name"));
                bean.setEmail(result.getString("email"));
                bean.setPassword(result.getString("password"));
                bean.setContact(result.getString("contact_no"));
                bean.setCity(result.getString("city"));
                bean.setAddress(result.getString("address"));
                bean.setLoginStatus(result.getInt("login_status"));
                bean.setEntryDate(result.getString("entry_date"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bean;
    }
    
    
    public UserBean getMyProfile(String email) {

        UserBean bean = new UserBean();

        ResultSet result = null;

        String query = "select * from users where email = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, email);

            result = ps.executeQuery();
            if (result.next()) {

                bean.setUserId(result.getInt("user_id"));
                bean.setUserRoleId(result.getString("user_type"));
                bean.setFirstName(result.getString("first_name"));
                bean.setLastName(result.getString("last_name"));
                bean.setUserName(result.getString("user_name"));
                bean.setEmail(result.getString("email"));
                bean.setPassword(result.getString("password"));
                bean.setContact(result.getString("contact_no"));
                bean.setCity(result.getString("city"));
                bean.setAddress(result.getString("address"));
                bean.setLoginStatus(result.getInt("login_status"));
                bean.setEntryDate(result.getString("entry_date"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bean;
    }
    
    public UserBean getUserDetails(String userId){
         UserBean userDetails=null;
         conn = jconnect.getNewConnection();
         System.out.println("CONN:"+conn);
         
         try {
            String sql="SELECT * from users where user_id=?";
            PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.setString(1, userId);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                UserBean bean = new UserBean();
                
                bean.setUserId(result.getInt("user_id"));
                bean.setUserRoleId(result.getString("user_type"));
                bean.setFirstName(result.getString("first_name"));
                bean.setLastName(result.getString("last_name"));
                bean.setUserName(result.getString("user_name"));
                bean.setEmail(result.getString("email"));
                bean.setPassword(result.getString("password"));
                bean.setContact(result.getString("contact_no"));
                bean.setCity(result.getString("city"));
                bean.setAddress(result.getString("address"));
                bean.setLoginStatus(result.getInt("login_status"));
                bean.setEntryDate(result.getString("entry_date"));
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return userDetails;
     }


    public int delUser(int uid) {
        int status = -1;
        conn = jconnect.getNewConnection();
        try {
            String sql = "DELETE from users where user_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, uid);
            int count = pstm.executeUpdate();
            if (count == 1) {
                status = 0;
                System.out.println("Status:"+status);
            } else {
                status = 1;
                System.out.println("Status:"+status);
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean loginValidate(String userName, String userPass) throws SQLException {
        boolean status = false;
        conn = jconnect.getNewConnection();

        String sql = "SELECT * FROM users\n"
                + "WHERE user_name=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();
        String uname;
        String pass;
        while (rs.next()) {
            uname = rs.getString("user_name");
            pass = rs.getString("password");

            if (pass.equals(userPass)) {
                return true;
            }
        }//end while
        return false;

    }
    
    public int RegisterUser(UserBean bean){
        int status = -1;
        
        String sql="INSERT into users(first_name,last_name,user_name,email,password,user_type,contact_no,city,address,login_status,entry_date) "
                    + "Values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        conn = jconnect.getNewConnection();
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getFirstName());
            ps.setString(2, bean.getLastName());
            ps.setString(3, bean.getUserName());
            ps.setString(4, bean.getEmail());
            ps.setString(5, bean.getPassword());
            ps.setString(6, bean.getUserRoleId());
            ps.setString(7, bean.getContact());
            ps.setString(8, bean.getCity());
            ps.setString(9, bean.getAddress());
            ps.setInt(10, bean.getLoginStatus());
            ps.setString(11, bean.getEntryDate());
            
            int count = ps.executeUpdate();
            if (count == 1) {
                status = 0;
                System.out.println("Status:"+status);
            } else {
                status = 1;
                System.out.println("Status:"+status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public int getPendingLoginRequest() {
        int count = 0;
        ResultSet result = null;

        String query = "SELECT COUNT(*) as total FROM users where login_status = 0";
        Statement stmt = null;

        conn = jconnect.getNewConnection();

        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
            if (result.next()) {
                count = result.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }
    
    public int getCountUsers() {
        int count = 0;
        ResultSet result = null;

        String query = "SELECT COUNT(*) as total FROM users ";
        Statement stmt = null;

        conn = jconnect.getNewConnection();

        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
            if (result.next()) {
                count = result.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }
    
    public static void main(String[] args) {
        
        
        UserDAO dao = new UserDAO();
        System.out.println("Count: "+dao.getCountUsers());
        
//        int status = dao.delUser(7);
//        System.out.println("\tStatus: " + status);
//        
//        UserBean user = dao.getMyProfile("admin@gmail.com");
//        System.out.println("\tUser Role Id: " + user.getUserId());
//        System.out.println("\tUser Role: " + user.getPassword());
//        System.out.println("\tDescription: " + user.getUserRoleId());
//        System.out.println("\tDescription: " + user.getFirstName());
//        System.out.println("\tDescription: " + user.getLastName());
//        System.out.println("\tDescription: " + user.getUserName());
//        System.out.println("\tDescription: " + user.getContact());
//        System.out.println("\tDescription: " + user.getEmail());
//        System.out.println("\tEntry Date: " + user.getAddress());
//
//        UserBean xBean = new UserBean();
//        
//        xBean.setUserId(0);
//        xBean.setPassword("password");
//        xBean.setUserRoleId("UR01");
//        xBean.setFirstName("Precious");
//        xBean.setLastName("Ekwere");
//        xBean.setUserName("P.T Barnum");
//        xBean.setContact("08102578777");
//        xBean.setEmail("tom@gmail.com");
//        xBean.setCity("Ikeja");
//        xBean.setAddress("10,Wewe Street");
//        xBean.setLoginStatus(0);
//        xBean.setEntryDate("2020-10-10");
//
//        int xstatus = dao.RegisterUser(xBean);
//        System.out.println("\tStatus: " + xstatus);
    }
}
