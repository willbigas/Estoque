package br.com.bwstock;

import br.com.bwstock.view.PainelLogin;
import br.com.bwstock.view.PainelPrincipal;
import br.com.bwstock.view.PainelProdutoBusca;
import br.com.bwstock.view.PainelUsuarioBusca;
import br.com.bwstock.view.PainelUsuarioCadastro;
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
        painelPrincipal.setTitle("BW-STOCK - GERENCIADOR DE ESTOQUE");
        painelPrincipal.setSize(800, 600);
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelPrincipal.setVisible(true);
    }
    
    public static void JanelaUsuario() {
        PainelUsuarioBusca painelUsuario = new PainelUsuarioBusca();
        painelUsuario.setTitle("BW-STOCK - USUARIO");
        painelUsuario.setSize(800, 600);
        painelUsuario.setLocationRelativeTo(null);
        painelUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelUsuario.setVisible(true);
    }
    
    public static void JanelaUsuarioEdicao() {
        PainelUsuarioCadastro painelUsuarioEdicao = new PainelUsuarioCadastro();
        painelUsuarioEdicao.setTitle("BW-STOCK - EDICAO DE USUARIO");
        painelUsuarioEdicao.setSize(546, 500);
        painelUsuarioEdicao.setLocationRelativeTo(null);
        painelUsuarioEdicao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelUsuarioEdicao.setVisible(true);
    }
    
    
    public static void JanelaProduto() {
        PainelProdutoBusca painelProduto = new PainelProdutoBusca();
        painelProduto.setTitle("BW-STOCK - PRODUTOS");
        painelProduto.setSize(800, 600);
        painelProduto.setLocationRelativeTo(null);
        painelProduto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelProduto.setVisible(true);
    }
    
}
