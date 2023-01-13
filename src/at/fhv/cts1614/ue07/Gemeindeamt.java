//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue07;

import java.util.LinkedList;
import java.util.List;

public class Gemeindeamt{

    private List<Besucher> _besucher = new LinkedList<>();
    private Bearbeitungsstelle _bauamt = new Bearbeitungsstelle(AntragArt.Bauantrag);
    private Bearbeitungsstelle _standesamt = new Bearbeitungsstelle(AntragArt.Ehe);

    public Gemeindeamt(){

    }

    //Frage: wie teste ich es???
    public static void main(String[] args) throws InterruptedException {
        Gemeindeamt gemeindeamt = new Gemeindeamt();

        //Antrag muss für jeden Besucher neu zugeteilt werden weil sonst für 2 Personen der Gleiche Antrag bearbeitet wird
        Besucher david = new Besucher("David", new Antrag(AntragArt.Bauantrag), gemeindeamt);
        Besucher margit = new Besucher("Margit", new Antrag(AntragArt.Bauantrag), gemeindeamt);
        //Besucher martin = new Besucher("Martin", new Antrag(AntragArt.Ehe), gemeindeamt);
        Besucher johannes = new Besucher("Johannes", new Antrag(AntragArt.Bauantrag), gemeindeamt);
        Besucher hannah = new Besucher("Hannah", new Antrag(AntragArt.Bauantrag), gemeindeamt);

        gemeindeamt.teileBesucherZu();
        Thread David = new Thread(david);
        Thread Margit = new Thread(margit);
        //Thread Martin = new Thread(martin);
        Thread Johannes = new Thread(johannes);
        Thread Hannah = new Thread(hannah);

        // start the execution of all threads (in parallel)
        David.start();
        Margit.start();
        Johannes.start();
        Hannah.start();
        //Martin.start();

        System.out.println("main thread ended...");
    }

    public void addBesucher(Besucher besucher){
        _besucher.add(besucher);
    }

    public void teileBesucherZu(){
        for(Besucher besucher : _besucher){
            switch(besucher.get_antrag().get_antragart()){
                case Bauantrag:
                    besucher.set_bearbeitungsstelle(_bauamt); //monitor als pointer übergeben
                    break;
                case Ehe:
                case Scheidung:
                    besucher.set_bearbeitungsstelle(_standesamt);
                    break;
            }
        }
        _besucher.clear();
    }
}
