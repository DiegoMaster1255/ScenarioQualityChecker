package pl.put.poznan.transformer.test;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.app.*;
import pl.put.poznan.transformer.logic.SQChecker;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void mockTest7(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        Krok mock = mock(Krok.class);
        when(mock.getAktor()).thenReturn("Bibliotekarz");
        AktorzyVisitor v = new AktorzyVisitor();
        int ile = 0;
        for(int i =0; i<sg.getListaScenariuszy().size();i++){
            ile++;
            for(int j=0; j<sg.getListaScenariuszy().get(i).getListaKrokow().size();j++) {
                sg.getListaScenariuszy().get(i).getListaKrokow().remove(0);
            }
            sg.getListaScenariuszy().get(i).getListaKrokow().add(mock);
        }
        v.visit(sg);
        assertEquals(ile, v.getIleAktorow(), 0);
    }

    @Test
    void mockTest8(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        Krok mock = mock(Krok.class);
        when(mock.getAktor()).thenReturn("Bibliotekarz");
        AktorzyVisitor v = new AktorzyVisitor();
        int ile = 0;
        for(int i =0; i<sg.getListaScenariuszy().size();i++){
            for(int j=0; j<sg.getListaScenariuszy().get(i).getListaKrokow().size();j++) {
                sg.getListaScenariuszy().get(i).getListaKrokow().remove(0);
                sg.getListaScenariuszy().get(i).getListaKrokow().add(mock);
                ile++;
            }
        }
        v.visit(sg);
        assertEquals(ile, v.getIleAktorow(), 0);
    }

    @Test
    void mockTest9(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        Krok mock = mock(Krok.class);
        when(mock.getAktor()).thenReturn("");
        AktorzyVisitor v = new AktorzyVisitor();
        for(int i =0; i<sg.getListaScenariuszy().size();i++){
            for(int j=0; j<sg.getListaScenariuszy().get(i).getListaKrokow().size();j++) {
                sg.getListaScenariuszy().get(i).getListaKrokow().remove(0);
                sg.getListaScenariuszy().get(i).getListaKrokow().add(mock);
            }
        }
        v.visit(sg);
        assertEquals(0, v.getIleAktorow(), 0);
    }

    @Test
    void mockTest10(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        Podscenariusz mock = mock(Podscenariusz.class);
        Krok mock1 = mock(Krok.class);
        ArrayList<Krok> kroczki = new ArrayList<Krok>();
        kroczki.add(mock1);
        kroczki.add(mock1);
        kroczki.add(mock1);
        when(mock.getListaKrokow()).thenReturn(kroczki);
        for(int i = 0; i<sg.getListaScenariuszy().size();i++){
            sg.getListaScenariuszy().remove(0);
        }
        sg.getListaScenariuszy().add(mock);
        sg.getListaScenariuszy().add(mock);

        AktorzyVisitor v = new AktorzyVisitor();
        v.visit(sg);
        assertEquals(6, v.getIleAktorow(), 0);
    }



}