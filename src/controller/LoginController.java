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
    
    private String nome, password; 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //----------------------------FUNCTIONS-------------------//

    public boolean entrar(String nome, String password) {
        setNome(nome);
        setPassword(password);
        
        try {
            cmd = conn.prepareStatement("SELECT nome, pass FROM administradores WHERE nome=? and pass=md5(?)");
            cmd.setString(1, getNome());
            cmd.setString(2, getPassword());
            ResultSet rs = cmd.executeQuery();
            if(rs.next()){
                Agenda agenda = new Agenda();
                agenda.setVisible(true);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        }catch (NullPointerException nl){
            JOptionPane.showMessageDialog(null, "Erro");
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
        setNome(nome);
        setPassword(password);
        
        try {
            cmd = conn.prepareStatement("SELECT nome, pass FROM administradores WHERE nome=? and pass=md5(?)");
            cmd.setString(1, getNome());
            cmd.setString(2, getPassword());
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
