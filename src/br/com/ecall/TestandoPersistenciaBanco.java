package br.com.ecall;

import br.com.ecall.dao.TipoEmailDao;
import br.com.ecall.daoimpl.TipoEmailDaoImpl;
import br.com.ecall.entidade.TipoEmail;

public class TestandoPersistenciaBanco {
    
    public static void main(String[] args) throws Exception{
             TipoEmailDao tipoEmailDao = new TipoEmailDaoImpl();
             
             TipoEmail te = new TipoEmail();
             
             te.setId(1);
             te.setNome("Pessoal");
             tipoEmailDao.update(te);
         
    }
}
