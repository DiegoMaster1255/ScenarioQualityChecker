package pl.put.poznan.transformer.app;

import java.util.ArrayList;
import java.util.List;



public class Podscenariusz extends ScenariuszGlowny{

    /** glebokosc podscenariusza.*/
    int zagniezdzenie;
    /** lista krokow.*/
    List<Krok> listaKrokow = new ArrayList();
    /** liczba krokow.*/
    int liczbaKrokow;
    /** Slowo kluczowe w podscenariuszu.*/
    String slowoKlucz;

    /**
     * Konstruktor podscenariusza.
     * @param zagniezdzenie stopien zagniezdzenia podscenariusza.
     * @param listaKrokow lista krokow w podscenariuszu.
     * @param liczbaKrokow liczba krokow.
     * @param slowoKlucz slowo kluczowe w kroku.
     */
    public Podscenariusz(int zagniezdzenie, Krok listaKrokow, int liczbaKrokow, String slowoKlucz) {
        super();
        this.zagniezdzenie = zagniezdzenie;
        //this.listaKrokow = listaKrokow;
        this.listaKrokow = new ArrayList<>();
        getListaKrokow().add(listaKrokow);
        this.liczbaKrokow = liczbaKrokow;
        this.slowoKlucz = slowoKlucz;
    }

    /**
     * Konstruktor domyslny podscenariusza.
     */
    public Podscenariusz() {
        super();
        this.slowoKlucz = "";
    }


    /*public void wypelnij(int zagniezdzenie, int liczbaKrokow, String slowoKlucz) {
        this.zagniezdzenie = zagniezdzenie;
        this.liczbaKrokow = liczbaKrokow;
        this.slowoKlucz = slowoKlucz;
    }*/


    /**
     * Zwraca zagniezdzenie podscenariusza.
     * @return zagniezdzenie
     */
    public int getZagniezdzenie() {
        return zagniezdzenie;
    }

    /**
     * Zwraca liste krokow.
     * @return listaKrokow
     */
    public List<Krok> getListaKrokow() {
        return listaKrokow;
    }

    /**
     * Zwraca liczbe krokow z podscenariusza.
     * @return liczbaKrokow.
     */
    public int getLiczbaKrokow() {
        return liczbaKrokow;
    }

    /**
     * Zwraca slowo klucz z podscenariusza.
     * @return slowoKlucz
     */
    public String getSlowoKlucz() {
        return slowoKlucz;
    }

    /**
     * Ustawienie zagniezdzenia projektu.
     * @param zagniezdzenie zagniezdzenie podscenariusza.
     */
    public void setZagniezdzenie(int zagniezdzenie) {
        this.zagniezdzenie = zagniezdzenie;
    }

    /**
     * Ustawienie listy krokow podscenariusza.
     * @param listaKrokow lista krokow.
     */
    public void setListaKrokow(List<Krok> listaKrokow) {
        this.listaKrokow = listaKrokow;
    }

    /**
     * Ustawienie liczby krokow.
     * @param liczbaKrokkow liczba krokow.
     */
    public void setLiczbaKrokkow(int liczbaKrokkow) {
        this.liczbaKrokow = liczbaKrokkow;
    }

    /**
     * Ustawienie slowa klucza.
     * @param slowoKlucz slowo klucz.
     */
    public void setSlowoKlucz(String slowoKlucz) {
        this.slowoKlucz = slowoKlucz;
    }
}
