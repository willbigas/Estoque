package br.com.bwstock.negocio;

import br.com.bwstock.dao.ProdutoDao;
import br.com.bwstock.daoimpl.ProdutoDaoImpl;
import br.com.bwstock.entidade.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Classe de Negocio - Produto - Todas as implementacoes de negocio.
 *
 * @author William
 */
public class ManterProdutoNegocio {

    public static ProdutoDao PRODUTO_DAO = new ProdutoDaoImpl();

    public static List<Produto> PRODUTOS_DO_BANCO;

    private JTextField campoEan13;
    private JTextField campoNomeProduto;
    private JTextField campoPrecoUnitario;
    private JTextField campoSku;
    private JCheckBox checkAtivo;
    private JComboBox<String> comboCategoria;
    private JTable tabelaProduto;

    public ManterProdutoNegocio(JTextField campoEan13, JTextField campoNomeProduto, JTextField campoPrecoUnitario, JTextField campoSku, JCheckBox checkAtivo,
            JComboBox<String> comboCategoria, JTable tabelaProduto) {
        this.campoEan13 = campoEan13;
        this.campoNomeProduto = campoNomeProduto;
        this.campoPrecoUnitario = campoPrecoUnitario;
        this.campoSku = campoSku;
        this.checkAtivo = checkAtivo;
        this.comboCategoria = comboCategoria;
        this.tabelaProduto = tabelaProduto;
        recebendoProdutoDoBanco();

    }

    public ManterProdutoNegocio() {
        recebendoProdutoDoBanco();
    }

    /**
     * Adiciona Produto e Persiste no BD.
     *
     * @param campoEan13
     * @param campoNomeProduto
     * @param campoPrecoUnitario
     * @param campoSku
     * @param checkAtivo
     * @param comboCategoria
     */
    public static void adicionar(JTextField campoEan13, JTextField campoNomeProduto, JTextField campoPrecoUnitario,
            JTextField campoSku, JCheckBox checkAtivo, JComboBox<String> comboCategoria) {
        Produto p = new Produto();
        p.setCategoria(ManterCategoriaNegocio.pesquisarCategoriaPorNome((String) comboCategoria.getSelectedItem()));
        p.setSku(campoSku.getText());
        p.setNome(campoNomeProduto.getText());
        p.setEan13(campoEan13.getText());
        p.setPrecoUnitario(campoPrecoUnitario.getText());
        if (checkAtivo.isSelected()) {
            p.setAtivo(true);
        } else {
            p.setAtivo(false);
        }
        try {
            PRODUTO_DAO.inserir(p);
        } catch (Exception exception) {
        }

    }

    /**
     * Atualiza Produto - Necessario Ajustar
     *
     * @deprecated - Ainda nao implementei completamente.
     * @param produto
     * @throws Exception
     */
    public static void atualizar(Produto produto) throws Exception {
        if (produto.getId() != null) {
            Produto produtoEditar = obterId(produto.getId());
            produtoEditar.setNome(produto.getNome());
            produtoEditar.setSku(produto.getSku());
            produtoEditar.setCategoria(produto.getCategoria()); // Implementar pesquisa de Categoria
            produtoEditar.setEan13(produto.getEan13());
            produtoEditar.setPrecoUnitario(produto.getPrecoUnitario());
            produtoEditar.setAtivo(produto.getAtivo());
            return;
        }
    }

    /**
     * Metodo que Retorna um Produto
     *
     * @param id
     * @return
     * @throws Exception
     */
    public static Produto obterId(Integer id) throws Exception {

        List<?> objs = PRODUTO_DAO.pesquisarTodos();
        List<Produto> PRODUTOS = (List<Produto>) (Object) objs;
        for (int i = 0; i < PRODUTOS.size(); i++) {
            Produto contato = PRODUTOS.get(i);
            if (contato.getId().equals(id)) {
                Produto produtoPesquisado = (Produto) PRODUTO_DAO.pesquisar(id);
                PRODUTO_DAO.excluir(id);
                return produtoPesquisado;
            }
        }
        return null;
    }

    /**
     * Metodo que Lista todos os produtos do BD.
     */
    public static void recebendoProdutoDoBanco() {
        try {
            PRODUTOS_DO_BANCO = (List<Produto>) (Object) PRODUTO_DAO.pesquisarTodos();
            System.out.println(PRODUTOS_DO_BANCO);
        } catch (Exception exception) {
        }
    }

    /**
     * Metodo que pesquisa o Produto no BD e retorna uma Lista.
     *
     * @param termo
     * @return
     * @throws Exception
     */
    public static List<Produto> pesquisar(String termo) throws Exception {
        List<Produto> retorno = new ArrayList();
        List<?> objs = PRODUTO_DAO.pesquisarTodos();
        List<Produto> PRODUTOS = (List<Produto>) (Object) objs;

        for (Produto p : PRODUTOS) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase())
                    || p.getSku().toLowerCase().contains(termo.toLowerCase())
                    || p.getEan13().equals(Integer.valueOf(termo))) {
                retorno.add(p);
            }

        }
        return retorno;
    }

    /**
     * Metodo que Exclui um Produto do BD.
     *
     * @param id
     * @return
     * @throws Exception
     */
    public static boolean excluir(Integer id) throws Exception {

        List<?> objs = PRODUTO_DAO.pesquisarTodos();
        List<Produto> produtos = (List<Produto>) (Object) objs;
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getId().equals(id)) {
                produtos.remove(p);
                PRODUTO_DAO.excluir(id);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que exclui um Produto da Tabela.
     *
     * @param tabelaProduto
     */
    public static void excluirProdutoDaTabela(JTable tabelaProduto) {
        int linha = tabelaProduto.getSelectedRow();
        if (linha >= 0) {
            String campoSelecionado = (String) tabelaProduto.getValueAt(linha, 0);
            Integer campoIdProdutoSelecionado = Integer.valueOf(campoSelecionado);
            try {

                Boolean tudoCerto = PRODUTO_DAO.excluir(campoIdProdutoSelecionado);
                if (tudoCerto) {
                    JOptionPane.showMessageDialog(null, "Tipo de Contato Excluido");
                } else {
                    JOptionPane.showMessageDialog(null, "Existem vinculos, favor Excluir os Vinculos");
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Problemas ao Excluir");
            }
        }
    }
}
