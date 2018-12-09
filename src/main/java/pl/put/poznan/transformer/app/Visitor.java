package pl.put.poznan.transformer.app;

/**
 * Interfejs z funkcjonalnoscia zapewniajaca dostep do danej klasy
 */

public interface Visitor {
    /**
     * Funkcjonalnosc zapewniajaca dostep do klasy ScenariuszGlowny
     * @param scenariuszGlowny
     */

    public void visit(ScenariuszGlowny scenariuszGlowny);
}
