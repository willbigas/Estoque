package br.com.bwstock;

import br.com.bwstock.view.PainelLogin;
import br.com.bwstock.view.PainelPrincipal;
import br.com.bwstock.view.PainelProdutoBusca;
import br.com.bwstock.view.PainelProdutoCadastro;
import br.com.bwstock.view.PainelUsuarioBusca;
import br.com.bwstock.view.PainelUsuarioCadastro;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class BwStock {

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

    /**
     * Janelas referente a Usuario
     */
    public static void JanelaUsuario() {
        PainelUsuarioBusca painelUsuario = new PainelUsuarioBusca();
        painelUsuario.setTitle("BW-STOCK - USUARIOS");
        painelUsuario.setSize(800, 600);
        painelUsuario.setLocationRelativeTo(null);
        painelUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelUsuario.setVisible(true);
    }

    public static void JanelaUsuarioEdicao() {
        PainelUsuarioCadastro painelCadastroEdicao = new PainelUsuarioCadastro();
        painelCadastroEdicao.setTitle("BW-STOCK - EDICAO DE USUARIO");
        painelCadastroEdicao.setSize(546, 500);
        painelCadastroEdicao.setLocationRelativeTo(null);
        painelCadastroEdicao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelCadastroEdicao.setVisible(true);
    }

    /**
     * Janelas Referente a Produto
     */
    public static void JanelaProduto() {
        PainelProdutoBusca painelProduto = new PainelProdutoBusca();
        painelProduto.setTitle("BW-STOCK - PRODUTOS");
        painelProduto.setSize(800, 600);
        painelProduto.setLocationRelativeTo(null);
        painelProduto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelProduto.setVisible(true);
    }

    public static void JanelaProdutoEdicao() {
        PainelProdutoCadastro painelProdutoEdicao = new PainelProdutoCadastro();
        painelProdutoEdicao.setTitle("BW-STOCK - EDICAO DE PRODUTOS");
        painelProdutoEdicao.setSize(546, 500);
        painelProdutoEdicao.setLocationRelativeTo(null);
        painelProdutoEdicao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelProdutoEdicao.setVisible(true);
    }

}
