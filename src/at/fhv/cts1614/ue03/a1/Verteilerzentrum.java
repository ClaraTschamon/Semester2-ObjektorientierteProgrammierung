package at.fhv.cts1614.ue03.a1;

import java.util.LinkedList;

public class Verteilerzentrum {
    private final int _length;
    private final int _height;
    LinkedList <Trolley> _trolleys;
    //eine Liste aller Produkte erstellen, die dieser spezifische Trolley mitnehmen soll

    public Verteilerzentrum(int length, int height, LinkedList<Trolley> trolleys) {
        _length = length;
        _height = height;
        _trolleys = trolleys;
    }

    /**
     * schickt einen bestimmten Trolley durch das Verteilerzentrum
     * bis alle "herumliegenden" Produkte an ihrem Zielort sind
     */
    public void cleanUp(Trolley trolley){
        Point position = trolley.get_position();

        while(!(trolley.productsForTrolley() == 0)){
            findProd(trolley);

            while(!position.equals(trolley.get_products().getFirst().get_aim())){
                check(trolley);
            }
            trolley.printGeneralProductList();
            trolley.printLoadedProductList();
            System.out.println("Produkte auf Trolley" + trolley.get_trolleyID() + " werden abgeladen");
            trolley.unloadAll();
            System.out.println("---------------------------------");
            trolley.get_position().set_x(0);
            trolley.get_position().set_y(0);
        }
    }

    /**
     * findet das erste Produkt und ladet es auf
     */
    private void findProd(Trolley trolley){

        while (!trolley.productFound()) {
            move(trolley);
        }
    }

    /**
     * überprüft, ob an der aktuellen Position vom Trolley ein Produkt liegt.
     * Wenn nicht, bewegt sich der Trolley einen Schritt weiter
     */
    private void check(Trolley trolley){
        if(!trolley.productFound()) {
            move(trolley);
        }
    }

    /**
     * bewegt den Trolley bei jedem Aufruf einen Schritt weiter
     */
    private void move(Trolley trolley){
        Point position = trolley.get_position();
        if (position.get_x() < _length) {
            position.set_x(position.get_x() + 1);
        } else if (position.get_y() < _height) {
            position.set_x(0);
            position.set_y(position.get_y() + 1);
        }else{
            position.set_x(0);
            position.set_y(0);
        }
    }

}
