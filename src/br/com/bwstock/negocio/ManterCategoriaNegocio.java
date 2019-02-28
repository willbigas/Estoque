package br.com.bwstock.negocio;

import br.com.bwstock.dao.CategoriaDao;
import br.com.bwstock.daoimpl.CategoriaDaoImpl;
import br.com.bwstock.entidade.CategoriaProduto;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 * Classe de Negocio - ProdutoCategoria - Todas as implementacoes de negocio
 *
 * @author William
 */
public class ManterCategoriaNegocio {

    public static CategoriaDao CATEGORIA_DAO = new CategoriaDaoImpl();

    private JTextField campoCategoria;
    private JTextField campoDescricao;
    private JCheckBox checkAtivo;

    public ManterCategoriaNegocio(JTextField campoCategoria, JTextField campoDescricao, JCheckBox checkAtivo) {
        this.campoCategoria = campoCategoria;
        this.campoDescricao = campoDescricao;
        this.checkAtivo = checkAtivo;
    }

    /**
     * Adicionando uma Nova Categoria no BD
     *
     * @param campoCategoria
     * @param campoDescricao
     * @param checkAtivo
     * @return Retorna um Boolean se Foi gravado ou não.
     */
    public static Boolean addCategoria(JTextField campoCategoria, JTextField campoDescricao, JCheckBox checkAtivo) {
        Boolean inserido = false;
        CategoriaProduto c = new CategoriaProduto();
        c.setNome(campoCategoria.getText());
        c.setDescricao(campoDescricao.getText());
        if (checkAtivo.isSelected()) {
            c.setAtivo(true);
        } else {
            c.setAtivo(false);
        }

        try {
            inserido = CATEGORIA_DAO.inserir(c);

        } catch (Exception exception) {
            inserido = false;
            System.out.println("Não foi possivel Inserir uma Categoria , Verifique!");
        }
        return inserido;
    }

    /**
     * Pesquisando Categoria por nome no BD.
     *
     * @param termo
     * @return
     */
    public static CategoriaProduto pesquisarCategoriaPorNome(String termo) {
        try {
            List<CategoriaProduto> categorias = (List<CategoriaProduto>) CATEGORIA_DAO.pesquisarTodos();
            for (CategoriaProduto categoria : categorias) {
                if (categoria.getNome().equals(termo.toLowerCase())) {
                    return categoria;
                }
            }
        } catch (Exception exception) {
        }
        return null;

    }
}
