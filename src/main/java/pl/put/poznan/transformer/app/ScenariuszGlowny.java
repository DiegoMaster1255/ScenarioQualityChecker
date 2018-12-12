package pl.put.poznan.transformer.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentujaca scenariusz glowny.
 */
public class ScenariuszGlowny implements Visitable{
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



    public void accept(Visitor visitor) {
        visitor.visit(this);
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
