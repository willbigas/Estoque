package br.com.stock.daoimpl;

import br.com.stock.SessionFactory;
import br.com.stock.dao.UsuarioDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import br.com.stock.model.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        Usuario user = (Usuario) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO USUARIO (LOGIN , SENHA , ATIVO"
                    + " , PRIMEIROLOGIN , ATUALIZADO ) VALUES (? , ? , ? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getSenha());
            statement.setBoolean(3, user.getAtivo());
            statement.setBoolean(4, user.getPrimeiroLogin());
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                user.setId(idInserido);
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
        Usuario user = (Usuario) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE USUARIO SET LOGIN = ? , SENHA = ? , ATIVO = ?  , PRIMEIROLOGIN = ?  , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getSenha());
            statement.setBoolean(3, user.getAtivo());
            statement.setBoolean(4, user.getPrimeiroLogin());
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            statement.setInt(6, user.getId());
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
    public Usuario pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM USUARIO WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                Boolean ativo = rs.getBoolean("ativo");
                Boolean primeiroLogin = rs.getBoolean("primeirologin");
                Timestamp ts = rs.getTimestamp("atualizado");
                Usuario user = new Usuario();
                user.setId(id);
                user.setLogin(login);
                user.setSenha(senha);
                user.setAtivo(ativo);
                user.setPrimeiroLogin(primeiroLogin);
                user.setAtualizado(ts);
                return user;
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
        List<Object> usuarios = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM USUARIO");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setAtivo(rs.getBoolean("ativo"));
                user.setPrimeiroLogin(rs.getBoolean("primeirologin"));
                user.setAtualizado(rs.getTimestamp("atualizado"));
                user.setId(rs.getInt("id"));
                usuarios.add(user);
            }
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return usuarios;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM USUARIO WHERE ID = ? ");
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
