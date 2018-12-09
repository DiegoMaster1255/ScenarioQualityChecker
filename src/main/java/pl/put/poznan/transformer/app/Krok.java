package pl.put.poznan.transformer.app;

/**
 * Klasa reprezentujaca krok scenariusza.
 */
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

    /**
     * Zlicza ile jest slow w danym kroku
     * @return ilosc slow w danym kroku
     */
    public int ileSlowWKroku() {
        String tempWiersz = wiersz;
        if(tempWiersz == null  || tempWiersz == "") {
            return 0;
        }
        int ileSlow = 1;
        while(tempWiersz.substring(0,1).equalsIgnoreCase(" ")) {
            if(tempWiersz.length() == 1) {
                return 0;
            }
            tempWiersz = tempWiersz.substring(1);
        }


        while(tempWiersz.indexOf(" ")>0) {
            tempWiersz = tempWiersz.substring(tempWiersz.indexOf(" "));
            ileSlow++;
            while(tempWiersz.substring(0,1).equalsIgnoreCase(" ")) {
                tempWiersz = tempWiersz.substring(1);
                if(tempWiersz.length() == 1) {
                    break;
                }
            }
        }
        return ileSlow;
    }


    /** Zwraca aktora, zawartego w kroku.
     * @return aktor
     * */
    public String getAktor() {
        return aktor;
    }

    /** Zwraca tresc kroku.
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

    /** Ustala tresc kroku.
     * @param wiersz tresc kroku.
     */
    public void setWiersz(String wiersz) {
        this.wiersz = wiersz;
    }
}
