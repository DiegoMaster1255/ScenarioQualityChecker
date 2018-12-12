package pl.put.poznan.transformer.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */


public class SzukajAVisitor implements Visitor {

    private List<String> listaKrokowZAktorem;
    private String aktor;

    public SzukajAVisitor(String aktor){
        this.aktor = aktor;
        listaKrokowZAktorem = new ArrayList<String>();
    }
    public void visit(ScenariuszGlowny scenariuszGlowny) {
        for (int i = 0; i < scenariuszGlowny.listaScenariuszy.size(); i++) {
            for (int j = 0; j < scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.size(); j++) {
                System.out.println(scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.get(j).getAktor());
                if(scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.get(j).getAktor().equalsIgnoreCase(aktor)) {
                    listaKrokowZAktorem.add(scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.get(j).wiersz);
                }
            }
        }
    }

    /**
     * Wyszukuje kroki, w ktorych wystepuje dany aktor
     * @return lista z krokami, w ktorych wystepuje dany aktor
     */
    public List<String> getZAktorem(){
        return listaKrokowZAktorem;
    }
}
