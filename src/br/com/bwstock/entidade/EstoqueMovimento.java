package br.com.bwstock.entidade;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Will
 */
public class EstoqueMovimento {

    private Integer id;
    private Date dataEntrada;
    private Date dataSaida;
    private Timestamp atualizado;

    public EstoqueMovimento() {
    }

    public EstoqueMovimento(Integer id, Date dataEntrada, Date dataSaida, Timestamp atualizado) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.atualizado = atualizado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Timestamp getAtualizado() {
        return atualizado;
    }

    public void setAtualizado(Timestamp atualizado) {
        this.atualizado = atualizado;
    }

    @Override
    public String toString() {
        return "EstoqueMovimento{" + "id=" + id + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", atualizado=" + atualizado + '}';
    }

}
