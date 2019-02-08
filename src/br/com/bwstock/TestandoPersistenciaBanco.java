package br.com.bwstock;

import br.com.bwstock.dao.CategoriaDao;
import br.com.bwstock.dao.EstoqueMovimentoDao;
import br.com.bwstock.dao.ProdutoDao;
import br.com.bwstock.daoimpl.CategoriaDaoImpl;
import br.com.bwstock.daoimpl.EstoqueMovimentoDaoImpl;
import br.com.bwstock.daoimpl.ProdutoDaoImpl;
import br.com.bwstock.entidade.CategoriaProduto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author William
 */
public class TestandoPersistenciaBanco {
    
    public static ProdutoDao produtoDao = new ProdutoDaoImpl();
    public static EstoqueMovimentoDao estoqueMovimentoDao = new EstoqueMovimentoDaoImpl();
    public static CategoriaDao categoriaDao = new CategoriaDaoImpl();
    
    public static void main(String[] args) throws Exception {
//
        String teste = "21/11/1997";
//        
        Date teste2 = utilpacket.UtilFormat.data(teste);
//        
//        EstoqueMovimento em = new EstoqueMovimento();
//        em.setAtualizado(null);
//        em.setDataEntrada(teste2);
//        em.setDataSaida(teste2);
//        em.setId(1);
//        List<EstoqueMovimento> teste = (List<EstoqueMovimento>) estoqueMovimentoDao.pesquisarTodos();
//        
//        System.out.println(teste);

//        Produto p = new Produto();
//        p.setDataCadastro(teste2);
//        p.setEan13(123456789);
//        p.setId(2);
//        p.setMovEstoque((EstoqueMovimento) estoqueMovimentoDao.pesquisar(2));
//        p.setAtualizado(null);
//        p.setNome("Produto X");
//        p.setPrecoUnitario(2.80);
//        p.setQtdEstoque(15);
//        p.setSku("AAAAAA");
        CategoriaProduto cp = new CategoriaProduto();
        cp.setNome("2 ALTERADO");
        cp.setDescricao("TEST2E ALTERADO");
        cp.setId(1);
        cp.setAtualizado(null);
        categoriaDao.excluir(2);
        
      List<CategoriaProduto> cprod = (List<CategoriaProduto>) categoriaDao.pesquisarTodos();
        System.out.println(cprod);
        
    }    
    
}
