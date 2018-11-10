package pl.put.poznan.transformer.app;


public class Krok {
    String aktor;
    String wiersz;

    public Krok(String aktor, String wiersz) {
        this.aktor = aktor;
        this.wiersz = wiersz;
    }

    public String getAktor() {
        return aktor;
    }

    public String getWiersz() {
        return wiersz;
    }

    public void setAktor(String aktor) {
        this.aktor = aktor;
    }

    public void setWiersz(String wiersz) {
        this.wiersz = wiersz;
    }
}
