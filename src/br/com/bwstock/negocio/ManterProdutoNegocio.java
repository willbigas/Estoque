package br.com.bwstock.negocio;

import br.com.bwstock.dao.ProdutoDao;
import br.com.bwstock.daoimpl.ProdutoDaoImpl;
import br.com.bwstock.entidade.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class ManterProdutoNegocio {
    
    
        public static List<Produto> PRODUTOS = new ArrayList<>();
        public static ProdutoDao PRODUTO_DAO = new ProdutoDaoImpl();
        
    
    
     public static void adicionar(Produto produto) throws Exception {
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
     
    
}
