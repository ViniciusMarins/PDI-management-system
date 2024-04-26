/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import model.Reuniao;

/**
 *
 * @author leandro
 */
public class DateUtils {
    public static boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);
    }   
    
    public static String convertToDate(Date receivedDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(receivedDate);
    }
    
    public static String convertToDateTime(Date receivedDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(receivedDate);
    }
    
    public static String convertToTime(Date receivedDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(receivedDate);
    }
    
    public static Date gerarHoraToken() {
        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis();
        return new Date(timeInSecs + (6 * 60 * 1000));
    }
    
    public static String converterDataParaTexto(Date data) {
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            Date date = sdfInput.parse(data.toString());
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            DateFormatSymbols dfs = new DateFormatSymbols(new Locale("pt", "BR"));
            String[] meses = dfs.getMonths();
            String dia = converterDiaParaPalavras(calendar.get(Calendar.DAY_OF_MONTH));
            String mes = meses[calendar.get(Calendar.MONTH)];
            String ano = converterAnoParaPalavras(calendar.get(Calendar.YEAR));

            String dataConvertida = dia + " de " + mes + " de dois mil e " + ano;

            return dataConvertida.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return ""; 
        }
    }

    public static String converterDiaParaPalavras(int dia) {
        String[] unidades = {
            "", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove",
            "dez", "onze", "doze", "treze", "catorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"
        };

        String[] dezenas = {
            "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"
        };

        if (dia < 20) {
            return unidades[dia];
        } else {
            int unidade = dia % 10;
            int dezena = dia / 10;
            return dezenas[dezena] + (unidade > 0 ? " e " + unidades[unidade] : "");
        }
    }
    
    public static String converterAnoParaPalavras(int ano) {
    String[] unidades = {
        "", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"
    };
    
    String[] dezenas = {
        "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"
    };

    if (ano < 10) {
        return unidades[ano];
    } else if (ano < 20) {
        return unidades[ano];
    } else {
        int unidade = ano % 10;
        int dezena = (ano / 10) % 10;
        int centena = (ano / 100) % 10;

        String anoEmPalavras = "";

        if (centena > 0) {
            anoEmPalavras += unidades[centena] + "centos";
            if (dezena > 0 || unidade > 0) {
                anoEmPalavras += " e ";
            }
        }

        if (dezena >= 2) {
            anoEmPalavras += dezenas[dezena];
            if (unidade > 0) {
                anoEmPalavras += " e " + unidades[unidade];
            }
        } else if (dezena == 1) {
            anoEmPalavras += unidades[10 + unidade];
        } else if (unidade > 0) {
            anoEmPalavras += unidades[unidade];
        }

        return anoEmPalavras;
    }
}
    
}
