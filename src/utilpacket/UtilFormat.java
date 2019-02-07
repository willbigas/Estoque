package utilpacket;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilitario para Formatacao de Variaveis - Data, DecimalFormat
 *
 * @since 03/09/2018 - Ultima Modificação
 * @author William Bigas Mauro
 */
public class UtilFormat {

    /**
     * Convertendo String para Data no Formato DD/MM/AAAA
     *
     * @param dataStr
     * @return Date
     * @throws Exception
     */
    public static Date data(String dataStr) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.parse(dataStr);
    }

    /**
     * Convertendo Data para String no Formato DD/MM/AAAA
     *
     * @param data
     * @return String
     * @throws Exception
     */
    public static String data(Date data) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(data);
    }

    /**
     * Formatando um Double em Moeda - "R$ 1,110.00"
     *
     * @param numero
     * @return String
     */
    public static String decimalFormatR$(Double numero) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        return df.format(numero);
    }

    /**
     * Formatando um Integer em Moeda - "R$ 1,110.00"
     *
     * @param numero
     * @return String
     */
    public static String decimalFormatR$(Integer numero) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        return df.format(numero);
    }

    /**
     * Formatando um Decimal em Valor decimal Normal - "1,110.00"
     *
     * @param numero
     * @return
     */
    public static String decimalFormat(Double numero) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("###,###,##0.00");
        return df.format(numero);
    }

    /**
     * Formatando um Inteiro em Valor decimal Normal - "1,110.00"
     *
     * @param numero
     * @return
     */
    public static String decimalFormat(Integer numero) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("###,###,##0.00");
        return df.format(numero);
    }

    /**
     * Formatando Integer em Minutos:Segundos
     *
     * @param numero
     * @return
     */
    public static String mmss(Integer numero) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("mm:ss");
        return sdf.format(numero);
    }

    /**
     * Formatando Double em Minutos:Segundos
     *
     * @param numero
     * @return
     */
    public static String mmss(Double numero) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("mm:ss");
        return sdf.format(numero);
    }

    /**
     * Formatando Integer em Horas:Minutos:Segundos
     *
     * @param numero
     * @return
     */
    public static String hhmmss(Integer numero) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("HH:mm:ss");
        return sdf.format(numero);
    }

    /**
     * Formatando Double em Horas:Minutos:Segundos
     *
     * @param numero
     * @return
     */
    public static String hhmmss(Double numero) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("HH:mm:ss");
        return sdf.format(numero);
    }

}
