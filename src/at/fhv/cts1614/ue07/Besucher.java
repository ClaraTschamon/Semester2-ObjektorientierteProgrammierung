//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue07;

import java.util.Random;

public class Besucher implements Runnable{
    private String _name;
    private Antrag _antrag;
    private Bearbeitungsstelle _bearbeitungsstelle;
    private final Object _lock = new Object();
    //ich brauche keine warteliste im Gemeindeamt weil ich bei wait() eine automatische Liste habe


    public Besucher(String name, Antrag antrag, Gemeindeamt gemeindeamt){
        _name = name;
        _antrag = antrag;
        gemeindeamt.addBesucher(this);
    }

    public String get_name() {
        return _name;
    }

    public Antrag get_antrag() {
        return _antrag;
    }

    public void set_bearbeitungsstelle(Bearbeitungsstelle bearbeitungsstelle) {
        _bearbeitungsstelle = bearbeitungsstelle;
    }

    public Object get_lock() {
        return _lock;
    }

    public void warten() throws InterruptedException {
        Random random = new Random();
        switch (random.nextInt(2)){
            case 0:
                System.out.println(_name + " macht Mittagsschlaf");
                break;
            case 1:
                System.out.println(_name + " telefoniert");
                break;
        }
        synchronized(_bearbeitungsstelle.get_monitor()){
            _bearbeitungsstelle.get_monitor().wait();
        }
    }

    @Override
    public void run() {
        try{
            _bearbeitungsstelle.betreten(this);
            _bearbeitungsstelle.bearbeiten(this);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
