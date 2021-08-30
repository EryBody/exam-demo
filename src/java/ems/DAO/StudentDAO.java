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
public class StudentDAO {
    Connection connect = null;
    JConnect jconnect = new JConnect();
    
    public int addNewStudent(UserBean bean){
        int status = -1;
        
        String sql="INSERT into users(first_name,last_name,user_name,email,password,user_type,contact_no,city,address,login_status,entry_date) "
                    + "Values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        connect = jconnect.getNewConnection();
        
        try {
            ps = connect.prepareStatement(sql);
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
    
    
    public int updateStudent(UserBean bean){
        int status = -1;
        
       String sql="update users set first_name = ?,last_name = ?,user_name = ?,email = ?,password = ?,user_type=?,contact_no = ?,city = ?,address = ?, login_status=? where user_id =?";
                    
        PreparedStatement ps = null;
        connect = jconnect.getNewConnection();
        
        try {
            ps = connect.prepareStatement(sql);
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
            ps.setInt(11, bean.getUserId());
            
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
    
     public int updateProfile(UserBean bean){
        int status = -1;
        
       String sql="update users set first_name = ?,last_name = ?,user_name = ?,email = ?,contact_no = ?,city = ?,address = ? where user_id =?";
                    
        PreparedStatement ps = null;
        connect = jconnect.getNewConnection();
        
        try {
            ps = connect.prepareStatement(sql);
            ps.setString(1, bean.getFirstName());
            ps.setString(2, bean.getLastName());
            ps.setString(3, bean.getUserName());
            ps.setString(4, bean.getEmail());
            ps.setString(5, bean.getContact());
            ps.setString(6, bean.getCity());
            ps.setString(7, bean.getAddress());
            ps.setInt(8, bean.getUserId());
            
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
    
    public List<UserBean> getStudentList(String userRoleId) {
        List<UserBean> list = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet result = null;
        String query = "select * from users where user_type=?";
        connect = jconnect.getNewConnection();

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, userRoleId);

            result = ps.executeQuery();
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
    
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        UserBean xBean = new UserBean();
        
//        List<UserBean> beans = dao.getStudentList("UR03");
//        
//        for(UserBean  user : beans){
//        System.out.println("\tUser Role Id: " + user.getUserId());
//        System.out.println("\tUser Role: " + user.getPassword());
//        System.out.println("\tDescription: " + user.getUserRoleId());
//        System.out.println("\tDescription: " + user.getFirstName());
//        System.out.println("\tDescription: " + user.getLastName());
//        System.out.println("\tDescription: " + user.getUserName());
//        System.out.println("\tDescription: " + user.getContact());
//        System.out.println("\tDescription: " + user.getEmail());
//        System.out.println("\tEntry Date: " + user.getAddress());
//        }
        
        xBean.setUserId(16);
        xBean.setPassword("password");
        xBean.setUserRoleId("UR01");
        xBean.setFirstName("Precious");
        xBean.setLastName("Ekwere");
        xBean.setUserName("P.T Barnum");
        xBean.setUserRoleId("UR03");
        xBean.setContact("08102578777");
        xBean.setEmail("tom@gmail.com");
        xBean.setCity("Ikeja");
        xBean.setLoginStatus(1);
        xBean.setAddress("10,Wewe Street");

        int status = dao.updateStudent(xBean);
        System.out.println("\tStatus: " + status);
    }
}
