package io.muic.ooc.webapp.service;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JohnnyV on 2/14/2017 AD.
 */
public class DatabaseService {

    SecurityService securityService;

    Connection conn;
    private final String SQL_URL = "jdbc:mysql://localhost:3306/ooc_test";
//    private final String SQL_USERNAME = "";

    public DatabaseService() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(SQL_URL, "root", "");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteDB(String uid){
        try {
            PreparedStatement preS = this.conn.prepareStatement("delete from ooc_test.test_table where id = '" + uid + "'  ");
            preS.executeUpdate();

        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateDB(String uid, String usr, String fname){
        try {
            PreparedStatement preS = this.conn.prepareStatement("update ooc_test.test_table SET username = ?, firstname = ? where id = '" + uid + "'  ");
            preS.setString(1,usr);
            preS.setString(2,fname);
            preS.executeUpdate();

        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertDB(String usr, String pwd, String fname){
        try {
            PreparedStatement preS = this.conn.prepareStatement("insert into ooc_test.test_table (username,password,firstname) values (?,?,?);");
            preS.setString(1,usr);
            preS.setString(2,pwd);
            preS.setString(3,fname);
            preS.executeUpdate();

        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Map<String, String> readData(){
        Map<String, String> temp = new HashMap<>();

        try {
            PreparedStatement preS = this.conn.prepareStatement("select * from ooc_test.test_table;");
            ResultSet result = preS.executeQuery();

            while (result.next()){
                String id = result.getString("id");
                String usr = result.getString("username");
                String pwd = result.getString("password");
                String fname = result.getString("firstname");
//                System.out.println("id: " + id + ", username: " + usr + ", pwd: " + pwd + ", fname: " + fname);
                temp.put(usr,pwd);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

//        System.out.println(temp);
        return temp;
    }


}
