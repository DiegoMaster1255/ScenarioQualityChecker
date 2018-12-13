package pl.put.poznan.transformer.test;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.app.ScenariuszGlowny;
import pl.put.poznan.transformer.app.SzukajAVisitor;
import pl.put.poznan.transformer.logic.SQChecker;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SzukajAVisitorTest {

    SQChecker sqc = new SQChecker("testAktor");


    @Test
    void pustyAktor(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajAVisitor v = new SzukajAVisitor(" ");
        v.visit(sg);
        System.out.println(v.getZAktorem());
        assertTrue(v.getZAktorem().isEmpty());
    }
    @Test
    void poprawnyAktor(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajAVisitor v = new SzukajAVisitor("Bibliotekarz");
        v.visit(sg);
        System.out.println(v.getZAktorem());
        assertTrue(v.getZAktorem().size() == 1);
    }

    @Test
    void sameSpacjeAktor(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajAVisitor v = new SzukajAVisitor("     ");
        v.visit(sg);
        System.out.println(v.getZAktorem());
        assertTrue(v.getZAktorem().size() == 0);
    }

    @Test
    void liczbyAktor(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajAVisitor v = new SzukajAVisitor("123782");
        v.visit(sg);
        System.out.println(v.getZAktorem());
        assertTrue(v.getZAktorem().size() == 0);
    }

    @Test
    void blednyAktor(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajAVisitor v = new SzukajAVisitor("jiasodjaw");
        v.visit(sg);
        System.out.println(v.getZAktorem());
        assertTrue(v.getZAktorem().size() == 0);
    }


}