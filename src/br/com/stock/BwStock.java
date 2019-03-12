package br.com.stock;

import br.com.stock.view.PainelCategoriaCadastro;
import br.com.stock.view.PainelLogin;
import br.com.stock.view.PainelPrincipal;
import br.com.stock.view.PainelProdutoBusca;
import br.com.stock.view.PainelProdutoCadastro;
import br.com.stock.view.PainelUsuarioBusca;
import br.com.stock.view.PainelUsuarioCadastro;
import javax.swing.JFrame;

public class BwStock {

    public static void main(String[] args) throws Exception {
        JanelaLogin();

    }

    public static void JanelaLogin() {
        PainelLogin painelLogin = new PainelLogin();
        painelLogin.setTitle("Acesso do Sistema");
        painelLogin.setSize(360, 340);
        painelLogin.setLocationRelativeTo(null);
        painelLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelLogin.setVisible(true);
    }

    public static void JanelaPrincipal() {
        PainelPrincipal painelPrincipal = new PainelPrincipal();
        painelPrincipal.setTitle("BW-STOCK - GERENCIADOR DE ESTOQUE");
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
        painelUsuario.setLocationRelativeTo(null);
        painelUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelUsuario.setVisible(true);
    }

    public static void JanelaUsuarioEdicao() {
        PainelUsuarioCadastro painelCadastroEdicao = new PainelUsuarioCadastro();
        painelCadastroEdicao.setTitle("BW-STOCK - EDICAO DE USUARIO");
        painelCadastroEdicao.setLocationRelativeTo(null);
        painelCadastroEdicao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelCadastroEdicao.setVisible(true);
    }

    /**
     * Janelas Referente a Produto
     */
    public static void JanelaProduto() throws Exception {
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

    public static void JanelaCategoria() {
        PainelCategoriaCadastro painelCategoria = new PainelCategoriaCadastro();
        painelCategoria.setTitle("BW-STOCK - CATEGORIAS");
        painelCategoria.setSize(800, 600);
        painelCategoria.setLocationRelativeTo(null);
        painelCategoria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelCategoria.setVisible(true);
    }

}
