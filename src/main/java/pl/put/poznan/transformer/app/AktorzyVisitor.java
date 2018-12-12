package pl.put.poznan.transformer.app;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */


public class AktorzyVisitor implements Visitor {

    private int ileAktorow;
    /**
     * zapisuje scenariusz do pliku.
     */
    public void visit(ScenariuszGlowny scenariuszGlowny) {
        for(int i = 0; i<scenariuszGlowny.listaScenariuszy.size(); i++)
        {
            for(int j =0; j<scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.size(); j++)
            {
                if(scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.get(j).aktor != "")
                    ileAktorow++;
            }
        }
    }

    /**
     * Zlicza ilosc krokow w ktorych wystepuje dany aktor
     * @return ilosc krokow w ktorych wystepuje dany aktor
     */

    public int getIleAktorow(){
        return ileAktorow;
    }
}
