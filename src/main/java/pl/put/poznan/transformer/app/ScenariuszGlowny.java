package pl.put.poznan.transformer.app;

import java.util.ArrayList;
import java.util.List;

public class ScenariuszGlowny {
    String tytul;
    List<String> aktorzy = new ArrayList();
    List<String> aktorzySystemowi = new ArrayList();
    List<Podscenariusz> listaScenariuszy = new ArrayList();
    List<Integer> listaNumerow = new ArrayList();

    public ScenariuszGlowny(String tytul, List<String> aktorzy, List<String> aktorzySystemowi, List<Podscenariusz> listaScenariuszy, List<Integer> listaNumerow) {
        this.tytul = tytul;
        this.aktorzy = aktorzy;
        this.aktorzySystemowi = aktorzySystemowi;
        this.listaScenariuszy = listaScenariuszy;
        this.listaNumerow = listaNumerow;
    }

    public ScenariuszGlowny(){}

    public String getTytul() {
        return tytul;
    }

    public List<String> getAktorzy() {
        return aktorzy;
    }

    public List<String> getAktorzySystemowi() {
        return aktorzySystemowi;
    }

    public List<Podscenariusz> getListaScenariuszy() {
        return listaScenariuszy;
    }

    public List<Integer> getListaNumerow() {
        return listaNumerow;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public void setAktorzy(List<String> aktorzy) {
        this.aktorzy = aktorzy;
    }

    public void setAktorzySystemowi(List<String> aktorzySystemowi) {
        this.aktorzySystemowi = aktorzySystemowi;
    }

    public void setListaScenariuszy(List<Podscenariusz> listaScenariuszy) {
        this.listaScenariuszy = listaScenariuszy;
    }

    public void setListaNumerow(List<Integer> listaNumerow) {
        this.listaNumerow = listaNumerow;
    }
}
