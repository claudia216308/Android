package claudia.com.authentification.com.claudia.authentification.outils;


import java.io.BufferedReader;
import java.io.IOException;


public class ConversionJSON {


    public static String convertionEnJson(BufferedReader reader){

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    return "Erreur : Conversion en string impossible";

    }//conversionEnJson
}
