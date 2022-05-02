/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.AdicionarADM;
import view.Agenda;


/**
 *
 * @author Carlos Mucavel
 */
public class LoginController {   
    
    Conexao conexao = new Conexao();
    Connection conn = conexao.conexao();
    PreparedStatement cmd;
    
    //----------------------------FUNCTIONS-------------------//

    public boolean entrar(String nomeUser, String password) {
        String nome = nomeUser;
        String pass = password;
        if(nome == null || nome.isEmpty() || nome.trim().isEmpty() || pass == null || pass.isEmpty() || pass.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos campos!");
            return false;
        }else{
            try {
                cmd = conn.prepareStatement("SELECT nome, pass FROM administradores WHERE nome=? and pass=md5(?)");
                cmd.setString(1, nome);
                cmd.setString(2, pass);
                ResultSet rs = cmd.executeQuery();
                if(rs.next()){
                    Agenda agenda = new Agenda();
                    agenda.setVisible(true);
                    return true;
                }
            } catch (SQLException sql) {
                JOptionPane.showMessageDialog(null, "Erro na base de dados");
            }catch (NullPointerException nullpointer){
                JOptionPane.showMessageDialog(null, "Erro!");
            }
  
        }
        return false;
    }

    public void erro_login() {
        JOptionPane.showMessageDialog(null, "Credencias Inválidas!");
    }
    
    public boolean sair() {
        System.exit(0);
        return true;
    }

    public boolean confirm(String nome, String password) {
        String nomeConf = nome;
        String passConf = password;
        
        try {
            cmd = conn.prepareStatement("SELECT nome, pass FROM administradores WHERE nome=? and pass=md5(?)");
            cmd.setString(1, nomeConf);
            cmd.setString(2, passConf);
            ResultSet rs = cmd.executeQuery();
            if(rs.next()){
                AdicionarADM addADM = new AdicionarADM();
                addADM.setVisible(true);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        }
        
        return false;
    }
    
}
