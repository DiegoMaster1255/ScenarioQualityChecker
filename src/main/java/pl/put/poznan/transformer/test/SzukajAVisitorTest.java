package pl.put.poznan.transformer.test;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.app.Krok;
import pl.put.poznan.transformer.app.ScenariuszGlowny;
import pl.put.poznan.transformer.app.SzukajAVisitor;
import pl.put.poznan.transformer.logic.SQChecker;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void mockTest2(){ //kazdy podscenariusz ma jednego mocka czyli krok
        Krok mock = mock(Krok.class);
        when(mock.getAktor()).thenReturn("jiasodjaw");
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajAVisitor v = new SzukajAVisitor("jiasodjaw");
        for(int i =0; i<sg.getListaScenariuszy().size();i++){
            for(int j =0; j<sg.getListaScenariuszy().get(i).getListaKrokow().size();j++)
            sg.getListaScenariuszy().get(i).getListaKrokow().remove(0);
            sg.getListaScenariuszy().get(i).getListaKrokow().add(mock);
        }
        int ile = sg.getListaScenariuszy().size();
        System.out.println(v.getZAktorem());
        v.visit(sg);
        assertEquals(ile, v.getZAktorem().size(), 0);
    }

    @Test
    void mockTest3(){
        Krok mock = mock(Krok.class);
        when(mock.getAktor()).thenReturn("jiasodjaw");
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajAVisitor v = new SzukajAVisitor("aktor");
        for(int i =0; i<sg.getListaScenariuszy().size();i++){
            for(int j =0; j<sg.getListaScenariuszy().get(i).getListaKrokow().size();j++) {
                sg.getListaScenariuszy().get(i).getListaKrokow().remove(0);
                sg.getListaScenariuszy().get(i).getListaKrokow().add(mock);
            }
        }
        System.out.println(v.getZAktorem());
        v.visit(sg);
        assertEquals(0, v.getZAktorem().size(), 0);
    }

    @Test
    void mockTest4(){
        Krok mock = mock(Krok.class);
        when(mock.getAktor()).thenReturn("jiasodjaw");
        ScenariuszGlowny sg = sqc.stworzScenariusz();
        SzukajAVisitor v = new SzukajAVisitor("jiasodjaw");
        int ile = 0;
        for(int i =0; i<sg.getListaScenariuszy().size();i++){
            for(int j =0; j<sg.getListaScenariuszy().get(i).getListaKrokow().size();j++) {
                sg.getListaScenariuszy().get(i).getListaKrokow().remove(0);
                sg.getListaScenariuszy().get(i).getListaKrokow().add(mock);
                ile++;
            }
        }
        System.out.println(v.getZAktorem());
        v.visit(sg);
        assertEquals(ile, v.getZAktorem().size(), 0);
    }






}