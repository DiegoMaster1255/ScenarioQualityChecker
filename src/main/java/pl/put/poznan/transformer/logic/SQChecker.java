package pl.put.poznan.transformer.logic;


import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.put.poznan.transformer.app.*;

import java.io.File;
import java.io.IOException;
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
            zliczKrokiVisitor v = new zliczKrokiVisitor();
            scenario.accept(v);
            int temp = v.getTotalKroki();
            wynik = "Ilosc krokow = " + String.valueOf(temp);
            System.out.println(temp);
            kon.toJSONLiczba(temp, 0, "output.json");
        }
        else if(funkcja[0].equals("bledneKroki")){
            BledneVisitor v = new BledneVisitor();
            scenario.accept(v);
            List<String> temp = v.getTotalBledne();
            StringBuilder sb = new StringBuilder();
            for(String s : temp){
                sb.append(s);
            }
            wynik = "Bledne kroki: " + sb.toString();
            System.out.println(temp);
            kon.toJSONStringArray(temp, "output.json");
        }
        else if(funkcja[0].equals("slowaKluczowe")){
            kluczoweVisitor v = new kluczoweVisitor();
            scenario.accept(v);
            int temp = v.getTotalKluczowe();
            wynik = "Slowa kluczowe = " + String.valueOf(temp);
            System.out.println(temp);
            kon.toJSONLiczba(temp, 1, "output.json");
        }
        else if(funkcja[0].equals("scenariuszDoPoziomu")){
            DoPoziomuVisitor v = new DoPoziomuVisitor(Integer.valueOf(funkcja[1]));
            scenario.accept(v);
            List<Podscenariusz> temp = v.getDoPoziomu();
            wynik = "Scenariusz do poziomu w pliku output.json";
            kon.toJSONScenariusz(temp, "output.json");
        }
        else if(funkcja[0].equals("scenariuszTekstowo")){
            ZapiszVisitor v = new ZapiszVisitor();
            scenario.accept(v);
            wynik = "Scenariusz zapisano do pliku ZapisanyScenariusz.txt";

        }
        else if(funkcja[0].equals("ileAktorow")){
            AktorzyVisitor v = new AktorzyVisitor();
            scenario.accept(v);
            int temp = v.getIleAktorow();
            wynik = "Kroki z aktorami = " + String.valueOf(temp);
            kon.toJSONLiczba(temp, 1, "output.json");
        }
        else if(funkcja[0].equals("ileSlow")){
            SlowaVisitor v = new SlowaVisitor();
            scenario.accept(v);
            int temp = v.getIleSlow();
            wynik = "Ilosc slow = " + String.valueOf(temp);
            kon.toJSONLiczba(temp, 1, "output.json");
        }
        else if(funkcja[0].equals("szukajAktora")){
            System.out.println(funkcja[1]);
            SzukajAVisitor v = new SzukajAVisitor(funkcja[1]);
            scenario.accept(v);
            List<String> temp = v.getZAktorem();
            StringBuilder sb = new StringBuilder();
            for(String s : temp){
                sb.append(s);
            }
            wynik = "Kroki z aktorem: " + sb.toString();
            System.out.println(temp);
            kon.toJSONStringArray(temp, "output.json");
        }
        else if(funkcja[0].equals("szukajKlucza")){
            System.out.println(funkcja[1]);
            SzukajKVisitor v = new SzukajKVisitor(funkcja[1]);
            scenario.accept(v);
            List<String> temp = v.getZKluczem();
            StringBuilder sb = new StringBuilder();
            for(String s : temp){
                sb.append(s);
            }
            wynik = "Kroki z kluczem: " + sb.toString();
            System.out.println(temp);
            kon.toJSONStringArray(temp, "output.json");
        }


        return wynik;
    }
}