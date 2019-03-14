package br.com.stock.control;

import br.com.stock.BwStock;
import br.com.stock.dao.UsuarioDao;
import br.com.stock.daoimpl.UsuarioDaoImpl;
import br.com.stock.model.Usuario;
import br.com.stock.view.PainelLogin;
import br.com.stock.view.usuario.PainelUsuarioBusca;
import br.com.stock.view.usuario.PainelUsuarioCadastro;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alunos
 */
public class UsuarioControl {

    private UsuarioControl USUARIO_CONTROL;
    private static Integer ID_SELECIONADO;

    public static UsuarioDao USUARIO_DAO = new UsuarioDaoImpl();

    public UsuarioControl() {
    }

    public Boolean adicionarAction() {
        Usuario user = new Usuario();
        user.setId(Integer.MAX_VALUE);
        user.setAtualizado(null);
        user.setLogin(PainelUsuarioCadastro.campoLogin.getText());
        user.setSenha(PainelUsuarioCadastro.campoSenha.getText());
        if (PainelUsuarioCadastro.checkAtivo.isSelected()) {
            user.setAtivo(true);
        } else {
            user.setAtivo(false);
        }
        if (PainelUsuarioCadastro.checkTrocarSenha.isSelected()) {
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

    public Boolean atualizarAction() {
        Usuario u = new Usuario();
        u.setId(ID_SELECIONADO);
        System.out.println(ID_SELECIONADO);
        u.setLogin(PainelUsuarioCadastro.campoLogin.getText());
        u.setSenha(PainelUsuarioCadastro.campoSenha.getText());
        u.setPrimeiroLogin(PainelUsuarioCadastro.checkTrocarSenha.isSelected());
        u.setAtivo(PainelUsuarioCadastro.checkAtivo.isSelected());
        try {
            if (USUARIO_DAO.update(u)) {
                JOptionPane.showMessageDialog(null, "Usuario Atualizado com Sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Não consegui atualizar o usuario");
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Usuario> pesquisar(String termo) {
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

    public static boolean excluir(Integer id) throws Exception {

        List<?> objs = USUARIO_DAO.pesquisarTodos();
        List<Usuario> USUARIOS = (List<Usuario>) (Object) objs;
        for (int i = 0; i < USUARIOS.size(); i++) {
            Usuario user = USUARIOS.get(i);
            if (user.getId().equals(id)) {
                USUARIOS.remove(user); // verificar isso , n faz sentido no projeto
                USUARIO_DAO.excluir(id);
                return true;
            }
        }
        return false;
    }

    public void ListUsuariosTabelaAction() {
        List<Usuario> usuarios = pesquisar("");
        adicionarListaUsuariosTabela(usuarios);

    }

    public static void adicionarListaUsuariosTabela(List<Usuario> usuarios) {
        String[] colunas = {"ID", "USUARIO", "ATIVO", "ATUALIZADO"};
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
        PainelUsuarioBusca.tabelaUsuario.setModel(modelo);
    }

    public void pesquisarUsuarioAction() {
        List<Usuario> usuarios = null;
        try {
            usuarios = pesquisar(PainelUsuarioBusca.campoPesquisar.getText());
        } catch (Exception exception) {
        }
        adicionarListaUsuariosTabela(usuarios);
    }

    public Boolean pesquisarLogin(String termo) {
        List<Usuario> retorno = new ArrayList<>();

        try {
            List<?> objs = USUARIO_DAO.pesquisarTodos();
            List<Usuario> USUARIOS = (List<Usuario>) (Object) objs;
            for (Usuario u : USUARIOS) {
                if (u.getLogin().toLowerCase().equals(termo.toLowerCase())) {
                    return true;
                }
            }

        } catch (Exception exception) {
        }
        return false;
    }

    public Boolean pesquisarSenha(String termo) {
        List<Usuario> retorno = new ArrayList<>();

        try {
            List<?> objs = USUARIO_DAO.pesquisarTodos();
            List<Usuario> USUARIOS = (List<Usuario>) (Object) objs;
            for (Usuario u : USUARIOS) {
                if (u.getSenha().toLowerCase().equals(termo.toLowerCase())) {
                    return true;
                }
            }

        } catch (Exception exception) {
        }
        return false;
    }

    public Boolean verificandoLoginAction() {
        Boolean loginOk = pesquisarLogin(PainelLogin.campoLogin.getText());
        Boolean senhaOk = pesquisarSenha(PainelLogin.campoSenha.getText());
        if (loginOk && senhaOk) {
            return true;
        } else {
            return false;
        }
    }

    public void excluirUsuarioAction() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja Realmente excluir Esse contato?\r\nEsta Ação é irreversivel!", "ATENÇÃO!", dialogButton);

        if (resposta == JOptionPane.NO_OPTION) {
            return;
        } else {
            int linha = PainelUsuarioBusca.tabelaUsuario.getSelectedRow();
            if (linha >= 0) {
                String idContato = (String) PainelUsuarioBusca.tabelaUsuario.getValueAt(linha, 0);
                boolean apagou = false;
                try {
                    apagou = USUARIO_DAO.excluir(Integer.valueOf(idContato));
                    System.out.println(idContato);
                } catch (Exception exception) {
                }
                if (apagou) {
                    JOptionPane.showMessageDialog(null, "Contato excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível excluir o contato , Verifique suas Dependencias");

                }
            }

        }
    }

    public void editarUsuarioAction() {

        int linha = PainelUsuarioBusca.tabelaUsuario.getSelectedRow();
        if (linha >= 0) {
            String idUsuario = (String) PainelUsuarioBusca.tabelaUsuario.getValueAt(linha, 0);
            ID_SELECIONADO = Integer.valueOf(idUsuario);
            Usuario u = null;
            try {
                u = (Usuario) USUARIO_DAO.pesquisar(ID_SELECIONADO);
            } catch (Exception exception) {
            }
            if (u != null) {
                try {
                    mostrandoUsuarioEditado(u);
                } catch (Exception exception) {
                }
            }

        }
    }

    public void mostrandoUsuarioEditado(Usuario u) {
        if (u != null) {
            PainelUsuarioCadastro.campoLogin.setText(u.getLogin());
            PainelUsuarioCadastro.campoSenha.setText(u.getSenha());
            PainelUsuarioCadastro.checkAtivo.setSelected(u.getAtivo());
            PainelUsuarioCadastro.checkTrocarSenha.setSelected(u.getPrimeiroLogin());
            BwStock.JanelaUsuarioEdicao();
        }

    }

    public Boolean seExisteNoBancoAction() {
        List<Usuario> usuariosRecebidos;
        try {
            usuariosRecebidos = (List<Usuario>) (Object) USUARIO_DAO.pesquisarTodos();

            for (Usuario usuariosRecebido : usuariosRecebidos) {
                if (usuariosRecebido.getLogin().toLowerCase().equals(PainelUsuarioCadastro.campoLogin.getText().toLowerCase())) {
                    return true;
                }
            }

        } catch (Exception exception) {
        }
        return null;
    }

}
