package br.com.ecall.entidade;

import java.sql.Timestamp;

public class TipoEndereco {

    private Integer id;
    private String nome;
    private Timestamp atualizado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Timestamp getAtualizado() {
        return atualizado;
    }

    public void setAtualizado(Timestamp atualizado) {
        this.atualizado = atualizado;
    }

    @Override
    public String toString() {
        return "TipoEndereco{" + "id=" + id + ", nome=" + nome + ", atualizado=" + atualizado + '}';
    }
    
    

}
