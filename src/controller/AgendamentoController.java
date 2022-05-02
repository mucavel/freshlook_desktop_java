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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import model.DadosAgenda;
/**
 *
 * @author Carlos Mucavel
 */

public class AgendamentoController {
    DadosAgenda dadosAgenda = new DadosAgenda();
    Conexao conexao = new Conexao();
    Connection conn = conexao.conexao();
    PreparedStatement cmd;
    ResultSet rs;
    
    
    private boolean isValid(String name){
        return Pattern.matches("|^[\\pL\\s]+$|u", name);
    }
    public boolean dados(String nome, int telefone, int idade, String corte, String preco, String dia, String hora, String observ, String telSub) {  
        dadosAgenda.setNome(nome);
        dadosAgenda.setTelefone(telefone);
        dadosAgenda.setIdade(idade);
        dadosAgenda.setCorte(corte);
        dadosAgenda.setPreco(preco);
        dadosAgenda.setDia(dia);
        dadosAgenda.setHora(hora);
        dadosAgenda.setObserv(observ);
        String telsub = telSub;
        
        
        if(dadosAgenda.getNome() == null || dadosAgenda.getNome().isEmpty() || dadosAgenda.getNome().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha o nome.");
        }else if(!isValid(dadosAgenda.getNome())){
            JOptionPane.showMessageDialog(null, "Nome inválido!");
        }else{
                if(telsub.equals("82") || telsub.equals("83")|| telsub.equals("84") || telsub.equals("85")|| telsub.equals("86") || telsub.equals("87")){
                  
                    if(dadosAgenda.getIdade() <= 5 || dadosAgenda.getIdade() >=80){
                        JOptionPane.showMessageDialog(null, "Idade inválida!");
                    }else{
                        try {
                            cmd = conn.prepareStatement("SELECT `dia`, `hora` FROM `clientes` WHERE dia=? and hora=?");
                            cmd.setString(1, dadosAgenda.getDia());
                            cmd.setString(2, dadosAgenda.getHora());
                            rs = cmd.executeQuery();
                            if(rs.next()){
                                JOptionPane.showMessageDialog(null, "Hora ocupada. Selecione outra hora.");
                            }else{
                                cmd = conn.prepareStatement("INSERT INTO `clientes`(`nome`, `telefone`, `idade`, `corte`, `preco`, `dia`, `hora`, `observ`) VALUES (?,?,?,?,?,?,?,?)");
                                cmd.setString(1, dadosAgenda.getNome());
                                cmd.setInt(2, dadosAgenda.getTelefone());
                                cmd.setInt(3, dadosAgenda.getIdade());
                                cmd.setString(4, dadosAgenda.getCorte());
                                cmd.setString(5, dadosAgenda.getPreco());
                                cmd.setString(6, dadosAgenda.getDia());
                                cmd.setString(7, dadosAgenda.getHora());
                                cmd.setString(8, dadosAgenda.getObserv());
                                cmd.executeUpdate();

                                JOptionPane.showMessageDialog(null, "Agendado com sucesso!");

                                return true;
                                
                            }
                        } catch (SQLException sql) {
                            Logger.getLogger(AgendamentoController.class.getName()).log(Level.SEVERE, null, sql);
                        }
                    }
                }else{
                    numeroErro();     
                }
            }
        return false;
    }

    public void numeroErro() {
       JOptionPane.showMessageDialog(null, "Número inválido!");
    }

    public void idadeErro() {
        JOptionPane.showMessageDialog(null, "Idade inválida!");
    }

    public void agendaErro() {
        JOptionPane.showMessageDialog(null, "Falha ao Agendar!");
    }
    
    public void dataErro(){
        JOptionPane.showMessageDialog(null, "Data inválida!");
    }
    
}
