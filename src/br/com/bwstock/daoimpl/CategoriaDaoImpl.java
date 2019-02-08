package br.com.bwstock.daoimpl;

import br.com.bwstock.SessionFactory;
import br.com.bwstock.dao.CategoriaDao;
import br.com.bwstock.dao.EstoqueMovimentoDao;
import br.com.bwstock.entidade.CategoriaProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import br.com.bwstock.entidade.EstoqueMovimento;
import java.sql.Date;

public class CategoriaDaoImpl implements CategoriaDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        CategoriaProduto categoriaProd = (CategoriaProduto) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO CATEGORIAPRODUTO (NOME , DESCRICAO , ATUALIZADO ) VALUES (? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, categoriaProd.getNome());
            statement.setString(2, categoriaProd.getDescricao());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                categoriaProd.setId(idInserido);
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
        CategoriaProduto categoriaProd = (CategoriaProduto) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE CATEGORIAPRODUTO SET NOME = ? , DESCRICAO = ? , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setString(1, categoriaProd.getNome());
            statement.setString(2, categoriaProd.getDescricao());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, categoriaProd.getId());
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
    public CategoriaProduto pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM CATEGORIAPRODUTO WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                Timestamp ts = rs.getTimestamp("atualizado");
                CategoriaProduto cp = new CategoriaProduto();
                cp.setId(id);
                cp.setNome(nome);
                cp.setDescricao(descricao);
                cp.setAtualizado(ts);
                return cp;
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
        List<Object> categoriaProdutos = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM CATEGORIAPRODUTO");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CategoriaProduto cp = new CategoriaProduto();
                cp.setNome(rs.getString("nome"));
                cp.setDescricao(rs.getString("descricao"));
                cp.setAtualizado(rs.getTimestamp("atualizado"));
                cp.setId(rs.getInt("id"));
                categoriaProdutos.add(cp);
            }
            return categoriaProdutos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return categoriaProdutos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM CATEGORIAPRODUTO WHERE ID = ? ");
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
