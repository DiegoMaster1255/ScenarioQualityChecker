package pl.put.poznan.transformer.test;


import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.app.*;
import pl.put.poznan.transformer.logic.SQChecker;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void mockTest5(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        Podscenariusz mock = mock(Podscenariusz.class);
        when(mock.getSlowoKlucz()).thenReturn("IF");
        when(mock.getListaKrokow()).thenReturn(sg.getListaScenariuszy().get(1).getListaKrokow());
        SzukajKVisitor v = new SzukajKVisitor("IF");
        int ile = sg.getListaScenariuszy().size();
        for(int i =0; i<sg.getListaScenariuszy().size();i++){
                sg.getListaScenariuszy().remove(0);
                sg.getListaScenariuszy().add(mock);
        }
        v.visit(sg);
        assertEquals(ile, v.getZKluczem().size(), 0);
    }

    @Test
    void mockTest6(){
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        Podscenariusz mock = mock(Podscenariusz.class);
        when(mock.getSlowoKlucz()).thenReturn("ELSE");
        when(mock.getListaKrokow()).thenReturn(sg.getListaScenariuszy().get(1).getListaKrokow());
        SzukajKVisitor v = new SzukajKVisitor("IF");
        int ile = sg.getListaScenariuszy().size();
        for(int i =0; i<sg.getListaScenariuszy().size();i++){
            sg.getListaScenariuszy().remove(0);
            sg.getListaScenariuszy().add(mock);
        }
        v.visit(sg);
        assertEquals(0, v.getZKluczem().size(), 0);
    }


}