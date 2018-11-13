package pl.put.poznan.transformer.app;

public class Krok {
    /**Aktor uczestniczacy w kroku.*/
    public String aktor;
    /**Pojedynczy krok.*/
    public String wiersz;

    /** Konstruktor domyslny kroku.
     *
     */
    public Krok(){}

    /**Konstruktor kroku z aktorem i wierszem.
     * @param aktor aktor uczestniczacy w kroku.
     * @param wiersz tresc kroku.
     */
    public Krok(String aktor, String wiersz) {
        this(wiersz);
        this.aktor = aktor;
    }

    /** Konstruktor kroku bez aktora.
     * @param wiersz tresc kroku.
     */
    public Krok( String wiersz){
        this.wiersz = wiersz;
        this.aktor = "";
    }
    /** Zwraca aktora, zawartego w kroku.
     * @return aktor
     * */
    public String getAktor() {
        return aktor;
    }

    /** Zwraca tresc kroki.
     * @return wiersz
     */
    public String getWiersz() {
        return wiersz;
    }

    /** Ustala aktora, wykonawce kroku.
     * @param aktor aktor uczestniczacy w kroku.
     */
    public void setAktor(String aktor) {
        this.aktor = aktor;
    }

    /** Ustala tresc kroki.
     * @param wiersz tresc kroku.
     */
    public void setWiersz(String wiersz) {
        this.wiersz = wiersz;
    }
}
