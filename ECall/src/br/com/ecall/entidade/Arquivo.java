package br.com.ecall.entidade;

import java.sql.Timestamp;

/**
 *
 * @author William
 */
public class Arquivo {

    private Integer id;
    private String nome;
    private TipoArquivo tipoArquivo;
    private Byte[] conteudo;
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

    public TipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public Byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(Byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public Timestamp getAtualizado() {
        return atualizado;
    }

    public void setAtualizado(Timestamp atualizado) {
        this.atualizado = atualizado;
    }

}
