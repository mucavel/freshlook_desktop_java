/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import model.DadosAgenda;

/**
 *
 * @author Carlos Mucavel
 */

public class AgendamentoController {
    DadosAgenda dadosAgenda = new DadosAgenda();
    
    public boolean isValid(String name){
        return Pattern.matches("[a-zA-Z]+", name);
    }
    public void dados(String nome, int telefone, int idade, String corte, String dia, String observ, String telSub) {   
        dadosAgenda.setNome(nome.replaceAll(" ", ""));
        dadosAgenda.setTelefone(telefone);
        dadosAgenda.setIdade(idade);
        dadosAgenda.setCorte(corte);
        dadosAgenda.setDia(dia);
        dadosAgenda.setObserv(observ);
        String telsub = telSub;
        
        
        if(dadosAgenda.getNome() == null || dadosAgenda.getNome().isEmpty() || dadosAgenda.getNome().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha o nome.");
        }else if(!isValid(dadosAgenda.getNome())){
            JOptionPane.showMessageDialog(null, "Nome inválido!");
        }else if(
                !telsub.equals("82")
                ){
            numberError();
            System.out.println(telsub);
        }else{
            System.out.println("Tudo bem");
        }
        
        
        
        
//        nome.replaceAll(" ", "");
//        if(nome == null || nome.isEmpty() || nome.trim().isEmpty()){
//            JOptionPane.showMessageDialog(null, "Preencha o nome.");
//        }else if(!isValid(nome)){
//            JOptionPane.showMessageDialog(null, "Nome inválido!");
//        }else{
//            dadosAgenda.setNome(nome);
//        }

    }

    public void numberError() {
       JOptionPane.showMessageDialog(null, "Número inválido!");
    }


    
}
