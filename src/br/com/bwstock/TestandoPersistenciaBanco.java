package br.com.bwstock;

import br.com.bwstock.dao.EstoqueMovimentoDao;
import br.com.bwstock.dao.ProdutoDao;
import br.com.bwstock.daoimpl.EstoqueMovimentoDaoImpl;
import br.com.bwstock.daoimpl.ProdutoDaoImpl;
import br.com.bwstock.entidade.EstoqueMovimento;
import java.util.List;

/**
 *
 * @author William
 */
public class TestandoPersistenciaBanco {

    public static ProdutoDao produtoDao = new ProdutoDaoImpl();
    public static EstoqueMovimentoDao estoqueMovimentoDao = new EstoqueMovimentoDaoImpl();

    public static void main(String[] args) throws Exception{
//
//        String teste = "21/11/1997";
//        
//       Date teste2 = utilpacket.UtilFormat.data(teste);
//        
//        EstoqueMovimento em = new EstoqueMovimento();
//        em.setAtualizado(null);
//        em.setDataEntrada(teste2);
//        em.setDataSaida(teste2);
//        em.setId(1);
        List<EstoqueMovimento> teste = (List<EstoqueMovimento>) estoqueMovimentoDao.pesquisarTodos();
        
        System.out.println(teste);
    }

}
