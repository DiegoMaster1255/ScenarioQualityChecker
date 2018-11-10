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
/*
	public ScenariuszGlowny JSONtoJava(String path)
	{
		File file = new File(path);
		String content = null;
		try {
			content = FileUtils.readFileToString(file, "utf-8");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<String> sAktorzy = new ArrayList<String>();
		ArrayList<String> sAktorzySystemowi = new ArrayList<String>();
		String sTytul = null;
		ArrayList<Podscenariusz> podscenariusze = new ArrayList<Podscenariusz>();

		ArrayList<String> klucze = new ArrayList<String>();
		klucze.add("IF");
		klucze.add("ELSE");
		klucze.add("FOR EACH");

		int liczbaKrokow = 0;

		try {

			JSONObject mainJSONObject = new JSONObject(content);
			sTytul = mainJSONObject.getString("Tytul");
			JSONArray aktorzy = mainJSONObject.getJSONArray("Aktorzy");

			for (int i = 0; i < aktorzy.length(); i++) {
				sAktorzy.add( (String) aktorzy.get(i) );
			}

			JSONArray aktorzySystemowi = mainJSONObject.getJSONArray("Aktorzy systemowi");
			for (int i = 0; i < aktorzySystemowi.length(); i++) {
				sAktorzySystemowi.add( (String) aktorzySystemowi.get(i) );
			}


			JSONObject passportJsonObject = mainJSONObject.getJSONObject("Scenariusz glowny");
			JSONArray podscenariuszeArray = passportJsonObject.getJSONArray("Podscenariusze");
			for(int i=0;i<podscenariuszeArray.length();i++) {
				JSONObject krokiObj = podscenariuszeArray.getJSONObject(i);
				JSONArray krokiArray = krokiObj.getJSONArray("kroki");
				Podscenariusz podscenariusz = new Podscenariusz();
				liczbaKrokow = 0;
				for (int j = 0; j < krokiArray.length(); j++) {
					Krok krok = new Krok(krokiArray.get(j).toString());
					liczbaKrokow++;

					int tabulatory = 0;
					String tempString = krokiArray.get(j).toString();
					while(tempString.indexOf("\t")==0) {
						tabulatory++;
						tempString = tempString.substring(1);
					}

					if(j==0) //Tylko raz wystarczy w scenariuszu ustawic glebokosc - za pierwszym razem
					podscenariusz.setZagniezdzenie(tabulatory);
					//sprawdzanie slow kluczy


					for(int k=0;k<klucze.size();k++)
					{
						while(tempString.indexOf(klucze.get(k))==0)
						{
							int licznik = 1;
							String tmp = klucze.get(k);
							while(tmp.indexOf(" ") == 0){
								licznik++;
								int indeksik = tempString.indexOf(" ");
								tmp.substring(indeksik+1);
							}
							podscenariusz.setSlowoKlucz(klucze.get(k));
							for(int l = 0;l<licznik;l++){
								int indeksik = tempString.indexOf(" ");
								tempString = tempString.substring(indeksik+1);
							}
						}
					}

					//szukanie aktora kroku
					for(String aktor : sAktorzy)
					{
						if(tempString.indexOf(aktor)==0)
							krok.aktor = aktor;
					}
					for(String aktor : sAktorzySystemowi)
					{
						if(tempString.indexOf(aktor)==0)
							krok.aktor = aktor;
					}
					podscenariusz.listaKrokow.add(krok);
					podscenariusz.setLiczbaKrokkow(liczbaKrokow);
				}
				podscenariusze.add(podscenariusz);
				System.out.println(podscenariusz.liczbaKrokow+" " + podscenariusz.getSlowoKlucz());
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		ScenariuszGlowny scenariusz = new ScenariuszGlowny(sTytul, sAktorzy, sAktorzySystemowi, podscenariusze);

		return scenariusz;
	}
*/

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
