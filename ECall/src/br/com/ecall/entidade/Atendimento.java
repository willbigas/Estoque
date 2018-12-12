package br.com.ecall.entidade;

import java.sql.Timestamp;
import java.util.Date;

public class Atendimento {

    private Integer id;
    private Usuario criador;
    private MeioContato meioContato;
    private TipoAtendimento tipoAtendimento;
    private Date dataInicio;
    private Date dataFim;
    private Timestamp atualizado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public MeioContato getMeioContato() {
        return meioContato;
    }

    public void setMeioContato(MeioContato meioContato) {
        this.meioContato = meioContato;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Timestamp getAtualizado() {
        return atualizado;
    }

    public void setAtualizado(Timestamp atualizado) {
        this.atualizado = atualizado;
    }
    
    
}
