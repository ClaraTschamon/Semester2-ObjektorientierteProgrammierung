//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue04;

import java.io.Serializable;

public class Item implements Serializable {

    private static final long serialVersionUID = 2L;
    private String _name;
    private Room _position;

    public Item (String name, Room position, Playground playground){
        _name = name;
        _position = position;
        playground.get_items().add(this);
    }

    public Room get_position() {
        return _position;
    }

    public String get_name(){
        return _name;
    }
}
