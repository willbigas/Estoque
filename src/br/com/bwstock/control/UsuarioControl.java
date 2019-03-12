package br.com.bwstock.control;

import br.com.bwstock.dao.UsuarioDao;
import br.com.bwstock.daoimpl.UsuarioDaoImpl;
import br.com.bwstock.entidade.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alunos
 */
public class UsuarioControl {

    public static UsuarioDao USUARIO_DAO = new UsuarioDaoImpl();

    private JTextField campoLogin;
    private JTextField campoSenha;
    private JCheckBox checkAtivo;
    private JCheckBox checkTrocarSenha;
    private JTable tabelaUsuario;
    

    public UsuarioControl(JTextField campoLogin, JTextField campoSenha, JCheckBox checkAtivo, 
            JCheckBox checkTrocarSenha, JTable tabelaUsuario) {
        this.campoLogin = campoLogin;
        this.campoSenha = campoSenha;
        this.checkAtivo = checkAtivo;
        this.checkTrocarSenha = checkTrocarSenha;
        this.tabelaUsuario = tabelaUsuario;
    }

    public static Boolean adicionar(JTextField campoLogin, JTextField campoSenha, JCheckBox checkAtivo, JCheckBox checkTrocarSenha) {
        Usuario user = new Usuario();
        user.setId(1);
        user.setAtualizado(null);
        user.setLogin(campoLogin.getText());
        user.setSenha(campoSenha.getText());
        if (checkAtivo.isSelected()) {
            user.setAtivo(true);
        } else {
            user.setAtivo(false);
        }
        if (checkTrocarSenha.isSelected()) {
            user.setPrimeiroLogin(true);
        } else {
            user.setPrimeiroLogin(false);
        }

        try {
            Boolean inserido = USUARIO_DAO.inserir(user);
            if (inserido == true) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Não consegui gravar.");
                return false;
            }
        } catch (Exception exception) {
            System.out.println("Deu ruim na hora de inserir");
            return false;
        }
    }

    public static List<Usuario> pesquisar(String termo) {
        List<Usuario> retorno = new ArrayList<>();

        try {
            List<?> objs = USUARIO_DAO.pesquisarTodos();
            List<Usuario> USUARIOS = (List<Usuario>) (Object) objs;
            for (Usuario u : USUARIOS) {
                if (u.getLogin().toLowerCase().contains(termo.toLowerCase())) {
                    retorno.add(u);
                }
            }

        } catch (Exception exception) {
        }
        return retorno;
    }
    
    public static void mostrandoUsuariosNaTabela(JTable tabelaUsuario) {
        List<Usuario> usuarios = UsuarioControl.pesquisar("");
        adicionarListaUsuariosTabela(usuarios, tabelaUsuario);
        
    }
    
    
    public static void adicionarListaUsuariosTabela(List<Usuario> usuarios , JTable tabelaUsuario) {
        String[] colunas = {"ID", "USUARIO" , "ATIVO" , "ATUALIZADO"};
        String[][] dados = new String[usuarios.size()][colunas.length];
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario user = usuarios.get(i);
            dados[i][0] = user.getId().toString();
            dados[i][1] = user.getLogin();
            if (user.getAtivo() == true) {
               dados[i][2] = "Sim"; 
            } else {
                dados[i][2] = "Não"; 
            }
            dados[i][3] = String.valueOf(user.getAtualizado());
        }
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
        tabelaUsuario.setModel(modelo);
    }
    
     public static void buscandoUsuariosNaTabela(JTextField campoPesquisar , JTable tabelaUsuario) {
        // TODO add your handling code here:
        
        List<Usuario> usuarios = null;
        try {
            usuarios = UsuarioControl.pesquisar(campoPesquisar.getText());
        } catch (Exception exception) {
        }
        adicionarListaUsuariosTabela(usuarios , tabelaUsuario);
    }

}
