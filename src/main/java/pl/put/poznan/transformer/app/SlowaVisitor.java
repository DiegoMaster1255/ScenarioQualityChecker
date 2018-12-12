package pl.put.poznan.transformer.app;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */


public class SlowaVisitor implements Visitor {

    private int ileSlow;
    /**
     * zapisuje scenariusz do pliku.
     */
    public void visit(ScenariuszGlowny scenariuszGlowny) {
        for(int i = 0; i<scenariuszGlowny.listaScenariuszy.size(); i++) {
            ileSlow += scenariuszGlowny.listaScenariuszy.get(i).ileSlowWPodscenariuszu();
        }
    }

    /**
     * Zlicza ile jest slow w Scenariuszu
     * @return ilosc slow w Scenariuszu
     */

    public int getIleSlow(){
        return ileSlow;
    }
}
