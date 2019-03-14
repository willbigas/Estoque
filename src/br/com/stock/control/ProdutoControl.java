package br.com.stock.control;

import br.com.stock.BwStock;
import br.com.stock.dao.ProdutoDao;
import br.com.stock.daoimpl.ProdutoDaoImpl;
import br.com.stock.model.CategoriaProduto;
import br.com.stock.model.Produto;
import br.com.stock.util.UtilFormat;
import br.com.stock.view.produto.PainelProdutoBusca;
import br.com.stock.view.produto.PainelProdutoCadastro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Classe de Negocio - Produto - Todas as implementacoes de negocio.
 *
 * @author William
 */
public class ProdutoControl {

    private CategoriaControl CATEGORIA_CONTROL;
    private ProdutoControl PRODUTO_CONTROL;
    private ProdutoDao PRODUTO_DAO = new ProdutoDaoImpl();
    private static Integer ID_SELECIONADO;

    private List<Produto> PRODUTOS_DO_BANCO;

    public ProdutoControl() {
        recebendoProdutoDoBanco();
        CATEGORIA_CONTROL = new CategoriaControl();
    }

    /**
     * ADICIONAR PRODUTO - INTERFACE *
     */
    /**
     * Adiciona Produto e Persiste no BD.
     *
     * @return Retorna um Boolean dizendo se houve Sucesso ou não.
     */
    public Boolean adicionarAction() {
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
     * EDITAR PRODUTO - INTERFACE *
     */
    /**
     * Metodo que pega a o ID do Objeto selecionado na tabela, pesquisa no Banco
     * e acessa A Janela de Edit.
     *
     * @return Retorna um Boolean se houve sucesso no edit.
     */
    public Boolean editarAction() {

        int linha = PainelProdutoBusca.tabelaProduto.getSelectedRow();
        if (linha >= 0) {
            String idProduto = (String) PainelProdutoBusca.tabelaProduto.getValueAt(linha, 0);
            ID_SELECIONADO = Integer.valueOf(idProduto);
            Produto p = null;
            try {
                p = (Produto) PRODUTO_DAO.pesquisar(ID_SELECIONADO);
            } catch (Exception exception) {
            }
            if (p != null) {
                try {
                    mostrarProdutoNoPainelEdit(p);
                    return true;
                } catch (Exception exception) {
                }
            }

        }
        return null;
    }

    /**
     * PESQUISAR PRODUTO - INTERFACE *
     */
    /**
     * Metodo que pega o valor de Pesquisa da Tabela e Pesquisa no BD.
     */
    public void pesquisarProdutoAction() {
        List<Produto> produtos = new ArrayList<>();
        try {
            produtos = pesquisar(PainelProdutoBusca.campoPesquisar.getText());

        } catch (Exception exception) {
        }

        atualizaDadosTabelaAction(produtos);
    }

    /**
     * EXCLUIR PRODUTO - INTERFACE *
     */
    /**
     * Metodo que exclui um Produto da Tabela.
     *
     */
    public void excluirProdutoAction() {
        int linha = PainelProdutoBusca.tabelaProduto.getSelectedRow();
        if (linha >= 0) {
            String campoSelecionado = (String) PainelProdutoBusca.tabelaProduto.getValueAt(linha, 0);
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

    /**
     * Funcao que modifica o valor das Labes da Janela de Edicao recebendo como
     * parametro o produto selecionado na tabela.
     *
     * @param p - Recebe como Parametro um Objeto <b>Produto</b>
     */
    public void mostrarProdutoNoPainelEdit(Produto p) {
        if (p != null) {
            try {
                PainelProdutoCadastro.campoSku.setText(p.getSku());
                PainelProdutoCadastro.campoNomeProduto.setText(p.getNome());
                PainelProdutoCadastro.campoEan13.setText(String.valueOf(p.getEan13()));
                if (PainelProdutoCadastro.campoPrecoUnitario.getText() == null) {
                    PainelProdutoCadastro.campoPrecoUnitario.setText("0");
                } else {
                    PainelProdutoCadastro.campoPrecoUnitario.setText(UtilFormat.decimalFormat(p.getPrecoUnitario()));
                }

            } catch (Exception exception) {
            }
            BwStock.JanelaProdutoEdicao();
        }

    }

    /**
     * UTILITARIOS PARA JTABLE - ATUALIZAR DADOS *
     */
    /**
     * Funcao q recarrega os Dados da Tabela puxando do BD.
     */
    public void atualizaDadosTabelaAction() {
        recebendoProdutoDoBanco();
        String[] colunas = {"ID", "SKU", "Nome", "Estoque", "PrecoUN", "EAN13", "Ativo"};
        String[][] dados = new String[PRODUTOS_DO_BANCO.size()][colunas.length];
        for (int i = 0; i < PRODUTOS_DO_BANCO.size(); i++) {
            Produto p = PRODUTOS_DO_BANCO.get(i);
            dados[i][0] = p.getId().toString();
            dados[i][1] = p.getSku();
            dados[i][2] = p.getNome();
            if (p.getQtdEstoque() == 0) {
                dados[i][3] = "0";
            } else {
                dados[i][3] = String.valueOf(p.getQtdEstoque());

            }
            if (p.getPrecoUnitario() == null) {
                dados[i][4] = "Não informado";
            } else {
                dados[i][4] = String.valueOf(p.getPrecoUnitario());

            }
            dados[i][5] = String.valueOf(p.getEan13());
            if (p.getAtivo() == true) {
                dados[i][6] = "Ativo";
            } else {
                dados[i][6] = "Desativado";
            }

        }
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
        PainelProdutoBusca.tabelaProduto.setModel(modelo);
    }

    /**
     * Funcao que atualiza os dados da tabela , só que recebe como parametro a
     * busca realizada pelo campoBusca.
     *
     * @param produtos
     */
    public void atualizaDadosTabelaAction(List<Produto> produtos) {
        String[] colunas = {"SKU", "Nome", "Estoque", "PrecoUN", "EAN13", "Ativo"};
        String[][] dados = new String[produtos.size()][colunas.length];
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            dados[i][0] = p.getId().toString();
            dados[i][1] = p.getSku();
            dados[i][2] = p.getNome();
            if (p.getQtdEstoque() == 0) {
                dados[i][3] = "0";
            } else {
                dados[i][3] = String.valueOf(p.getQtdEstoque());

            }
            if (p.getPrecoUnitario() == null) {
                dados[i][4] = "Não informado";
            } else {
                dados[i][4] = String.valueOf(p.getPrecoUnitario());

            }
            dados[i][5] = String.valueOf(p.getEan13());
            if (p.getAtivo() == true) {
                dados[i][6] = "Ativo";
            } else {
                dados[i][6] = "Desativado";
            }

        }
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
        PainelProdutoBusca.tabelaProduto.setModel(modelo);
    }

    /**
     * METODOS UTILITARIOS PARA QUERYS DO BANCO. *
     */
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

}
