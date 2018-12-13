package pl.put.poznan.transformer.test;

import pl.put.poznan.transformer.app.Krok;

import static org.junit.Assert.assertEquals;

public class KrokTest {

    private Krok krok;

    @org.junit.Before
    public void setUp() {
        krok = new Krok();
    }

    @org.junit.Test
    public void sameSpacje() {
        krok.setWiersz("          ");
        assertEquals(0, krok.ileSlowWKroku(), 0.0001);
    }

    @org.junit.Test
    public void pusto() {
        krok.setWiersz("");
        assertEquals(0, krok.ileSlowWKroku(), 0.0001);
    }

    @org.junit.Test
    public void jednoSlowo() {
        krok.setWiersz("Slowo");
        assertEquals(1, krok.ileSlowWKroku(), 0.0001);
    }

    @org.junit.Test
    public void wiecejNizJednaSpacja() {
        krok.setWiersz("Wiecej   niz          jedna                             spacja");
        assertEquals(4, krok.ileSlowWKroku(), 0.0001);
    }

    @org.junit.Test
    public void ZwykleZdanie() {
        krok.setWiersz("To jest najzwyklejsze zdanie");
        assertEquals(4, krok.ileSlowWKroku(), 0.0001);
    }
}