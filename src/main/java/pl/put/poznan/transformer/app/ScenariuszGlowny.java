package pl.put.poznan.transformer.app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentujaca scenariusz glowny.
 */
public class ScenariuszGlowny {
    /** tytul scenariusza. */
    String tytul;
    /** aktorzy w scenariuszu.*/
    List<String> aktorzy = new ArrayList();
    /** aktorzy systemowi w scenariuszu.*/
    List<String> aktorzySystemowi = new ArrayList();
    /** lista podscenariuszy.*/
    List<Podscenariusz> listaScenariuszy = new ArrayList();
    /** Pomocnicza lista do tworzenia numeracji krokow.*/
    List<Integer> listaNumerow = new ArrayList();

    /**
     * Konstruktor scenariusza glownego z ponumerowanymi krokami
     * @param tytul tytul sceniariusza
     * @param aktorzy aktorzy bioracy udzial w scenariuszu
     * @param aktorzySystemowi aktorzy systemowi bioracy udzial w scenariuszu
     * @param listaScenariuszy lista podscenariuszy
     * @param listaNumerow pomocnicza lista do numeracji krokow
     */
    public ScenariuszGlowny(String tytul, List<String> aktorzy, List<String> aktorzySystemowi, List<Podscenariusz> listaScenariuszy, List<Integer> listaNumerow) {
        this(tytul, aktorzy,aktorzySystemowi,listaScenariuszy);
        this.listaNumerow = listaNumerow;
    }

    /**
     * Konstruktor scenariusza glownego bez listy numerow.
     * @param tytul tytul sceniariusza.
     * @param aktorzy aktorzy bioracy udzial w scenariuszu.
     * @param aktorzySystemowi aktorzy systemowi bioracy udzial w scenariuszu.
     * @param listaScenariuszy lista podscenariuszy.
     */
    public ScenariuszGlowny(String tytul, List<String> aktorzy, List<String> aktorzySystemowi, List<Podscenariusz> listaScenariuszy) {
        this.tytul = tytul;
        this.aktorzy = aktorzy;
        this.aktorzySystemowi = aktorzySystemowi;
        this.listaScenariuszy = listaScenariuszy;

    }

    /**
     * Konstruktor domyslny scenariusza glownego.
     */
    public ScenariuszGlowny(){}

    /**Zliczanie krokow scenariusza wraz z jego podscenariuszami.
     *
     * @return kroki
     */
    public int ileKrokowMaScenariusz()
    {
        int kroki = 0;
        for(int i = 0; i < listaScenariuszy.size(); i++)
        {
            kroki += listaScenariuszy.get(i).liczbaKrokow;
        }
        return kroki;
    }

    /** Zliczanie slow kluczy w scenariuszu.
     *
     * @return kroki
     */
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

    /**
     * Zliczanie blednych krokow scenariusza (bez autora).
     * @return mistake
     */
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

    /**Ucina scenariusz do podanego poziomu(zagniezdzenia).
     *
     * @param zagniezdzenie stopien zagniezdzenia scenariusza.
     * @return podscenariusz.
     */
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

    /**Odczytuje scenariusz podany w postaci listy podscenariuszy.*/
    public void odczytajScenariusz(List<Podscenariusz> lista)
    {
        for(int i = 0; i<lista.size(); i++)
        {
            for(int j = 0; j<lista.get(i).listaKrokow.size(); j++)
                System.out.println(lista.get(i).listaKrokow.get(j).wiersz);
        }
    }

    /**
     * zapisuje scenariusz do pliku.
     */
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


    /**
     * Zwraca tytul scenariusza.
     * @return tytul
     */
    public String getTytul() {
        return tytul;
    }

    /** Zwraca liste aktorow.
     *
     * @return aktorzy
     */
    public List<String> getAktorzy() {
        return aktorzy;
    }

    /**
     * Zwraca liste aktorow systemowych.
     * @return aktorzySystemowi.
     */
    public List<String> getAktorzySystemowi() {
        return aktorzySystemowi;
    }

    /**
     * Zwraca liste podscenariuszy.
     * @return
     */
    public List<Podscenariusz> getListaScenariuszy() {
        return listaScenariuszy;
    }

    /**
     * Zwraca liste numerow.
     * @return listaNumerow.
     */
    public List<Integer> getListaNumerow() {
        return listaNumerow;
    }

    /**
     * Ustawia tytul scenariusza glownego.
     * @param tytul
     */
    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    /**
     * Ustawia liste aktorow scenariusza.
     * @param aktorzy
     */
    public void setAktorzy(List<String> aktorzy) {
        this.aktorzy = aktorzy;
    }

    /**
     * Ustawia liste aktorow systemowych scenariusza.
     * @param aktorzySystemowi
     *
     */
    public void setAktorzySystemowi(List<String> aktorzySystemowi) {
        this.aktorzySystemowi = aktorzySystemowi;
    }

    /**
     * Ustawia liste podscenariuszy.
     * @param listaScenariuszy lista podscenariuszy scenariusza glownego.
     */
    public void setListaScenariuszy(List<Podscenariusz> listaScenariuszy) {
        this.listaScenariuszy = listaScenariuszy;
    }

    /**Ustawia liste numerow.
     * @param listaNumerow
     */
    public void setListaNumerow(List<Integer> listaNumerow) {
        this.listaNumerow = listaNumerow;
    }
}
