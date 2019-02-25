/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bwstock.negocio;

import br.com.bwstock.dao.UsuarioDao;
import br.com.bwstock.daoimpl.UsuarioDaoImpl;
import br.com.bwstock.entidade.Usuario;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author Alunos
 */
public class ManterUsuarioNegocio {

    public static UsuarioDao USUARIO_DAO = new UsuarioDaoImpl();

    private JTextField campoLogin;
    private JTextField campoSenha;
    private JCheckBox checkAtivo;
    private JCheckBox checkTrocarSenha;

    public ManterUsuarioNegocio(JTextField campoLogin, JTextField campoSenha, JCheckBox checkAtivo, JCheckBox checkTrocarSenha) {
        this.campoLogin = campoLogin;
        this.campoSenha = campoSenha;
        this.checkAtivo = checkAtivo;
        this.checkTrocarSenha = checkTrocarSenha;
    }

    public static Boolean adicionar(JTextField campoLogin, JTextField campoSenha, JCheckBox checkAtivo, JCheckBox checkTrocarSenha) {
        Usuario user = new Usuario();
        user.setLogin(campoLogin.getText());
        user.setSenha(campoSenha.getText());
        if (checkAtivo.isSelected()) {
            user.setAtivo(true);
        } else {
            user.setAtivo(false);
        }
        if (checkTrocarSenha.isSelected()) {
            user.setPrimeiroLogin(true);
        } else {
            user.setPrimeiroLogin(false);
        }
        try {
          Boolean Inserido = USUARIO_DAO.inserir(user);
        } catch (Exception exception) {
            System.out.println("Deu ruim na hora de inserir");
        }
        return null;
    }

}
