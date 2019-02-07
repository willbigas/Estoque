package utilpacket;

import java.util.Random;

/**
 * Utilitarios Matematicos Simples (Media,Porcentagem,Raiz Quadrada ,Fatorial)
 *
 * @since 16/09/2018
 * @author William Bigas Mauro
 */
public class UtilMath {

    /**
     * Medias de Decimais.
     *
     * @param numeroA
     * @param numeroB
     * @return Double - Retorna uma media em decimal
     */
    public static Double media(Double numeroA, Double numeroB) {
        return (numeroA + numeroB) / 2;
    }

    /**
     * Media de Inteiros
     *
     * @param numeroA
     * @param numeroB
     * @return Integer - Retorna uma Media em Inteiro
     */
    public static Integer media(Integer numeroA, Integer numeroB) {
        return (numeroA + numeroB) / 2;
    }

    /**
     * Calculo de Porcentagem
     *
     * @param valorCalculo
     * @param valorPorcentagem
     * @return Integer and Double
     */
    public static Double porcentagem(Double valorCalculo, Double valorPorcentagem) {
        return (valorCalculo * valorPorcentagem) / 100;
    }

    public static Integer porcentagem(Integer valorCalculo, Integer valorPorcentagem) {
        return (valorCalculo * valorPorcentagem) / 100;
    }

    /**
     * Calculo de Raiz Quadrada
     *
     * @param valorRaiz
     * @return Double
     */
    public static Double raizQuadrada(Integer valorRaiz) {
        Double valorTotal = java.lang.Math.sqrt(valorRaiz);
        return valorTotal;
    }

    /**
     * Gerar Numeros Randomicos Inteiros entre um NumeroInicial e Final, Pode
     * haver Repeticoes de numeros
     *
     * @param numInicial
     * @param numFinal
     * @return Integer
     */
    public static Integer numRandom(Integer numInicial, Integer numFinal) {
        Random gerador = new Random();
        for (int i = 0; i < 1; i++) { //Sequencia da mega sena
            Integer numeroR = gerador.nextInt((numFinal + 1) - numInicial) + numInicial;
            return numeroR;
        }
        return null;
    }

    /**
     * Gerar Numeros Randomicos Decimais entre um NumeroInicial e Final, Pode
     * haver Repeticoes de numeros
     *
     * @param numInicial
     * @param numFinal
     * @return
     */
    public static Double numRandom(Double numInicial, Double numFinal) {
        Random gerador = new Random();
        for (int i = 0; i < 1; i++) { //Sequencia da mega sena
            return numInicial + ((numFinal + 1) - numInicial) * gerador.nextDouble();
        }
        return null;
    }

    /**
     * Calcular Fatorial de um Integer
     *
     * @param numero
     * @return Integer
     */
    public static Integer fatorial(Integer numero) {
        Integer fatorial = 1;

        for (int i = 2; i <= numero; i++) {
            fatorial *= i;
        }

        return fatorial;
    }

    /**
     * Calculo de Fatorial de Decimais
     *
     * @param numero
     * @return Double
     */
    public static Double fatorial(Double numero) {
        Double fatorial = 1.0;

        for (Double i = 2.0; i <= numero; i++) {
            fatorial *= i;
        }

        return fatorial;
    }

    /**
     * Verificar se o numero passado por parametro é primo
     *
     * @param numero - Recebe um Integer como Parametro
     * @return boolean - Retorna true ou false
     */
    public static boolean numeroPrimo(Integer numero) {
        for (int j = 2; j < numero; j++) {
            if (numero % j == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * Converter Celcius em Kelvin
     * @param valor - Recebe como Parametro um Double
     * @return Double - Retorna um Double
     */
    public static Double celciusXKelvin(Double valor) {
        Double kelvin = valor + 273.00;
        return kelvin;

    }
    /**
     * Converter Celcius em Fahrenheit
     * @param valor - Recebe como parametro um Double
     * @return Double - Retorna um Double
     */
    public static Double celciusXFahrenheit(Double valor) {
        Double Fahrenheit = (valor * 1.8) + 32.00;
        return Fahrenheit;

    }

}
