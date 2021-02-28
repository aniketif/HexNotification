/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import encryption.AdvancedEncryptionStandard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Aniket
 */
public class MessageTable {
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        public static void createMessageTable(String username) {  
        // SQLite connection string  
    
        String location = "jdbc:sqlite:"+ System.getProperty("user.dir")+ "\\DataBase\\"+username+".db"; 
        // SQL statement for creating a new table  
        String messagetable_sql = "CREATE TABLE IF NOT EXISTS "+username+" (message_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, sendername TEXT, message TEXT, image TEXT, notification_status TEXT)";
        try{  
            try (Connection conn = DriverManager.getConnection(location)) {
                Statement stmt = conn.createStatement();
                stmt.execute(messagetable_sql);
                conn.close();
            }
            
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        
    }
    public static void sendMessage(String encryptedreceiver, String receiver, String sendername, String message, String image, String notification_status) {  
        
        String message_sql = "INSERT INTO "+receiver+" (sendername, message, image, notification_status) VALUES(?,?,?,?)";  
        String receiver_exist = "SELECT * FROM `employees` WHERE `username` = ?";
        String location = "jdbc:sqlite:"+ System.getProperty("user.dir")+ "\\DataBase\\HexaDB.db";
        String reciever_location = "jdbc:sqlite:"+ System.getProperty("user.dir")+ "\\DataBase\\"+receiver+".db";
        
        try{  
            try (Connection conn = DriverManager.getConnection(location)) {
                PreparedStatement stmt = conn.prepareStatement(receiver_exist);
                stmt.setString(1, encryptedreceiver);
                ResultSet rs  = stmt.executeQuery();
                if(rs.next()){
                    createMessageTable(receiver);
                    try (Connection reciever_conn = DriverManager.getConnection(reciever_location)){
                    PreparedStatement pstmt = reciever_conn.prepareStatement(message_sql);
                    pstmt.setString(1, sendername);
                    pstmt.setString(2, message);
                    pstmt.setString(3, image);
                    pstmt.setString(4, notification_status);
                    pstmt.executeUpdate();
                    reciever_conn.close();
                    }
                    catch (SQLException e) {  
                        JOptionPane.showMessageDialog(null,e.getMessage()); 
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Reciever Does Not Exist");
                }
                conn.close();
            }
        } catch (SQLException e) {  
            JOptionPane.showMessageDialog(null,e.getMessage());  
        }  
    }
    
}
