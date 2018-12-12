package pl.put.poznan.transformer.app;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */


public class kluczoweVisitor implements Visitor {
    private int totalKluczowe;

    public void visit(ScenariuszGlowny scenariuszGlowny) {
        for(int i = 0; i < scenariuszGlowny.listaScenariuszy.size(); i++)
        {
            if(scenariuszGlowny.listaScenariuszy.get(i).slowoKlucz != "")
                totalKluczowe += scenariuszGlowny.listaScenariuszy.get(i).liczbaKrokow;
        }
    }

    /** Zliczanie slow kluczy w scenariuszu.
     *
     * @return totalKluczowe
     */
    public int getTotalKluczowe(){
        return totalKluczowe;
    }
}
