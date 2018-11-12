package pl.put.poznan.transformer.logic;


import pl.put.poznan.transformer.app.Konwerter;
import pl.put.poznan.transformer.app.Krok;
import pl.put.poznan.transformer.app.Podscenariusz;
import pl.put.poznan.transformer.app.ScenariuszGlowny;

import org.apache.commons.io.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SQChecker {

    private final String nazwa_pliku;

    public SQChecker(String nazwa_pliku){
        this.nazwa_pliku = nazwa_pliku;
    }

    public ScenariuszGlowny stworzScenariusz()
    {
        int ktoryScenariusz = -1;
        int liczbaKrokow = -1;
        int indeksik = 0;

        File file = new File(nazwa_pliku + ".json");
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

    public String check(String[] funkcja){
        ScenariuszGlowny scenario = stworzScenariusz();
        String wynik = "Bledna funkcja";
        Konwerter kon = new Konwerter();
        if(funkcja[0].equals("zliczKroki")){
            int temp = scenario.ileKrokowMaScenariusz();
            wynik = "Ilosc krokow = " + String.valueOf(temp);
            System.out.println(temp);
            kon.toJSONLiczba(temp, 0, "output.json");
        }
        else if(funkcja[0].equals("bledneKroki")){
            List<String> temp = scenario.bledneKroki();
            StringBuilder sb = new StringBuilder();
            for(String s : temp){
                sb.append(s);
            }
            wynik = "Bledne kroki: " + sb.toString();
            System.out.println(temp);
            kon.toJSONStringArray(temp, "output.json");
        }
        else if(funkcja[0].equals("slowaKluczowe")){
            int temp = scenario.ileSlowKluczowych();
            wynik = "Slowa kluczowe = " + String.valueOf(temp);
            System.out.println(temp);
            kon.toJSONLiczba(temp, 1, "output.json");
        }
        else if(funkcja[0].equals("scenariuszDoPoziomu")){
            List<Podscenariusz> temp = scenario.scenariuszDoPoziomu(Integer.valueOf(funkcja[1]));
            wynik = "Scenariusz do poziomu w pliku output.json";
            kon.toJSONScenariusz(temp, "output.json");
        }
        else if(funkcja[0].equals("scenariuszTekstowo")){
            scenario.zapiszDoPliku();
            wynik = "Scenariusz zapisano do pliku ZapisanyScenariusz.txt";

        }



        return wynik;
    }
}