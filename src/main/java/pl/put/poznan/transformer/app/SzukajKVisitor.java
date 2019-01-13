package pl.put.poznan.transformer.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */


public class SzukajKVisitor implements Visitor {

    private List<String> listaKrokowZKluczem;
    private String klucz;

    public SzukajKVisitor(String klucz){
        this.klucz = klucz;
        listaKrokowZKluczem = new ArrayList<String>();
    }

    public void visit(ScenariuszGlowny scenariuszGlowny) {
        for (int i = 0; i < scenariuszGlowny.listaScenariuszy.size(); i++) {
            if(scenariuszGlowny.listaScenariuszy.get(i).getSlowoKlucz().equalsIgnoreCase(klucz)) {
                listaKrokowZKluczem.add(scenariuszGlowny.listaScenariuszy.get(i).getListaKrokow().get((scenariuszGlowny.listaScenariuszy.get(i).getListaKrokow().size()-1)).wiersz);
            }
        }
    }

    /**
     * Wyszukuje kroki, w ktorych wystepuje dane slowo klucz
     * @return lista z krokami, w ktorych wystepuje dane slowo klucz
     */
    public List<String> getZKluczem(){
        return listaKrokowZKluczem;
    }
}
