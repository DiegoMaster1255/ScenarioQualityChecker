package pl.put.poznan.transformer.app;

/**
 * Interfejs z funkcjonalnoscia zapewniajaca mozliwosc zaakceptowania wizytatora w klasie
 */

public interface Visitable {
    /**
     * funkcjonalnoscia zapewniajaca mozliwosc zaakceptowania wizytatora w klasie
     * @param visitor
     */

    public void accept(Visitor visitor);
}
