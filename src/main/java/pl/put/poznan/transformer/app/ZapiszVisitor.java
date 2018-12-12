package pl.put.poznan.transformer.app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Klasa reprezentujaca impelemtacje Wizytatora
 */


public class ZapiszVisitor implements Visitor {

    /**
     * zapisuje scenariusz do pliku.
     */
    public void visit(ScenariuszGlowny scenariuszGlowny) {
        PrintWriter out;
        try {
            out = new PrintWriter("ZapisanyScenariusz.txt");
            for (int i = 0; i<scenariuszGlowny.listaScenariuszy.size(); i++)
                scenariuszGlowny.listaNumerow.add(0);
            int p;
            for(int i = 0; i<scenariuszGlowny.listaScenariuszy.size(); i++)
            {
                for(int j = 0; j<scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.size(); j++)
                {

                    for(int k = 0; k < scenariuszGlowny.listaScenariuszy.get(i).zagniezdzenie;k++) {
                        p = scenariuszGlowny.listaScenariuszy.get(i).zagniezdzenie-1;
                        if(k == p) {
                            scenariuszGlowny.listaNumerow.set(k, scenariuszGlowny.listaNumerow.get(k) + 1);
                        }
                        out.print(scenariuszGlowny.listaNumerow.get(k) + ".");
                    }
                    String tempString = scenariuszGlowny.listaScenariuszy.get(i).listaKrokow.get(j).wiersz;
                    while(tempString.indexOf("\t")==0)
                    {
                        tempString = tempString.substring(1);

                    }
                    out.print(tempString);
                    out.println();
                }
            }


            out.println();



            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scenariuszGlowny.listaNumerow.clear();
    }

}
