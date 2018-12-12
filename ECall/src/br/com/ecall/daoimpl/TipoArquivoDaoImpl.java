package br.com.ecall.daoimpl;

import br.com.ecall.SessionFactory;
import br.com.ecall.dao.TipoArquivoDao;
import br.com.ecall.entidade.TipoArquivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TipoArquivoDaoImpl implements TipoArquivoDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        TipoArquivo tipoArquivo = (TipoArquivo) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO TIPOARQUIVO (NOME , ATUALIZADO) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tipoArquivo.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                tipoArquivo.setId(idInserido);
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
        TipoArquivo tipoArquivo = (TipoArquivo) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE TIPOARQUIVO SET NOME = ? , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setString(1, tipoArquivo.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, tipoArquivo.getId());
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
    public TipoArquivo pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOARQUIVO WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Timestamp ts = rs.getTimestamp("atualizado");
                TipoArquivo ta = new TipoArquivo();
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
        List<Object> tipoArquivos = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "select * from tipoarquivo");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TipoArquivo ta = new TipoArquivo();
                ta.setNome(rs.getString("nome"));
                ta.setAtualizado(rs.getTimestamp("atualizado"));
                ta.setId(rs.getInt("id"));
                tipoArquivos.add(ta);
            }
            return tipoArquivos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return tipoArquivos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM TIPOARQUIVO WHERE ID = ? ");
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
