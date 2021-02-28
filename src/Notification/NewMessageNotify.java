/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notification;

import static Notification.Notification.displayTray;
import java.awt.AWTException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aniket
 */
public class NewMessageNotify extends Thread{
    static String username;
    static boolean flag = true;
    public static void passUsername(String username_pass){
        username = username_pass;
    }
    @Override
     public void run(){
        
        String location = "jdbc:sqlite:"+ System.getProperty("user.dir")+ "\\DataBase\\"+username+".db";
        String should_notify_sql = "SELECT * FROM "+username+" WHERE notification_status = 'N'";
        String after_notify_sql = "UPDATE "+username+" SET notification_status = 'Y' WHERE notification_status = 'N'";
         
        while (flag) {
        try{  
            try (Connection conn = DriverManager.getConnection(location)) {
                PreparedStatement stmt = conn.prepareStatement(should_notify_sql);
                TimeUnit.SECONDS.sleep(1);
                ResultSet rs  = stmt.executeQuery();
                if(rs.next()){
                    displayTray();
                    PreparedStatement astmt = conn.prepareStatement(after_notify_sql);
                    astmt.executeUpdate();
                    
                }
                conn.close();
            } catch (AWTException ex) {
                Logger.getLogger(NewMessageNotify.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(NewMessageNotify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch (SQLException e) {  
            JOptionPane.showMessageDialog(null,e.getMessage());  
        }  
       
    }  
     }
     public void stopRunning()
    {
        flag = false;
    }
}
