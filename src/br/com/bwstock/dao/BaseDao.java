package br.com.bwstock.dao;

import java.util.List;

/**
 *
 * @author William
 */
public interface BaseDao {

    public boolean inserir(Object objeto) throws Exception;

    public boolean update(Object objeto) throws Exception;

    public Object pesquisar(Integer id) throws Exception;

    public List<?> pesquisarTodos() throws Exception;

    public boolean excluir(Integer id) throws Exception;
}
