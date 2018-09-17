/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Sahan Rajakaruna
 */
public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;
    
    private DbConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        Class.forName("com.mysql.jdbc.Driver");
        File dbPropertifile = new File("resource/application.properties");
        
        FileReader myfile = new FileReader(dbPropertifile);
        
        Properties dbProperties = new Properties();
        dbProperties.load(myfile);
        
        String ip = dbProperties.getProperty("ip");
        String port = dbProperties.getProperty("port");
        String uname = dbProperties.getProperty("username");
        String pass = dbProperties.getProperty("pass");
        String url = "jdbc:mysql://"+ip+":"+port+"/studentReg";
        
        System.out.println(url);
        connection = DriverManager.getConnection(url,uname,pass);
    }
    
    public static DbConnection getInstance() throws Exception{
        if (dbConnection == null){
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    
}
