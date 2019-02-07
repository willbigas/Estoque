package br.com.bwstock.daoimpl;

import br.com.bwstock.SessionFactory;
import br.com.bwstock.dao.EstoqueMovimentoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import br.com.bwstock.entidade.EstoqueMovimento;
import java.sql.Date;

public class EstoqueMovimentoDaoImpl implements EstoqueMovimentoDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        EstoqueMovimento em = (EstoqueMovimento) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO ESTOQUEMOVIMENTO (DATAENTRADA , DATASAIDA , ATUALIZADO ) VALUES (? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, new Date(em.getDataEntrada().getTime()));
            statement.setDate(2, new Date(em.getDataSaida().getTime()));
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                em.setId(idInserido);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return false;
    }

    @Override
    public boolean update(Object obj) throws Exception {
        EstoqueMovimento em = (EstoqueMovimento) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE ESTOQUEMOVIMENTO SET DATAENTRADA = ? , DATASAIDA = ? , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setDate(1, new Date(em.getDataEntrada().getTime()));
            statement.setDate(2, new Date(em.getDataSaida().getTime()));
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, em.getId());
            int linhasAtualizadas = statement.executeUpdate();
            return linhasAtualizadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return false;
    }

    @Override
    public EstoqueMovimento pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM ESTOQUEMOVIMENTO WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date dataEntrada = rs.getDate("dataEntrada");
                Date dataSaida = rs.getDate("dataSaida");
                Timestamp ts = rs.getTimestamp("atualizado");
                EstoqueMovimento em = new EstoqueMovimento();
                em.setId(id);
                em.setDataEntrada(dataEntrada);
                em.setDataSaida(dataSaida);
                em.setAtualizado(ts);
                return em;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return null;
    }

    @Override
    public List<Object> pesquisarTodos() throws Exception {
        List<Object> estoqueMovimentos = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM ESTOQUEMOVIMENTO");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EstoqueMovimento em = new EstoqueMovimento();
                em.setDataEntrada(rs.getDate("dataEntrada"));
                em.setDataSaida(rs.getDate("dataSaida"));
                em.setAtualizado(rs.getTimestamp("atualizado"));
                em.setId(rs.getInt("id"));
                estoqueMovimentos.add(em);
            }
            return estoqueMovimentos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return estoqueMovimentos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM ESTOQUEMOVIMENTO WHERE ID = ? ");
            statement.setInt(1, id);
            int executeUpdate = statement.executeUpdate();
            return executeUpdate != 0;
        } catch (Exception e) {
            return false;
        } finally {
            conexao.close();
        }
    }

}
