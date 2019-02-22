package br.com.bwstock.entidade;

import java.util.List;

/**
 *
 * @author Alunos
 */
public class Estoque {

    private Integer id;
    private Integer qtdProduto;
    private List<Produto> produtos;

    public Estoque() {
    }

    public Estoque(Integer id, Integer qtdProduto, List<Produto> produtos) {
        this.id = id;
        this.qtdProduto = qtdProduto;
        this.produtos = produtos;
    }

}
