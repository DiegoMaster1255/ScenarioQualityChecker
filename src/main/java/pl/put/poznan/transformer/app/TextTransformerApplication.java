package pl.put.poznan.transformer.app;




import b.d.K;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.Collections;
import org.json.*;





@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(TextTransformerApplication.class, args);
        System.out.println("ABBA");
        System.out.println("Cos");
        System.out.println("aha");
        System.out.println("my");

        Podscenariusz p1 = new Podscenariusz();
        p1.getListaKrokow().add(new Krok("Bibliotekarz","Bibliotekarz wybiera"));
        p1.getListaKrokow().add(new Krok("wyświetla","wyświetla fomularz"));
        p1.getListaKrokow().add(new Krok("Bibliotekarz","Bibliotekarz podaje"));
        p1.getListaKrokow().add(new Krok("Bibliotekarz","IF Bibliotekarz pragnie"));
        p1.wypelnij(1, 4,"");

        Podscenariusz p2 = new Podscenariusz();
        p2.getListaKrokow().add(new Krok("Bibliotekarz","Bibliotekarz wybiera"));
        p2.getListaKrokow().add(new Krok("System","System prezentuje"));
        p2.getListaKrokow().add(new Krok("egzemplarz","FOR EACH egzemplarz"));
        p2.wypelnij(2,3,"IF");

        Podscenariusz p3 = new Podscenariusz();
        p3.getListaKrokow().add(new Krok("Bibliotekarz","Bibliotekarz wybiera"));
        p3.getListaKrokow().add(new Krok("System","System prosi"));
        p3.getListaKrokow().add(new Krok("Bibliotekarz","Bibliotekarz podaje"));
        p3.getListaKrokow().add(new Krok("System","System informuje"));
        p3.wypelnij(3,4,"FOR EACH");

        Podscenariusz p4 = new Podscenariusz();
        p4.getListaKrokow().add(new Krok("Bibliotekarz","Bibliotekarz zatwierdza"));
        p4.getListaKrokow().add(new Krok("System","System informuje"));


        List<Podscenariusz> listaScenariuszy = new ArrayList();
        listaScenariuszy.add(p1);
        listaScenariuszy.add(p2);
        listaScenariuszy.add(p3);
        listaScenariuszy.add(p4);


        /**
         JSONObject obj = null;
         try {
         obj = new JSONObject("content");
         }
         catch (JSONException e) {
         e.printStackTrace();
         }
         try {
         String pageName = obj.getJSONObject("pageInfo").getString("pageName");
         } catch (JSONException e) {
         e.printStackTrace();
         }

         JSONArray arr = null;
         try {
         arr = obj.getJSONArray("posts");
         }
         catch (JSONException e) {
         e.printStackTrace();
         }
         for (int i = 0; i < arr.length(); i++)
         {
         try {
         String post_id = arr.getJSONObject(i).getString("post_id");
         }
         catch (JSONException e) {
         e.printStackTrace();
         }
         }
         **/
        Konwerter konwerter = new Konwerter();

        ScenariuszGlowny scenariusz = konwerter.JSONtoJava(
                "C:\\Users\\Radek\\git\\ScenarioQualityChecker\\src\\" +
                        "main\\java\\pl\\put\\poznan\\transformer\\app\\plik.json");

//        scenariusz.wypiszScenariusz();
//        System.out.println(scenariusz.ileSlowKluczowych());



        konwerter.toJSONScenariusz(scenariusz.scenariuszDoPoziomu(2),
                "C:\\Users\\Radek\\git\\ScenarioQualityChecker\\" +
                        "src\\main\\java\\pl\\put\\poznan\\transformer\\app\\out.json");

        // Convert JSON string to JSONObject

    }
}
