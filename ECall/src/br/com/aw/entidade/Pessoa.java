package br.com.aw.entidade;

import java.sql.Timestamp;
import java.util.List;

public class Pessoa {

    private Integer id;
    private Integer codigoPessoa;
    private List<Endereco> endereços;
    private List<Telefone> telefones;
    private List<Email> enderecos;
    private Boolean ativo;
    private Timestamp atualizado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Integer codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public List<Endereco> getEndereços() {
        return endereços;
    }

    public void setEndereços(List<Endereco> endereços) {
        this.endereços = endereços;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Email> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Email> enderecos) {
        this.enderecos = enderecos;
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

}
