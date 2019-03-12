package br.com.stock;

import br.com.stock.control.CategoriaControl;
import br.com.stock.dao.CategoriaDao;
import br.com.stock.dao.EstoqueMovimentoDao;
import br.com.stock.dao.ProdutoDao;
import br.com.stock.daoimpl.CategoriaDaoImpl;
import br.com.stock.daoimpl.EstoqueMovimentoDaoImpl;
import br.com.stock.daoimpl.ProdutoDaoImpl;
import br.com.stock.control.ProdutoControl;
import br.com.stock.model.CategoriaProduto;

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
