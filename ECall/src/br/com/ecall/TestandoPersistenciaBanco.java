package br.com.ecall;

import br.com.ecall.dao.TipoArquivoDao;
import br.com.ecall.daoimpl.TipoArquivoDaoImpl;
import br.com.ecall.entidade.TipoArquivo;
import java.util.List;

public class TestandoPersistenciaBanco {
    
    public static void main(String[] args) throws Exception{
        TipoArquivoDao tipoArquivoDao = new TipoArquivoDaoImpl();
        TipoArquivo ta = new TipoArquivo();
        
//        ta.setId(2);
//        ta.setNome("Tipo Teste 2");
//        
//        tipoArquivoDao.inserir(ta);
        
        List<TipoArquivo>  teste = (List<TipoArquivo>) (Object) tipoArquivoDao.pesquisarTodos();
        for (int i = 0; i < teste.size(); i++) {
            TipoArquivo get = teste.get(i);
            System.out.println(get);
        }
             
         
    }
}
