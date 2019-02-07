package utilpacket;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Metodos Utilitarios para transmissao de dados via Rede - Swing
 *
 * @author William
 */
public class UtilSocket {

    private static ServerSocket servidor;

    private static Object OBJ_RECEBIDO;

    public static Object getObjRecebido() {
        return OBJ_RECEBIDO;
    }

    public static void setObjRecebido(Object objRecebido) {
        UtilSocket.OBJ_RECEBIDO = objRecebido;
    }

    public static ServerSocket getServidor() {
        return servidor;
    }

    public static void setServidor(ServerSocket servidor) {
        UtilSocket.servidor = servidor;
    }

    public static void iniciarSocketEstatico(Integer porta) throws Exception {
        servidor = new ServerSocket(porta);

    }

    /**
     * Metodo que fica esperando um cliente entrar numa porta especifica para
     * recepcionar objetos
     *
     * @param porta
     * @return Object
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */
    public static Object recebendoDadosViaSocket(Integer porta) throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket servidor = new ServerSocket(porta);
        Socket entradaDados = servidor.accept();
        ObjectInputStream input = new ObjectInputStream(entradaDados.getInputStream());
        Object objRecebido = input.readObject();
        servidor.close();
        entradaDados.close();
        return objRecebido;
    }

    /**
     * Funcao que envia Dados via Socket para um Determinado servidor atraves de
     * um endereco ip e uma porta
     *
     * @param enderecoIp
     * @param porta
     * @param objParaEnviar
     * @throws Exception
     */
    public static void enviandoDadosViaSocket(String enderecoIp, Integer porta, Object objParaEnviar) throws Exception {
        InetAddress endereco = InetAddress.getByName(enderecoIp);
        Socket saidaDados = new Socket(endereco, porta);
        ObjectOutputStream saida = new ObjectOutputStream(saidaDados.getOutputStream()); // saida de objeto da classe cliente
        saida.flush();
        saida.writeObject(objParaEnviar);
        saida.close();
        saidaDados.close(); // fechando acesso ao servidor

    }

    public static void recebendoDadosViaSocketInfinitamente(Integer porta) throws NumberFormatException, HeadlessException, Exception {
        UtilSocket.iniciarSocketEstatico(porta);
        Thread t = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Socket entradaDados = UtilSocket.getServidor().accept();
                        ObjectInputStream input = new ObjectInputStream(entradaDados.getInputStream());
                        OBJ_RECEBIDO = input.readObject(); // Obj Recebido se encontra aqui
                        entradaDados.close();

                    }
                } catch (IOException iOException) {
                } catch (ClassNotFoundException classNotFoundException) {
                }
            }
        };
        t.start();
    }
}
