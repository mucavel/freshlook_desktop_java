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
            String url = "jdbc:mysql://127.0.0.1/freshlookDB?user=root&password=";
//            String url = "jdbc:mysql://sql303.epizy.com:3306/epiz_31486113_freshlookdb?user=epiz_31486113&password=tX4cN8mxv11mb";
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao conectar a base de dados!");
        } 
        return conn;
    } 
}
