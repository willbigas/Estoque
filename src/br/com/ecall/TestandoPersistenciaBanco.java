package br.com.ecall;

import br.com.ecall.dao.TipoTelefoneDao;
import br.com.ecall.daoimpl.TipoTelefoneDaoImpl;
import br.com.ecall.entidade.TipoTelefone;
import java.util.List;

public class TestandoPersistenciaBanco {

    public static void main(String[] args) throws Exception {

        TipoTelefoneDao tipoTelefoneDao = new TipoTelefoneDaoImpl();

        TipoTelefone tt = new TipoTelefone();
        tt.setNome("Telefone Corporativo");
        tt.setId(1);
        List<TipoTelefone> tipoTel = (List<TipoTelefone>)(Object) tipoTelefoneDao.pesquisarTodos();
        System.out.println(tipoTel);
    }
}
