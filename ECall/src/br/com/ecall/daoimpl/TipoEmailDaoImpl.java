package br.com.ecall.daoimpl;

import br.com.ecall.SessionFactory;
import br.com.ecall.dao.TipoAtendimentoDao;
import br.com.ecall.dao.TipoEmailDao;
import br.com.ecall.entidade.TipoAtendimento;
import br.com.ecall.entidade.TipoEmail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TipoEmailDaoImpl implements TipoEmailDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        TipoEmail tipoEmail = (TipoEmail) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO TIPOEMAIL (NOME , ATUALIZADO) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tipoEmail.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                tipoEmail.setId(idInserido);
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
        TipoEmail tipoEmail = (TipoEmail) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE TIPOEMAIL SET NOME = ? , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setString(1, tipoEmail.getNome());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, tipoEmail.getId());
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
    public TipoEmail pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOEMAIL WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Timestamp ts = rs.getTimestamp("atualizado");
                TipoEmail te = new TipoEmail();
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
        List<Object> tipoEmails = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM TIPOEMAIL");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TipoEmail te = new TipoEmail();
                te.setNome(rs.getString("nome"));
                te.setAtualizado(rs.getTimestamp("atualizado"));
                te.setId(rs.getInt("id"));
                tipoEmails.add(te);
            }
            return tipoEmails;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return tipoEmails;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM TIPOEMAIL WHERE ID = ? ");
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
