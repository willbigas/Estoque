package br.com.ecall;

import br.com.ecall.view.PainelLogin;
import br.com.ecall.view.PainelPrincipal;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class ECall {

    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JanelaLogin();
        
    }
    
    public static void JanelaLogin() {
        PainelLogin painelLogin = new PainelLogin();
        painelLogin.setTitle("Acesso do Sistema");
        painelLogin.setSize(400, 400);
        painelLogin.setLocationRelativeTo(null);
        painelLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelLogin.setVisible(true);
    }
    
     public static void JanelaPrincipal() {
        PainelPrincipal painelPrincipal = new PainelPrincipal();
        painelPrincipal.setTitle("E-CALL - Gerenciador de Atendimentos");
        painelPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painelPrincipal.setVisible(true);
    }

}
