package br.com.bwstock;

import br.com.bwstock.view.PainelLogin;
import br.com.bwstock.view.PainelPrincipal;
import br.com.bwstock.view.PainelProduto;
import br.com.bwstock.view.PainelUsuario;
import br.com.bwstock.view.PainelUsuarioEdicao;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Ecall {
    
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JanelaLogin();
        
    }
    
    public static void JanelaLogin() {
        PainelLogin painelLogin = new PainelLogin();
        painelLogin.setTitle("Acesso do Sistema");
        painelLogin.setSize(400, 400);
        painelLogin.setLocationRelativeTo(null);
        painelLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelLogin.setVisible(true);
    }
    
    public static void JanelaPrincipal() {
        PainelPrincipal painelPrincipal = new PainelPrincipal();
        painelPrincipal.setTitle("E-CALL - GERENCIADOR DE ATENDIMENTOS");
        painelPrincipal.setSize(800, 600);
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelPrincipal.setVisible(true);
    }
    
    public static void JanelaUsuario() {
        PainelUsuario painelUsuario = new PainelUsuario();
        painelUsuario.setTitle("E-CALL - USUARIO");
        painelUsuario.setSize(800, 600);
        painelUsuario.setLocationRelativeTo(null);
        painelUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelUsuario.setVisible(true);
    }
    
    public static void JanelaUsuarioEdicao() {
        PainelUsuarioEdicao painelUsuarioEdicao = new PainelUsuarioEdicao();
        painelUsuarioEdicao.setTitle("E-CALL - EDICAO DE USUARIO");
        painelUsuarioEdicao.setSize(546, 500);
        painelUsuarioEdicao.setLocationRelativeTo(null);
        painelUsuarioEdicao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelUsuarioEdicao.setVisible(true);
    }
    
    
    public static void JanelaTelefone() {
        PainelProduto painelTelefone = new PainelProduto();
        painelTelefone.setTitle("E-CALL - TELEFONES DE CONTATO");
        painelTelefone.setSize(800, 600);
        painelTelefone.setLocationRelativeTo(null);
        painelTelefone.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelTelefone.setVisible(true);
    }
    
}
