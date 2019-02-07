package utilpacket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utilitario para Leitura e Escrita de Arquivo
 *
 * @author William Bigas Mauro
 * @since 29/08/2018
 */
public class UtilArquivo {

    /**
     * Leitura Simples de arquivo concatenando em Linhas
     *
     * @param enderecoArq String do endereco do arquivo "pasta/pasta.txt"
     * @return String
     * @throws Exception
     */
    public static String lerArq(String enderecoArq) throws Exception {
        File arquivo = new File(enderecoArq);
        if (!arquivo.exists()) {
            return "";
        }
        FileReader leitor = new FileReader(enderecoArq);
        String conteudo;
        try (BufferedReader buffer = new BufferedReader(leitor)) {
            String linha = buffer.readLine();
            conteudo = "";
            while (linha != null) {
                conteudo += linha + "\r\n";
                linha = buffer.readLine();
            }
        }
        return conteudo;
    }

    /**
     * Leitura de arquivo concatentando em linhas e retornando Array
     *
     * @param enderecoArq - String do endereco do arquivo "pasta/pasta.txt"
     * @return String[]
     * @throws Exception
     */
    public static String[] lerArqArray(String enderecoArq) throws Exception {
        String conteudo = lerArq(enderecoArq);
        String[] linhas = conteudo.split("\r\n");
        return linhas;
    }

    /**
     * Leitura de arquivo concatentando em linhas e retornando ArrayList
     *
     * @param enderecoArq - String de endereco do arquivo "pasta/pasta.txt"
     * @return List <String>
     * @throws Exception
     */
    public static List<String> lerArqArrayList(String enderecoArq) throws Exception {
        String conteudo = lerArq(enderecoArq);
        List<String> umaLinha = new ArrayList<>();
        String[] linhas = conteudo.split("\r\n");
        umaLinha.addAll(Arrays.asList(linhas));
        return umaLinha;
    }

    /**
     * Escreve em arquivo, sobreescrevendo o conteudo ja escrito, se existir.
     *
     * @param enderecoArq - String de endereco do arquivo "pasta/pasta.txt" e
     * String de Conteudo
     * @param conteudo
     * @throws Exception
     */
    public static void escreverArq(String enderecoArq, String conteudo) throws Exception {
        FileWriter escritor = new FileWriter(enderecoArq);
        try (BufferedWriter buffer = new BufferedWriter(escritor)) {
            buffer.append(conteudo);
        }
    }

    /**
     * Escreve em arquivo, concatenando uma String
     *
     * @param enderecoArq - String de endereco do arquivo "pasta/pasta.txt" e
     * String de Conteudo
     * @param conteudo
     * @throws Exception
     */
    public static void escreverArqConcat(String enderecoArq, String conteudo) throws Exception {
        FileWriter escritor = new FileWriter(enderecoArq, true);
        try (BufferedWriter buffer = new BufferedWriter(escritor)) {
            buffer.append(conteudo + "\r\n");
        }
    }

    /**
     * Escreve em Arquivo concatenando uma Arraylist
     *
     * @param enderecoArq String de endereco do arquivo "pasta/pasta.txt" e uma
     * Lista de Strings do Conteudo
     * @param conteudo
     * @throws Exception
     */
    public static void escreverArqConcat(String enderecoArq, List<String> conteudo) throws Exception {
        FileWriter escritor = new FileWriter(enderecoArq, true);
        try (BufferedWriter buffer = new BufferedWriter(escritor)) {
            buffer.append(conteudo + "\r\n");
        }
    }

    /**
     * Pegando a quantidade de linhas de um Arquivo
     *
     * @param nomeArquivo - Endereco do Arquivo "pasta/arquivo.txt"
     * @return Integer
     * @throws Exception
     */
    public static Integer getQtdLinhasArquivo(String nomeArquivo) throws Exception {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            return null;
        }
        FileReader leitor = new FileReader(nomeArquivo);
        BufferedReader buffer = new BufferedReader(leitor);
        Integer qtdLinhas = 0;
        String linha = buffer.readLine();
        while (linha != null) {
            qtdLinhas++;
            linha = buffer.readLine();
        }
        return qtdLinhas;
    }
}
