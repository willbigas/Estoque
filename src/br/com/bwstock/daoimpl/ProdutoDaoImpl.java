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
import br.com.bwstock.dao.ProdutoDao;
import br.com.bwstock.entidade.EstoqueMovimento;
import br.com.bwstock.entidade.Produto;
import java.sql.Date;
import java.sql.Types;

public class ProdutoDaoImpl implements ProdutoDao {

    EstoqueMovimentoDao ESTOQUE_MOVIMENTO_DAO = new EstoqueMovimentoDaoImpl();

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        Produto produto = (Produto) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("INSERT INTO PRODUTO (SKU , NOME , EAN13 , QTDESTOQUE , PRECOUNITARIO , "
                    + "ID_ESTOQUEMOVIMENTO , DATACADASTRO  , ATIVO , ATUALIZADO) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, produto.getSku());
            statement.setString(2, produto.getNome());
            statement.setInt(3, produto.getEan13());
            if (produto.getQtdEstoque() == null) { // se for nulo
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, produto.getQtdEstoque());
            }
            statement.setDouble(5, produto.getPrecoUnitario());
            if (produto.getMovEstoque()== null) { // se for nulo
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, produto.getMovEstoque().getId());
            }
            statement.setDate(7, new Date(System.currentTimeMillis()));
            statement.setBoolean(8, produto.getAtivo());
            statement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                produto.setId(idInserido);
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
        Produto produto = (Produto) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "UPDATE PRODUTO SET SKU = ? , NOME = ? , EAN13 = ? , "
                    + "QTDESTOQUE = ? , ID_ESTOQUEMOVIMENTO = ? , DATACADASTRO = ?  , ATIVO = ? , ATUALIZADO = ?  WHERE ID = ? ");
            statement.setString(1, produto.getSku());
            statement.setString(2, produto.getNome());
            statement.setInt(3, produto.getEan13());
            statement.setInt(4, produto.getQtdEstoque());
            statement.setInt(5, produto.getMovEstoque().getId());
            statement.setDate(6, new Date(produto.getDataCadastro().getTime())); // essa data não pode ser recriada
            statement.setBoolean(7, produto.getAtivo());
            statement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            statement.setInt(9, produto.getId());
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
    public Produto pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM PRODUTO WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String sku = rs.getString("sku");
                String nome = rs.getString("nome");
                Integer ean13 = rs.getInt("ean13");
                Integer qtdEstoque = rs.getInt("qtdEstoque");
                Integer estoqueMovimentoId = rs.getInt("id_EstoqueMovimento");
                Boolean ativo = rs.getBoolean("ativo");
                Date dataCadastro = rs.getDate("dataCadastro");
                Timestamp ts = rs.getTimestamp("atualizado");
                Produto p = new Produto();
                p.setId(id);
                p.setSku(sku);
                p.setNome(nome);
                p.setEan13(ean13);
                p.setQtdEstoque(qtdEstoque);
                EstoqueMovimento em = (EstoqueMovimento) ESTOQUE_MOVIMENTO_DAO.pesquisar(estoqueMovimentoId);
                p.setMovEstoque(em);
                p.setDataCadastro(dataCadastro);
                p.setAtivo(ativo);
                p.setAtualizado(ts);
                return p;
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
    public List<?> pesquisarTodos() throws Exception {
        List<Object> produtos = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "SELECT * FROM PRODUTO");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setSku(rs.getString("sku"));
                p.setNome(rs.getString("nome"));
                p.setEan13(rs.getInt("ean13"));
                p.setQtdEstoque(rs.getInt("qtdEstoque"));
                Integer idEstoqueMovimento = (rs.getInt("id_EstoqueMovimento"));
                p.setAtivo(rs.getBoolean("ativo"));
                p.setMovEstoque((EstoqueMovimento) ESTOQUE_MOVIMENTO_DAO.pesquisar(idEstoqueMovimento));
                p.setDataCadastro(rs.getDate("dataCadastro"));
                p.setAtualizado(rs.getTimestamp("atualizado"));
                p.setId(rs.getInt("id"));
                produtos.add(p);
            }
            return produtos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return produtos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "DELETE FROM PRODUTO WHERE ID = ? ");
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
