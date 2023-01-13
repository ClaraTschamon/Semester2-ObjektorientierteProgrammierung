//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue07;

import java.util.Random;

public class Antrag{

    private static int maxNr;
    private final int _nr;
    private AntragArt _antragart;
    private int _bearbeitungszeit;

    public Antrag(AntragArt antragart){
        maxNr++;
        _nr = maxNr;
        _antragart = antragart;
        Random rand = new Random();
        _bearbeitungszeit = rand.nextInt(20);
    }

    public AntragArt get_antragart() {
        return _antragart;
    }

    public int get_bearbeitungszeit() {
        return _bearbeitungszeit;
    }

    public void reduceBearbeitungszeit() {
        _bearbeitungszeit = _bearbeitungszeit - 1;
    }
}
