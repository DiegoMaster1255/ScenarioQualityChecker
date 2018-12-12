package pl.put.poznan.transformer.app;

import java.util.List;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */


public class DoPoziomuVisitor implements Visitor {
    private List<Podscenariusz> list;
    private int zagniezdzenie;

    public DoPoziomuVisitor(int zagniezdzenie){
        this.zagniezdzenie = zagniezdzenie;
    }

    public void visit(ScenariuszGlowny scenariuszGlowny) {
        for(int i = 0; i<scenariuszGlowny.listaScenariuszy.size(); i++)
        {
            if(scenariuszGlowny.listaScenariuszy.get(i).zagniezdzenie <= zagniezdzenie)
                list.add(scenariuszGlowny.listaScenariuszy.get(i));
        }
    }

    /**Ucina scenariusz do podanego poziomu(zagniezdzenia).
     *
     *
     * @return podscenariusz.
     */
    public List<Podscenariusz> getDoPoziomu(){
        return list;
    }
}
