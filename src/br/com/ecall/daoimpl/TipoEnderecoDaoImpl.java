package br.com.ecall.daoimpl;

import br.com.ecall.SessionFactory;
import br.com.ecall.dao.TipoEmailDao;
import br.com.ecall.dao.TipoEnderecoDao;
import br.com.ecall.entidade.TipoEmail;
import br.com.ecall.entidade.TipoEndereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TipoEnderecoDaoImpl implements TipoEnderecoDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        TipoEndereco tipoEndereco = (TipoEndereco) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO TIPOENDERECO (NOME , ATUALIZADO) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tipoEndereco.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                tipoEndereco.setId(idInserido);
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
        TipoEndereco tipoEndereco = (TipoEndereco) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE TIPOENDERECO SET NOME = ? , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setString(1, tipoEndereco.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, tipoEndereco.getId());
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
    public TipoEndereco pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOENDERECO WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Timestamp ts = rs.getTimestamp("atualizado");
                TipoEndereco te = new TipoEndereco();
                te.setNome(nome);
                te.setAtualizado(ts);
                te.setId(id);
                return te;
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
        List<Object> tipoEnderecos = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOENDERECO");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TipoEndereco te = new TipoEndereco();
                te.setNome(rs.getString("nome"));
                te.setAtualizado(rs.getTimestamp("atualizado"));
                te.setId(rs.getInt("id"));
                tipoEnderecos.add(te);
            }
            return tipoEnderecos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return tipoEnderecos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM TIPOENDERECO WHERE ID = ? ");
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
