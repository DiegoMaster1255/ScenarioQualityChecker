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
        this(tytul, aktorzy, aktorzySystemowi, listaScenariuszy);
        this.listaNumerow = listaNumerow;
    }

    public ScenariuszGlowny(String tytul, List<String> aktorzy, List<String> aktorzySystemowi, List<Podscenariusz> listaScenariuszy) {
        this.tytul = tytul;
        this.aktorzy = aktorzy;
        this.aktorzySystemowi = aktorzySystemowi;
        this.listaScenariuszy = listaScenariuszy;

    }

    public void wypiszScenariusz(){
        for(int i=0;i<listaScenariuszy.size();i++){
            for(int j=0; j<listaScenariuszy.get(i).listaKrokow.size();j++) {
                System.out.println(listaScenariuszy.get(i).listaKrokow.get(j).wiersz);
            }
            System.out.println("");
        }
    }

    public ScenariuszGlowny(){}

    public int ileKrokowMaScenariusz()
    {
        int kroki = 0;
        for(int i = 0; i < listaScenariuszy.size(); i++)
        {
            kroki += listaScenariuszy.get(i).liczbaKrokow;
        }
        return kroki;
    }

    public int ileSlowKluczowych()
    {
        int kroki = 0;
        for(int i = 0; i < listaScenariuszy.size(); i++)
        {
            if(listaScenariuszy.get(i).slowoKlucz != "")
                kroki ++;
        }
        return kroki;
    }

    public List<Podscenariusz> scenariuszDoPoziomu(int zagniezdzenie)
    {
        List<Podscenariusz> podscenariusze = new ArrayList();
        for(int i = 0; i<listaScenariuszy.size(); i++)
        {
            if(listaScenariuszy.get(i).zagniezdzenie < zagniezdzenie)
                podscenariusze.add(listaScenariuszy.get(i));
        }
        return podscenariusze;
    }

    public void odczytajScenariusz(List<Podscenariusz> lista)
    {
        for(int i = 0; i<lista.size(); i++)
        {
            for(int j = 0; j<lista.get(i).listaKrokow.size(); j++)
                System.out.println(lista.get(i).listaKrokow.get(j).wiersz);
        }
    }

    public List<String> bledneKroki()
    {
        List<String> mistake = new ArrayList();
        for(int i = 0; i<listaScenariuszy.size(); i++)
        {
            for(int j =0; j<listaScenariuszy.get(i).listaKrokow.size(); j++)
            {
                if(listaScenariuszy.get(i).listaKrokow.get(j).aktor == null)
                    mistake.add(listaScenariuszy.get(i).listaKrokow.get(j).wiersz);
            }
        }
        return mistake;
    }

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
