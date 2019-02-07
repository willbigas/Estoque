package utilpacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Serializacao de Arquivos - Leitura e Escrita(Obj,List,Integer,Double,String)
 *
 * @since 16/09/2018 - Ultima Modificacao
 * @author William Bigas Mauro
 */
public class UtilSerializable {

    /**
     * Le um objeto serializado através de Stream
     *
     * @param enderecoArq
     * @param objeto
     * @return
     * @throws FileNotFoundException
     * @throws Exception
     */
    public static Object lerSerializando(String enderecoArq, Object objeto) throws FileNotFoundException, Exception {
        FileInputStream arqEntrada = new FileInputStream(enderecoArq);
        try (ObjectInputStream in = new ObjectInputStream(arqEntrada)) {
            objeto = (Object) in.readObject();
        }
        return objeto;
    }

    /**
     * Le uma Lista de Objetos serializando através de Stream
     *
     * @param enderecoArq
     * @param lista
     * @return
     * @throws FileNotFoundException
     * @throws Exception
     */
    public static List lerSerializando(String enderecoArq, List lista) throws FileNotFoundException, Exception {
        FileInputStream arqEntrada = new FileInputStream(enderecoArq);
        try (ObjectInputStream in = new ObjectInputStream(arqEntrada)) {
            lista = (List) in.readObject();
        }
        return lista;
    }

    /**
     * Le uma String de Arquivo Serializado
     *
     * @param enderecoArq
     * @param conteudo
     * @return
     * @throws Exception
     */
    public static String lerSerializando(String enderecoArq, String conteudo) throws Exception {
        String resultado;
        try (FileInputStream arqEntrada = new FileInputStream(enderecoArq)) {
            DataInputStream dadosIn = new DataInputStream(arqEntrada);
            resultado = dadosIn.readUTF();
        }
        return resultado;

    }

    /**
     * Le uma Integer de Arquivo Serializado
     *
     * @param enderecoArq
     * @param conteudo
     * @return
     * @throws Exception
     */
    public static Integer lerSerializando(String enderecoArq, Integer conteudo) throws Exception {
        Integer resultado;
        try (FileInputStream arqEntrada = new FileInputStream(enderecoArq)) {
            DataInputStream dadosIn = new DataInputStream(arqEntrada);
            resultado = dadosIn.readInt();
        }
        return resultado;

    }

    /**
     * Le um Double de Arquivo Serializado
     *
     * @param enderecoArq
     * @param conteudo
     * @return
     * @throws Exception
     */
    public static Double lerSerializando(String enderecoArq, Double conteudo) throws Exception {
        Double resultado;
        try (FileInputStream arqEntrada = new FileInputStream(enderecoArq)) {
            DataInputStream dadosIn = new DataInputStream(arqEntrada);
            resultado = dadosIn.readDouble();
        }
        return resultado;

    }

    /**
     * Escreve um Objeto em Arquivo Serializando.
     *
     * @param enderecoArq
     * @param objeto
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void escreverSerializando(String enderecoArq, Object objeto) throws FileNotFoundException, IOException {
        FileOutputStream arqEntrada = new FileOutputStream(enderecoArq);
        try (ObjectOutputStream out = new ObjectOutputStream(arqEntrada)) {
            out.writeObject(objeto);
        }

    }

    /**
     * Escreve uma Lista de Objetos em Arquivo serializando
     *
     * @param enderecoArq
     * @param lista
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void escreverSerializando(String enderecoArq, List lista) throws FileNotFoundException, IOException {
        FileOutputStream arqEntrada = new FileOutputStream(enderecoArq);
        try (ObjectOutputStream out = new ObjectOutputStream(arqEntrada)) {
            out.writeObject(lista);
        }

    }

    /**
     * Escreve uma String em Arquivo Serializando
     *
     * @param enderecoArq
     * @param conteudo
     * @throws Exception
     */
    public static void escreverSerializando(String enderecoArq, String conteudo) throws Exception {
        try (FileOutputStream arqSaida = new FileOutputStream(enderecoArq)) {
            DataOutputStream dadosOut = new DataOutputStream(arqSaida);
            dadosOut.writeUTF(conteudo);
        }

    }

    /**
     * Escreve um Integer em Arquivo Serializando
     *
     * @param enderecoArq
     * @param conteudo
     * @throws Exception
     */
    public static void escreverSerializando(String enderecoArq, Integer conteudo) throws Exception {
        try (FileOutputStream arqSaida = new FileOutputStream(enderecoArq)) {
            DataOutputStream dadosOut = new DataOutputStream(arqSaida);
            dadosOut.writeInt(conteudo);
        }

    }

}
