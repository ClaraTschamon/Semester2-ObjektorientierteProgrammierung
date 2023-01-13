//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue04;

import java.io.Serializable;

public abstract class Field implements Serializable {
    private static final long serialVersionUID = 3L;
    private String _name;
    private static int _max_nr;
    private int _fieldnr;

    public Field(String name, Playground playground){
        _name = name;
        _max_nr++;
        _fieldnr = _max_nr;
        playground.get_fields().put(_name, this);
    }

    public String get_name() {
        return _name;
    }

    public int get_fieldnr(){
        return _fieldnr;
    }

    public void set_fieldnr(int fieldnr) {
        _fieldnr = fieldnr;
    }

    public abstract void enter(Player player, Direction direction);


    /*
    !The defaultWriteObject() Method writes the non-static and non-transient fields of the
    current class to this stream!
    private void writeObject(ObjectOutputStream out){
        try{
            out.defaultWriteObject();
            out.writeInt(_fieldnr);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void readObject(ObjectInputStream in){
        try{
            in.defaultReadObject();
            _fieldnr = in.readInt();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
     */

}
