package pl.put.poznan.transformer.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */

public class BledneVisitor implements Visitor {
    private List<String> mistake = new ArrayList();
    public void visit(ScenariuszGlowny scenariuszGlowny) {
        for(int i = 0; i<scenariuszGlowny.listaScenariuszy.size(); i++)
        {
            for(int j =0; j<scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.size(); j++)
            {
                if(scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.get(j).aktor == "")
                    mistake.add(scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.get(j).wiersz);
            }
        }
    }

    /**
     * Zliczanie blednych krokow scenariusza (bez autora).
     * @return mistake
     */
    public List<String> getTotalBledne(){
        return mistake;
    }
}
