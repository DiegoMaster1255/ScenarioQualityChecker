package pl.put.poznan.transformer.app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ScenariuszGlowny {
    String tytul;
    List<String> aktorzy = new ArrayList();
    List<String> aktorzySystemowi = new ArrayList();
    List<Podscenariusz> listaScenariuszy = new ArrayList();
    List<Integer> listaNumerow = new ArrayList();

    public ScenariuszGlowny(String tytul, List<String> aktorzy, List<String> aktorzySystemowi, List<Podscenariusz> listaScenariuszy, List<Integer> listaNumerow) {
        this.tytul = tytul;
        this.aktorzy = aktorzy;
        this.aktorzySystemowi = aktorzySystemowi;
        this.listaScenariuszy = listaScenariuszy;
        this.listaNumerow = listaNumerow;
    }

    public ScenariuszGlowny(){}

    public int ileKrokowMaScenariusz()
    {
        int kroki = 0;
        for(int i = 0; i < listaScenariuszy.size(); i++)
        {
            kroki += listaScenariuszy.get(i).liczbaKrokow;
        }
        return kroki;
    }

    public int ileSlowKluczowych()
    {
        int kroki = 0;
        for(int i = 0; i < listaScenariuszy.size(); i++)
        {
            if(listaScenariuszy.get(i).slowoKlucz != "")
                kroki++;

        }
        return kroki;
    }

    public List<String> bledneKroki()
    {
        List<String> mistake = new ArrayList();
        for(int i = 0; i<listaScenariuszy.size(); i++)
        {
            for(int j =0; j<listaScenariuszy.get(i).listaKrokow.size(); j++)
            {
                if(listaScenariuszy.get(i).listaKrokow.get(j).aktor == "")
                    mistake.add(listaScenariuszy.get(i).listaKrokow.get(j).wiersz);
            }
        }
        return mistake;
    }

    public List<Podscenariusz> scenariuszDoPoziomu(int zagniezdzenie)
    {
        List<Podscenariusz> podscenariusze = new ArrayList();
        for(int i = 0; i<listaScenariuszy.size(); i++)
        {
            if(listaScenariuszy.get(i).zagniezdzenie <= zagniezdzenie)
                podscenariusze.add(listaScenariuszy.get(i));
        }
        return podscenariusze;
    }

    public void odczytajScenariusz(List<Podscenariusz> lista)
    {
        for(int i = 0; i<lista.size(); i++)
        {
            for(int j = 0; j<lista.get(i).listaKrokow.size(); j++)
                System.out.println(lista.get(i).listaKrokow.get(j).wiersz);
        }
    }

    public void zapiszDoPliku()
    {
        PrintWriter out;
        try {
            out = new PrintWriter("ZapisanyScenariusz.txt");
            for (int i = 0; i<listaScenariuszy.size(); i++)
                listaNumerow.add(0);
            int p;
            for(int i = 0; i<listaScenariuszy.size(); i++)
            {
                for(int j = 0; j<listaScenariuszy.get(i).listaKrokow.size(); j++)
                {

                    for(int k = 0; k < listaScenariuszy.get(i).zagniezdzenie;k++) {
                        p = listaScenariuszy.get(i).zagniezdzenie-1;
                        if(k == p) {
                            listaNumerow.set(k, listaNumerow.get(k) + 1);
                        }
                        out.print(listaNumerow.get(k) + ".");
                    }
                    String tempString = listaScenariuszy.get(i).listaKrokow.get(j).wiersz;
                    while(tempString.indexOf("\t")==0)
                    {
                        tempString = tempString.substring(1);

                    }
                    out.print(tempString);
                    out.println();
                }
            }


            out.println();



            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        listaNumerow.clear();
    }


    public String getTytul() {
        return tytul;
    }

    public List<String> getAktorzy() {
        return aktorzy;
    }

    public List<String> getAktorzySystemowi() {
        return aktorzySystemowi;
    }

    public List<Podscenariusz> getListaScenariuszy() {
        return listaScenariuszy;
    }

    public List<Integer> getListaNumerow() {
        return listaNumerow;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public void setAktorzy(List<String> aktorzy) {
        this.aktorzy = aktorzy;
    }

    public void setAktorzySystemowi(List<String> aktorzySystemowi) {
        this.aktorzySystemowi = aktorzySystemowi;
    }

    public void setListaScenariuszy(List<Podscenariusz> listaScenariuszy) {
        this.listaScenariuszy = listaScenariuszy;
    }

    public void setListaNumerow(List<Integer> listaNumerow) {
        this.listaNumerow = listaNumerow;
    }
}
