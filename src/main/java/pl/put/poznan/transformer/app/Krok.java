package pl.put.poznan.transformer.app;

public class Krok {
    String aktor;
    String wiersz;

    public Krok(){}

    public Krok(String wiersz){
        this.wiersz = wiersz;
    }

    public Krok(String aktor, String wiersz) {
        this(wiersz);
        this.aktor = aktor;
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
