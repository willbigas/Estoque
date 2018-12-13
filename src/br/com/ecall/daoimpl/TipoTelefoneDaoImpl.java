package br.com.ecall.daoimpl;

import br.com.ecall.SessionFactory;
import br.com.ecall.dao.TipoTelefoneDao;
import br.com.ecall.entidade.TipoEndereco;
import br.com.ecall.entidade.TipoTelefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TipoTelefoneDaoImpl implements TipoTelefoneDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        TipoTelefone tipoTelefone = (TipoTelefone) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO TIPOTELEFONE (NOME , ATUALIZADO) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tipoTelefone.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                tipoTelefone.setId(idInserido);
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
        TipoTelefone tipoTelefone = (TipoTelefone) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE TIPOTELEFONE SET NOME = ? , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setString(1, tipoTelefone.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, tipoTelefone.getId());
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
    public TipoTelefone pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOTELEFONE WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Timestamp ts = rs.getTimestamp("atualizado");
                TipoTelefone tt = new TipoTelefone();
                tt.setNome(nome);
                tt.setAtualizado(ts);
                tt.setId(id);
                return tt;
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
        List<Object> tipoTelefones = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOTELEFONE");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TipoTelefone tt = new TipoTelefone();
                tt.setNome(rs.getString("nome"));
                tt.setAtualizado(rs.getTimestamp("atualizado"));
                tt.setId(rs.getInt("id"));
                tipoTelefones.add(tt);
            }
            return tipoTelefones;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return tipoTelefones;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM TIPOTELEFONE WHERE ID = ? ");
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
