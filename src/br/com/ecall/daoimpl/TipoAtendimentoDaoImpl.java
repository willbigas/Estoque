package br.com.ecall.daoimpl;

import br.com.ecall.SessionFactory;
import br.com.ecall.dao.TipoArquivoDao;
import br.com.ecall.dao.TipoAtendimentoDao;
import br.com.ecall.entidade.TipoArquivo;
import br.com.ecall.entidade.TipoAtendimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TipoAtendimentoDaoImpl implements TipoAtendimentoDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        TipoAtendimento tipoAtendimento = (TipoAtendimento) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO TIPOATENDIMENTO (NOME , ATUALIZADO) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tipoAtendimento.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                tipoAtendimento.setId(idInserido);
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
        TipoAtendimento tipoAtendimento = (TipoAtendimento) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE TIPOATENDIMENTO SET NOME = ? , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setString(1, tipoAtendimento.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, tipoAtendimento.getId());
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
    public TipoAtendimento pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOATENDIMENTO WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Timestamp ts = rs.getTimestamp("atualizado");
                TipoAtendimento ta = new TipoAtendimento();
                ta.setNome(nome);
                ta.setAtualizado(ts);
                ta.setId(id);
                return ta;
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
        List<Object> tipoAtendimentos = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOATENDIMENTO");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TipoAtendimento ta = new TipoAtendimento();
                ta.setNome(rs.getString("nome"));
                ta.setAtualizado(rs.getTimestamp("atualizado"));
                ta.setId(rs.getInt("id"));
                tipoAtendimentos.add(ta);
            }
            return tipoAtendimentos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return tipoAtendimentos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM TIPOATENDIMENTO WHERE ID = ? ");
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
