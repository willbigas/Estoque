package br.com.bwstock;

import br.com.bwstock.control.CategoriaControl;
import br.com.bwstock.dao.CategoriaDao;
import br.com.bwstock.dao.EstoqueMovimentoDao;
import br.com.bwstock.dao.ProdutoDao;
import br.com.bwstock.daoimpl.CategoriaDaoImpl;
import br.com.bwstock.daoimpl.EstoqueMovimentoDaoImpl;
import br.com.bwstock.daoimpl.ProdutoDaoImpl;
import br.com.bwstock.control.ProdutoControl;
import br.com.bwstock.entidade.CategoriaProduto;

/**
 *
 * @author William
 */
public class TestandoPersistenciaBanco {

    ProdutoControl produtoControl;
    public static CategoriaControl categoriaControl = new CategoriaControl();
    public static ProdutoDao produtoDao = new ProdutoDaoImpl();
    public static EstoqueMovimentoDao estoqueMovimentoDao = new EstoqueMovimentoDaoImpl();
    public static CategoriaDao categoriaDao = new CategoriaDaoImpl();

    public static void main(String[] args) throws Exception {
        
        CategoriaProduto catP = categoriaControl.pesquisarCategoriaPorNome("Teste");
        
        System.out.println(catP);
    }

}
