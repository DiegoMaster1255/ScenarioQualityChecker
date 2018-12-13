package pl.put.poznan.transformer.test;


import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.app.ScenariuszGlowny;
import pl.put.poznan.transformer.app.SzukajKVisitor;
import pl.put.poznan.transformer.logic.SQChecker;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SzukajKVisitorTest {

    SQChecker sqc = new SQChecker("testKlucz");


    @Test
    void pustyKlucz(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajKVisitor v = new SzukajKVisitor(" ");
        v.visit(sg);
        System.out.println(v.getZKluczem());
        assertTrue(v.getZKluczem().isEmpty());

    }

    @Test
    public void sameSpacjeKlucz(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajKVisitor v = new SzukajKVisitor("       ");
        v.visit(sg);
        assertTrue(v.getZKluczem().isEmpty());

    }

    @Test
    public void blednyKlucz(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajKVisitor v = new SzukajKVisitor("asdkaiw");
        v.visit(sg);
        assertTrue(v.getZKluczem().isEmpty());
    }

    @Test
    public void liczbyKlucz(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajKVisitor v = new SzukajKVisitor("123456");
        v.visit(sg);
        assertTrue(v.getZKluczem().isEmpty());
    }

    @Test
    public void poprawnyBrakKlucza(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajKVisitor v = new SzukajKVisitor("FOR EACH");
        v.visit(sg);
        assertTrue(v.getZKluczem().isEmpty());
    }

    @Test
    public void poprawnyKlucz(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajKVisitor v = new SzukajKVisitor("IF");
        v.visit(sg);
        assertTrue(v.getZKluczem().size() == 2);
    }


}