/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model;
import view.AdicionarADM;
import view.ConfirmarID;
import view.Login;

/**
 *
 * @author Carlos Mucavel
 */
public class AgendaController {
    Login login = new Login();
    AdicionarADM addAdm = new AdicionarADM();
    ConfirmarID confID = new ConfirmarID();
    Model model = new Model();
    Conexao conexao = new Conexao();
    
    
    
    
    public boolean bloquear() {
        login.setVisible(true);
        return true;
    }

    public void confirmID() {
        confID.setVisible(true);
    }

    public void sair() {
        System.exit(0);
    }

    public boolean addAdmin(String nome, String password) {
        Connection conn = conexao.conexao();
        try {
            PreparedStatement cmd = conn.prepareStatement("INSERT INTO `admin`(`nome`, `pass`) VALUES (?,md5(?))");
            cmd.setString(1, model.getNome());
            cmd.setString(2, model.getPassword());
            cmd.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void erro_add() {
        JOptionPane.showMessageDialog(null, "Erro ao adicionar!");
    }
    
    
    
}
