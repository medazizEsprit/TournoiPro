package com.Utils;
import java.sql.*;
public class Datasource {
    private Connection con;
    private static Datasource data;
    private String url = "jdbc:mysql://localhost:3306/tournoipro";
    private String user = "root";
    private String pwd ="";

    private  Datasource (){
        if (con == null)
            try {
                con = DriverManager.getConnection(url,user,pwd);
                System.out.println("Connexion établie");
            }
            catch(SQLException exception){
                System.out.println(exception);
            }
    }
    public static Datasource getInstance(){
        if (data == null)
            data = new Datasource();
        return data;
    }
    public Connection getCon(){

        return con;
    }
}
