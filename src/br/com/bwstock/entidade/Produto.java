package br.com.bwstock.entidade;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Will
 */
public class Produto {

    private Integer id;
    private String sku;
    private String nome;
    private CategoriaProduto categoria;
    private Integer ean13;
    private Integer qtdEstoque;
    private Double precoUnitario;
    private EstoqueMovimento movEstoque;
    private Boolean ativo;
    private Date dataCadastro;
    private Timestamp atualizado;

    public Produto() {
    }

    public Produto(Integer id, String sku, String nome, CategoriaProduto categoria, Integer ean13, Integer qtdEstoque, Double precoUnitario, EstoqueMovimento movEstoque, Boolean ativo, Date dataCadastro, Timestamp atualizado) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.categoria = categoria;
        this.ean13 = ean13;
        this.qtdEstoque = qtdEstoque;
        this.precoUnitario = precoUnitario;
        this.movEstoque = movEstoque;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.atualizado = atualizado;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEan13() {
        return ean13;
    }

    public void setEan13(Integer ean13) {
        this.ean13 = ean13;
    }
    public void setEan13(String ean13) {
        setEan13(Integer.valueOf(ean13));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    public void setPrecoUnitario(String precoUnitario) {
        setPrecoUnitario(Double.valueOf(precoUnitario));
    }

    public EstoqueMovimento getMovEstoque() {
        return movEstoque;
    }

    public void setMovEstoque(EstoqueMovimento movEstoque) {
        this.movEstoque = movEstoque;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Timestamp getAtualizado() {
        return atualizado;
    }

    public void setAtualizado(Timestamp atualizado) {
        this.atualizado = atualizado;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", sku=" + sku + ", nome=" + nome + ", categoria=" + categoria + ", ean13=" + ean13 + ", qtdEstoque=" + qtdEstoque + ", precoUnitario=" + precoUnitario + ", movEstoque=" + movEstoque + ", ativo=" + ativo + ", dataCadastro=" + dataCadastro + ", atualizado=" + atualizado + '}';
    }

   

}
