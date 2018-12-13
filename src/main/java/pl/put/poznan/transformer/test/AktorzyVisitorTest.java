package pl.put.poznan.transformer.test;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.app.AktorzyVisitor;
import pl.put.poznan.transformer.app.Krok;
import pl.put.poznan.transformer.app.Podscenariusz;
import pl.put.poznan.transformer.app.ScenariuszGlowny;
import pl.put.poznan.transformer.logic.SQChecker;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AktorzyVisitorTest {
    SQChecker sqc = new SQChecker("testAktorzyZlicz");

    @Test
    void brakAktorow() {
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        AktorzyVisitor v = new AktorzyVisitor();
        v.visit(sg);
        assertTrue(v.getIleAktorow() == 0);
    }

    @Test
    void jestAktor() {
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        AktorzyVisitor v = new AktorzyVisitor();
        Krok temp1 = new Krok("Bibliotekarz", "aksodakso");
        List<Krok> temp3 = new ArrayList<Krok>();
        temp3.add(temp1);
        Podscenariusz temp2 = new Podscenariusz();
        temp2.setListaKrokow(temp3);
        List<Podscenariusz> temp = new ArrayList<>();
        temp.add(temp2);
        sg.setListaScenariuszy(temp);
        v.visit(sg);
        assertTrue(v.getIleAktorow() == 1);
    }

    @Test
    void brakListyAktorow() {
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        AktorzyVisitor v = new AktorzyVisitor();
        Krok temp1 = new Krok("Bibliotekarz", "aksodakso");
        List<Krok> temp3 = new ArrayList<Krok>();
        temp3.add(temp1);
        Podscenariusz temp2 = new Podscenariusz();
        temp2.setListaKrokow(temp3);
        List<Podscenariusz> temp = new ArrayList<>();
        temp.add(temp2);
        sg.setListaScenariuszy(temp);
        List<String> t = new ArrayList<>();
        sg.setAktorzy(t);
        v.visit(sg);
        assertTrue(v.getIleAktorow() == 1);
    }

    @Test
    void brakKrokow() {
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        AktorzyVisitor v = new AktorzyVisitor();
        List<Podscenariusz> temp = new ArrayList<>();
        sg.setListaScenariuszy(temp);
        v.visit(sg);
        assertTrue(v.getIleAktorow() == 0);
    }



}