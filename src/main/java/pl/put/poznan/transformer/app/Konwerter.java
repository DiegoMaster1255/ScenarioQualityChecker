package pl.put.poznan.transformer.app;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Konwerter {

    public void toJSONLiczba(int liczba,int typ, String path){
        JSONObject obj = new JSONObject();
        if(typ == 0)
            obj.put("liczba krokow", String.valueOf(liczba));
        else
            obj.put("liczba kluczy", String.valueOf(liczba));
        writeToFile(obj, path);
    }

    public void toJSONStringArray(List<String> lista, String path){
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        for(int i=0;i<lista.size();i++){
            array.put(lista.get(i));
        }
        obj.put("lista bledow", array);

        writeToFile(obj, path);
    }

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

    private void writeToFile(JSONObject obj, String path){

        try (FileWriter file = new FileWriter(path)) {
            file.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}