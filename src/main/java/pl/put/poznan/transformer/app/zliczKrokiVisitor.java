package pl.put.poznan.transformer.app;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */

public class zliczKrokiVisitor implements Visitor {
    private int totalKroki;
    public void visit(ScenariuszGlowny scenariuszGlowny) {
        for(int i = 0; i < scenariuszGlowny.listaScenariuszy.size(); i++)
        {
            totalKroki += scenariuszGlowny.listaScenariuszy.get(i).liczbaKrokow;
        }
    }


    public int getTotalKroki(){
        return totalKroki;
    }
}
