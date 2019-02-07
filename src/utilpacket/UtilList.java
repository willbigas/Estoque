package utilpacket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Utiliarios Arraylist - Ordenar, Mapear, Media e Randomicos.
 *
 * @since 16/09/2018 - Ultima Modificacao
 * @author William Bigas Mauro
 */
public class UtilList {

    /**
     * Ordena uma Arraylist usando Algoritmo BubbleSort
     *
     * @param lista - Recebe como Parametro uma Lista de inteiros
     * @return List<Integer> - Retorna uma Lista de Inteiros
     */
    public static List<Integer> bubbleSort(List<Integer> lista) {
        int aux = 0;
        for (Integer lista1 : lista) {
            for (int j = 0; j < lista.size() - 1; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    aux = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, aux);
                }
            }
        }
        return lista;
    }

    /**
     * Gerar uma lista de um Numero Inteiro Randomico , pode haver repeticoes
     *
     * @param numInicial
     * @param numFinal
     * @param qtdNumero
     * @return List<Integer>
     */
    public static List<Integer> numRandom(Integer numInicial, Integer numFinal, Integer qtdNumero) {
        List<Integer> numA = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < qtdNumero; i++) {
            numA.add(r.nextInt((numFinal + 1) - numInicial) + numInicial);

        }
        return numA;

    }

    /**
     * Gerar uma lista de um Numero Decimal Randomico , pode haver repeticoes
     *
     * @param numInicial
     * @param numFinal
     * @param qtdNumero
     * @return
     */
    public static List<Double> numRandom(Double numInicial, Double numFinal, Integer qtdNumero) {
        List<Double> numA = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < qtdNumero; i++) { //Sequencia da mega sena
            numA.add(numInicial + ((numFinal + 1) - numInicial) * r.nextDouble());

        }
        return numA;
    }

    /**
     * Gerar Lista de numeros Randomicos Inteiros Aleatórios sem Repetições
     *
     * @param Integer numInicial
     * @param Integer numFinal
     * @return List<Integer>
     * @return
     */
    public static List<Integer> numRandomListNoRepeat(Integer numInicial, Integer numFinal) {
        List<Integer> numA = new ArrayList<>();
        for (int i = numInicial; i <= numFinal; i++) {
            numA.add(i);
        }
        Collections.shuffle(numA);
        return numA;
    }

    /**
     * Gerar Lista de numeros Randomicos Decimais Aleatórios sem Repetições
     *
     * @param numInicial
     * @param numFinal
     * @return List<Double>
     */
    public static List<Double> numRandomListNoRepeat(Double numInicial, Double numFinal) {
        List<Double> numA = new ArrayList<>();
        for (Double i = numInicial; i <= numFinal; i++) {
            numA.add(i);
        }
        Collections.shuffle(numA);
        return numA;
    }

    /**
     * Media de um Arraylist de Inteiros
     *
     * @param numeros List <Integer> como parametro
     * @return Integer
     */
    public static Integer media(List<Integer> numeros) {
        Integer quantidade = numeros.size();
        Integer valorT = 0;
        for (int i = 0; i < numeros.size(); i++) {
            valorT = valorT + numeros.get(i);
        }
        Integer calculo = valorT / quantidade;
        return calculo;
    }

    /**
     * Media de um Arraylist de Decimais
     *
     * @param numeros - List <Double> como parametro
     * @return Double retorna um Decimal
     */
    public static Double mediaDec(List<Double> numeros) {
        Integer quantidade = numeros.size();
        Double valorT = 0.0;
        for (int i = 0; i < numeros.size(); i++) {
            valorT = valorT + numeros.get(i);
        }
        Double calculo = valorT / quantidade;
        return calculo;
    }

    /**
     * Faz Contagem de frequencia de valores e retorna um Map < Key,Value >
     *
     * @param objetos Recebe como parametro uma lista de objetos
     * @return Map - Retorna um Map < Key , Value >
     */
    public static Map mapearFreqObj(List objetos) {
        Map<String, Integer> contFreq = new HashMap<>();
        Iterator<Integer> it = objetos.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            int frequency = Collections.frequency(objetos, obj);
            contFreq.put(String.valueOf(obj), frequency);
        }

        return contFreq;

    }

}
