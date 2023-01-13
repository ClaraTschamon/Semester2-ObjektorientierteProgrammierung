//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue07;

public class Bearbeitungsstelle {

    private final AntragArt _zustaendigkeit;
    private boolean _frei;
    private final Object _monitor = new Object();

    public Bearbeitungsstelle(AntragArt zustaendigkeit) {
        _zustaendigkeit = zustaendigkeit;
        _frei = true;
    }

    public Object get_monitor() {
        return _monitor;
    }

    /**
     * Besucher geht ins Wartezimmer und waretet darauf, bis die Bearbeitungsstelle zeit hat für ihn
     */
    public void betreten(Besucher besucher){
        synchronized (besucher.get_lock()) { //ich kann auch einfach nur synchronized(besucher)
            //besucher geht in wartezimmer und wartet bis er dran ist
            System.out.println(besucher.get_name() + " betreten");
            while (!_frei) {
                try {
                    besucher.warten();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            _frei = false;
        }
    }

    /**
     * Antrag von Besucher wird bearbeitet indem die Bearbeitungszeit runtergezählt wird
     */
    public synchronized void bearbeiten(Besucher besucher) throws InterruptedException {
        System.out.println(besucher.get_name() + " ist dran!");
        while (besucher.get_antrag().get_bearbeitungszeit() > 0) {
            besucher.get_antrag().reduceBearbeitungszeit();
            System.out.println("Bearbeitungszeit " + besucher.get_name() + " : " + besucher.get_antrag().get_bearbeitungszeit());
        }
        fertig(besucher);
    }

    /**
     * wenn der Antrag fertig bearbeitet wurde, wird ein wartender Besucher benachrichtigt
     */
    private void fertig(Besucher besucher) {
        System.out.println("Besucher " + besucher.get_name() + " fertig");
        synchronized (_monitor){
            _frei = true;
            _monitor.notify();
        }
    }
}
