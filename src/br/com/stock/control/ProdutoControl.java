package br.com.stock.control;

import br.com.stock.dao.ProdutoDao;
import br.com.stock.daoimpl.ProdutoDaoImpl;
import br.com.stock.model.CategoriaProduto;
import br.com.stock.model.Produto;
import br.com.stock.view.PainelProdutoCadastro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * Classe de Negocio - Produto - Todas as implementacoes de negocio.
 *
 * @author William
 */
public class ProdutoControl {

    private CategoriaControl CATEGORIA_CONTROL;
    private ProdutoControl PRODUTO_CONTROL;
    private ProdutoDao PRODUTO_DAO = new ProdutoDaoImpl();

    private List<Produto> PRODUTOS_DO_BANCO;

    public ProdutoControl() {
        recebendoProdutoDoBanco();
        CATEGORIA_CONTROL = new CategoriaControl();
    }

    /**
     * Adiciona Produto e Persiste no BD.
     *
     * @return Retorna um Boolean dizendo se houve Sucesso ou não.
     */
    public Boolean adicionar() {
        Produto p = new Produto();
        String resultado = (String) PainelProdutoCadastro.comboCategoria.getSelectedItem();
        System.out.println("Resultado :" + resultado);
        CategoriaProduto catp;
        catp = CATEGORIA_CONTROL.pesquisarCategoriaPorNome(resultado);
        p.setCategoria(catp);
        p.setSku(PainelProdutoCadastro.campoSku.getText());
        p.setNome(PainelProdutoCadastro.campoNomeProduto.getText());
        p.setEan13(PainelProdutoCadastro.campoEan13.getText());
        p.setPrecoUnitario(PainelProdutoCadastro.campoPrecoUnitario.getText());
        if (PainelProdutoCadastro.checkAtivo.isSelected()) {
            p.setAtivo(true);
        } else {
            p.setAtivo(false);
        }
        try {
           return PRODUTO_DAO.inserir(p);
        } catch (Exception exception) {
        }
        return null;
    }

    /**
     * Atualiza Produto - Necessario Ajustar
     *
     * @deprecated - Ainda nao implementei completamente.
     * @param produto
     * @throws Exception
     */
    public void atualizar(Produto produto) throws Exception {
        if (produto.getId() != null) {
            Produto produtoEditar = new Produto();
            produtoEditar = obterId(produto.getId());
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
    public Produto obterId(Integer id) throws Exception {

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
    public void recebendoProdutoDoBanco() {
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
    public List<Produto> pesquisar(String termo) throws Exception {
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
    public boolean excluir(Integer id) throws Exception {

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
    public void excluirProdutoDaTabela(JTable tabelaProduto) {
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
