package br.com.bwstock.control;

import br.com.bwstock.dao.CategoriaDao;
import br.com.bwstock.daoimpl.CategoriaDaoImpl;
import br.com.bwstock.entidade.CategoriaProduto;
import br.com.bwstock.view.PainelCategoriaCadastro;
import java.util.List;

/**
 * Classe de Negocio - ProdutoCategoria - Todas as implementacoes de negocio
 *
 * @author William
 */
public class CategoriaControl {

    private CategoriaProduto CATEGORIA;
    private CategoriaDao CATEGORIA_DAO = new CategoriaDaoImpl();

    /**
     * Adicionando uma Nova Categoria no BD
     *
     * @return Retorna um Boolean se Foi gravado ou não.
     */
    public Boolean addCategoria() {
        Boolean inserido = false;
        CategoriaProduto c = new CategoriaProduto();
        c.setNome(PainelCategoriaCadastro.campoCategoria.getText());
        c.setDescricao(PainelCategoriaCadastro.campoDescricao.getText());
        if (PainelCategoriaCadastro.checkAtivo.isSelected()) {
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
    public CategoriaProduto pesquisarCategoriaPorNome(String termo) {
        try {
            List<CategoriaProduto> categorias = (List<CategoriaProduto>) CATEGORIA_DAO.pesquisarTodos();
            for (CategoriaProduto categoria : categorias) {
                if (categoria.getNome().toLowerCase().equals(termo.toLowerCase())) {
                    return categoria;
                }
            }
        } catch (Exception exception) {
        }
        return null;

    }
}
