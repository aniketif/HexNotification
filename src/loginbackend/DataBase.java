package loginbackend;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aniket
 */

import java.sql.PreparedStatement; 
import java.sql.Statement;  
import java.io.File;
import static Message.MessageTable.createMessageTable;
import encryption.AdvancedEncryptionStandard;
import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;  
import java.sql.SQLException;
import java.sql.ResultSet;  
import javax.swing.JOptionPane;

public class DataBase {
    
    public static void createNewDatabase() {  
        File file = new File(System.getProperty("user.dir")+ "\\DataBase");
        String location = "jdbc:sqlite:"+ System.getProperty("user.dir")+ "\\DataBase\\HexaDB.db";  
        file.mkdir();
        try {  
            try (Connection conn = DriverManager.getConnection(location)) {
                DatabaseMetaData meta = conn.getMetaData();
                conn.close();
            }
            
        } 
        catch (SQLException e) {  
            JOptionPane.showMessageDialog(null,e.getMessage());  
        } 
        
    }  

    public static void createEmployeeTable() {  
        // SQLite connection string  
    
        String location = "jdbc:sqlite:"+ System.getProperty("user.dir")+ "\\DataBase\\HexaDB.db"; 
        // SQL statement for creating a new table  
        String sql = "CREATE TABLE IF NOT EXISTS employees (\n"
                +"sr_num INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                +"username TEXT,\n"
                +"password TEXT,\n"
                +"employee_id TEXT,\n"
                +"firstname TEXT,\n"
                +"lastname TEXT,\n"
                +"security_question TEXT,\n"
                +"security_answer TEXT,\n"
                +"position TEXT\n"
                +");";
                 
          
        try{  
            try (Connection conn = DriverManager.getConnection(location)) {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                conn.close();
            }
        } catch (SQLException e) {  
            JOptionPane.showMessageDialog(null,e.getMessage());  
        }  
    }  
 
}

