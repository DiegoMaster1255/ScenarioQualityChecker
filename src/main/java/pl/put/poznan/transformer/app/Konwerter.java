package pl.put.poznan.transformer.app;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Klasa z funkcjonalnoscia zapewniajaca konwersje z Javy do Jsona
 */

public class Konwerter {

    /**
     * konwersja liczby typu int na Jsona
     * @param liczba
     * @param typ
     * @param path
     */

    public void toJSONLiczba(int liczba,int typ, String path){
        JSONObject obj = new JSONObject();
        if(typ == 0)
            obj.put("liczba krokow", String.valueOf(liczba));
        else
            obj.put("liczba kluczy", String.valueOf(liczba));
        writeToFile(obj, path);
    }

    /**
     * konwersja listy stringow na Jsona
     * @param lista
     * @param path
     */

    public void toJSONStringArray(List<String> lista, String path){
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        for(int i=0;i<lista.size();i++){
            array.put(lista.get(i));
        }
        obj.put("lista bledow", array);

        writeToFile(obj, path);
    }

    /**
     * konwersja listy Podscenariuszy na Jsona
     * @param scenariusze
     * @param path
     */
    public void toJSONScenariusz(List<Podscenariusz> scenariusze, String path){
        JSONObject obj = new JSONObject();
        JSONArray podscenariusze = new JSONArray();
        for(int i=0;i<scenariusze.size();i++){
            JSONArray kroki = new JSONArray();
            for(int j=0;j<scenariusze.get(i).listaKrokow.size();j++){
                kroki.put(scenariusze.get(i).listaKrokow.get(j).wiersz);
            }

            podscenariusze.put("Kroki: "+ kroki);

        }
        obj.put("podscenariusze", podscenariusze);
        writeToFile(obj, path);

    }

    /**
     * Zapis objektow typu Json
     * @param obj
     * @param path
     */
    private void writeToFile(JSONObject obj, String path){

        try (FileWriter file = new FileWriter(path)) {
            file.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}