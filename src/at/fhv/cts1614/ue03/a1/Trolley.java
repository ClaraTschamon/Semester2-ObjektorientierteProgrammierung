//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue03.a1;

import java.util.LinkedList;
import java.util.List;

public class Trolley {

    private Point _position;
    private int _trolleyID;
    private static int _highest_trolleyID = 0;
    private final int _min_size;
    private final int _max_size;

    private LinkedList<Product> _general_products;

    private LinkedList<Product> _loaded_products = new LinkedList<Product>();

    private LinkedList <Product> _products_for_trolley = new LinkedList<>();

    public Trolley(LinkedList general_products, int min_size, int max_size){
        _highest_trolleyID ++;
        _trolleyID = _highest_trolleyID;
        _position = new Point(0,0);
        _general_products = general_products;
        _min_size  = min_size;
        _max_size = max_size;
    }

    public Point get_position(){
        return _position;
    }

    public LinkedList<Product> get_products() {
        return _loaded_products;
    }

    public LinkedList<Product> get_general_products() {
        return _general_products;
    }

    public LinkedList <Product> get_products_for_trolley() {
        return _products_for_trolley;
    }

    public static int get_number_of_trolleys() {
        return _highest_trolleyID;
    }

    public int get_trolleyID() {
        return _trolleyID;
    }

    public int get_min_size(){
        return _min_size;
    }

    public int get_max_size(){
        return _max_size;
    }


    /**
     * überträgt alle Produkte in eine extra Liste, welche in den Trolley passen.
     */
    public int productsForTrolley(){
        for(int i = 0; i < _general_products.size(); i++){
            if(_min_size <= _general_products.get(i).get_size() &&
                    _max_size >= _general_products.get(i).get_size() &&
                    !_products_for_trolley.contains(_general_products.get(i))){
                _products_for_trolley.add(_general_products.get(i));
            }
        }
        return _products_for_trolley.size();
    }

    /**
     * Überprüft, ob an der aktuellen Position vom Trolley ein Produkt liegt,
     * indem die Liste an existierenden Produkten durchgeschaut wird.
     * Wenn ein Produkt gefunden wird, wird dies aus dem Verteilerzentrum gelöscht und
     * auf den Trolley aufgeladen.
     */
    public Boolean productFound(){
        for(int i = 0; i < _general_products.size(); i++){
            if (_general_products.get(i).get_position().equals(_position)){
                if(load(_general_products.get(i))){
                    _products_for_trolley.remove(_general_products.get(i));
                    _general_products.remove(i);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * ladet das gefundene Produkt nur dann auf den Trolley auf wenn noch Platz auf dem Trolley ist
     * und das Produkt die gleiche Zieldestination hat wie das erste aufgeladene Produkt.
     */
    private Boolean load(Product prod){
        if(prod.get_size() > _max_size){
            return false;
            //Ziel: dieses Produkt einfach ignorieren und mit einem anderen Trolley dann aufnehmen
        }
        else if(prod.get_size() < _min_size){
            return false;
        }
        else if (isFull(prod)){
            return false;
        }
        else if (_loaded_products.isEmpty()){
            _loaded_products.add(prod);
            return true;
        }
        else{
            Point dest = _loaded_products.getFirst().get_aim();
            Point dest2 = prod.get_aim();
            if(dest.equals(dest2)){
                _loaded_products.add(prod);
                return true;
            }
        }
        return false;
    }

    /**
     * ladet alle Produkte vom Trolley ab
     */
    public void unloadAll(){
        _loaded_products.clear();
    }

    /**
     * überprüft, ob noch Platz ist auf dem Trolley
     */
    private Boolean isFull(Product prod){
        int filled = 0;
        for (int i = 0; i < _loaded_products.size(); i++){
            filled = filled + _loaded_products.get(i).get_size();
        }
        filled = filled + prod.get_size();
        if(filled <= _max_size){
            return false;
        }
        return true;
    }

    /**
     * gibt eine Liste aus mit allen Produkten,
     * welche aktuell auf dem Trolley sind
     */
    public void printLoadedProductList(){
        System.out.println("Aufgeladene Produkte auf Trolley" + _trolleyID + " :");
        for(int i = 0; i < _loaded_products.size(); i++){
            System.out.println("--> " + _loaded_products.get(i).get_name());
        }
    }

    /**
     * gibt eine Liste aus mit allen Produkten, welche aktuell noch
     * im Verteilerzentrum herumliegen
     */
    public void printGeneralProductList(){
        System.out.println("Noch herumliegende Produkte in Verteilerzentrum:");
        for(int i = 0; i < _general_products.size(); i++){
            System.out.println("--> " + _general_products.get(i).get_name());
        }
    }

}
