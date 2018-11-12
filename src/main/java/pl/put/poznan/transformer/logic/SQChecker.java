package pl.put.poznan.transformer.logic;


/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SQChecker {

    private final String nazwa_pliku;

    public SQChecker(String nazwa_pliku){
        this.nazwa_pliku = nazwa_pliku;
    }

    public String check(String funkcja){
        // wczytaj plik
        String wynik = "Bledna funkcja";
        if(funkcja.equals("zliczKroki")){
            wynik = "Zlicz kroki";
        }
        else if(funkcja.equals("bledneKroki")){
            wynik = "Bledne kroki";
        }

        return wynik;
    }
}