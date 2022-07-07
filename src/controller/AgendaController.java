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
import view.ConfirmarID;
import view.Login;
import view.Agendamento;
/**
 *
 * @author Carlos Mucavel
 */
public class AgendaController {
   
    Conexao conexao = new Conexao();
    
    public boolean bloquear() {
        Login login = new Login();
        login.setVisible(true);
        return true;
    }

    public void confirmID() {
        ConfirmarID confID = new ConfirmarID();
        confID.setVisible(true);
    }

    public void sair() {
        System.exit(0);
    }

    public boolean addAdmin(String nome, String password) {
        Connection conn = conexao.conexao();
        try {
            PreparedStatement cmd = conn.prepareStatement("INSERT INTO `administradores`(`nome`, `pass`) VALUES (?,md5(?))");
            cmd.setString(1, nome);
            cmd.setString(2, password);
            cmd.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean removerCliente(int id){
        Connection conn = conexao.conexao();
        
        try {
            PreparedStatement cmd = conn.prepareStatement("DELETE FROM `clientes` WHERE id_cliente = ?");
            cmd.setInt(1, id);
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

    public void agendar() {
        Agendamento agendamento = new Agendamento();
        agendamento.setVisible(true);
    }
    
    
    
}
