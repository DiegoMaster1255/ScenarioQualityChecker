package pl.put.poznan.transformer.app;

import java.util.ArrayList;
import java.util.List;



public class Podscenariusz extends ScenariuszGlowny{

    int zagniezdzenie;
    List<Krok> listaKrokow = new ArrayList();
    int liczbaKrokow;
    String slowoKlucz;

    public Podscenariusz(int zagniezdzenie, Krok listaKrokow, int liczbaKrokow, String slowoKlucz) {
        super();
        this.zagniezdzenie = zagniezdzenie;
        //this.listaKrokow = listaKrokow;
        this.listaKrokow = new ArrayList<>();
        getListaKrokow().add(listaKrokow);
        this.liczbaKrokow = liczbaKrokow;
        this.slowoKlucz = slowoKlucz;
    }
    public Podscenariusz() {
        super();
        this.slowoKlucz = "";
    }

    public void wypelnij(int zagniezdzenie, int liczbaKrokow, String slowoKlucz) {
        this.zagniezdzenie = zagniezdzenie;
        this.liczbaKrokow = liczbaKrokow;
        this.slowoKlucz = slowoKlucz;
    }



    public int getZagniezdzenie() {
        return zagniezdzenie;
    }

    public List<Krok> getListaKrokow() {
        return listaKrokow;
    }

    public int getLiczbaKrokow() {
        return liczbaKrokow;
    }

    public String getSlowoKlucz() {
        return slowoKlucz;
    }

    public void setZagniezdzenie(int zagniezdzenie) {
        this.zagniezdzenie = zagniezdzenie;
    }

    public void setListaKrokow(List<Krok> listaKrokow) {
        this.listaKrokow = listaKrokow;
    }

    public void setLiczbaKrokkow(int liczbaKrokkow) {
        this.liczbaKrokow = liczbaKrokkow;
    }

    public void setSlowoKlucz(String slowoKlucz) {
        this.slowoKlucz = slowoKlucz;
    }
}