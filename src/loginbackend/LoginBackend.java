/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginbackend;

import static Message.MessageTable.createMessageTable;
import static Message.MessageTable.sendMessage;
import Notification.NewMessageNotify;
import static Notification.NewMessageNotify.passUsername;
import encryption.AdvancedEncryptionStandard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static loginbackend.DataBase.*;
import static loginbackend.messagesender.getSenderName;


/**
 *
 * @author Aniket
 */

    
    public class LoginBackend {

    /**
     */
   public static void main(String[] args)
   //public LoginBackend()
    { 
       createNewDatabase();
       createEmployeeTable();
       new login().setVisible(true);
    }
    
}
