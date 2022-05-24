/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Mucavel
 */
public class Conexao {
    
    public Connection conexao(){
        
        Connection conn = null;
        try {
            //LOCAL SERVER
            String url = "jdbc:mysql://127.0.0.1:3306/freshlookDB?user=root&password=";
            //REMOTE SERVER
//            String url = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql4489222?user=sql4489222&password=MLTvjMPe7K";
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro! Verifique sua conexão.");
        } 
        return conn;
    } 
}
