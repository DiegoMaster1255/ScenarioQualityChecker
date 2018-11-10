package pl.put.poznan.transformer.app;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.util.Collections;
import org.json.*;
import org.springframework.web.accept.ServletPathExtensionContentNegotiationStrategy;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static ScenariuszGlowny stworzScenariusz()
    {
        int ktoryScenariusz = -1;
        int liczbaKrokow = -1;
        int indeksik = 0;

        File file = new File("C:\\Users\\Kiranella\\IdeaProjects\\ScenarioQualityChecker-master\\src\\main\\" +
                "java\\pl\\put\\poznan\\transformer\\app\\plik.json");
        String content = null;
        try {
            content = FileUtils.readFileToString(file, "utf-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        ScenariuszGlowny SC = new ScenariuszGlowny();


        // Convert JSON string to JSONObject
        try {
            JSONObject tomJsonObject = new JSONObject(content);
            //String
            String name = tomJsonObject.getString("Tytul");
            SC.setTytul(tomJsonObject.getString("Tytul"));
            //Array
            JSONArray actors = tomJsonObject.getJSONArray("Aktorzy");
            for (int i = 0; i < actors.length(); i++) {
                SC.getAktorzy().add((String) actors.get(i));
            }

            JSONArray actorsS = tomJsonObject.getJSONArray("Aktorzy systemowi");
            for (int i = 0; i < actorsS.length(); i++) {
                SC.getAktorzySystemowi().add((String) actorsS.get(i));
            }

            ktoryScenariusz = -1;
            int tabulatory = 1;
            String tempString;
            JSONObject scenariuszG = tomJsonObject.getJSONObject("Scenariusz glowny");
            JSONArray scenariuszP = scenariuszG.getJSONArray("Podscenariusze");
            for(int i=0;i<scenariuszP.length();i++){
                JSONObject kroki = scenariuszP.getJSONObject(i);
                JSONArray kroczki = kroki.getJSONArray("kroki");
                ktoryScenariusz++;
                SC.getListaScenariuszy().add(new Podscenariusz());
                SC.getListaScenariuszy().get(ktoryScenariusz).setLiczbaKrokkow(kroczki.length());
                liczbaKrokow = -1;
                //Dla jednego Podscenariusza
                for(int j=0;j<kroczki.length();j++){
                    //System.out.println(kroczki.get(j));
                    liczbaKrokow++;
                    SC.getListaScenariuszy().get(ktoryScenariusz).getListaKrokow().add(new Krok((String)kroczki.get(j)));
                    //szukanie tabulatorow
                    tabulatory = 1;
                    tempString = SC.getListaScenariuszy().get(ktoryScenariusz).getListaKrokow().get(liczbaKrokow).wiersz;
                    while(tempString.indexOf("\t")==0)
                    {
                        tabulatory++;
                        tempString = tempString.substring(1);

                    }
                    SC.getListaScenariuszy().get(ktoryScenariusz).setZagniezdzenie(tabulatory);
                    //sprawdzanie slow kluczy
                    //List<String> klucze = new ArrayList(
                    //       Arrays.asList("IF", "ELSE", "FOR EACH"));

                        while (tempString.indexOf("IF") == 0) {
                            SC.getListaScenariuszy().get(ktoryScenariusz).setSlowoKlucz("IF");
                            indeksik = tempString.indexOf(" ");
                            tempString = tempString.substring(indeksik + 1);
                        }
                        while (tempString.indexOf("ELSE") == 0) {
                            SC.getListaScenariuszy().get(ktoryScenariusz).setSlowoKlucz("ELSE");
                            indeksik = tempString.indexOf(" ");
                            tempString = tempString.substring(indeksik + 1);
                        }
                        while (tempString.indexOf("FOR EACH") == 0) {
                            SC.getListaScenariuszy().get(ktoryScenariusz).setSlowoKlucz("FOR EACH");
                            indeksik = tempString.indexOf(" ");
                            tempString = tempString.substring(indeksik + 1);
                            indeksik = tempString.indexOf(" ");
                            tempString = tempString.substring(indeksik + 1);
                        }

                    //szukanie aktora kroku
                    for(String aktor : SC.getAktorzy())
                    {
                        if(tempString.indexOf(aktor)==0)
                            SC.getListaScenariuszy().get(ktoryScenariusz).getListaKrokow().get(liczbaKrokow).aktor = aktor;
                    }
                    for(String aktor : SC.getAktorzySystemowi())
                    {
                        if(tempString.indexOf(aktor)==0)
                            SC.getListaScenariuszy().get(ktoryScenariusz).getListaKrokow().get(liczbaKrokow).aktor = aktor;
                    }
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return SC;
    }

    public static void main(String[] args) {
        //SpringApplication.run(TextTransformerApplication.class, args);
        /*
        ScenariuszGlowny SC = stworzScenariusz();
        List<Podscenariusz> lista = new ArrayList<>();
        lista = SC.scenariuszDoPoziomu(2);
        SC.odczytajScenariusz(lista);
        System.out.println(SC.bledneKroki());
        System.out.println(SC.ileSlowKluczowych());
        System.out.println(SC.ileKrokowMaScenariusz());
        SC.zapiszDoPliku();
        */


    }
}
