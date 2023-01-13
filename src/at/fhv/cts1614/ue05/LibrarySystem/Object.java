//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

import java.time.LocalDate;

public abstract class Object {

    private Library _library;
    private int _objectnumber;
    private static int _maxNr;
    private Boolean _available;
    private int made_extensions;
    private LocalDate _returndate;

    public Object(Library library){
        //da das Objekt bei der Instanzierung zur library hinzugefügt wird, kann das Selbe
        //Objekt nicht 2 mal hinzugefügt werden. Das Selbe mit Employee und Customer.
        _library = library;
        _maxNr++;
        _objectnumber =_maxNr;
        _available = true;
        _library.get_objects().put(_objectnumber, this);
        _returndate = null;
    }

    public abstract <T> void printInformation(T object);

    public int get_objectnumber(){
        return _objectnumber;
   }
    public Boolean get_available(){
        return _available;
    }
    public void set_available(Boolean available) {
        _available = available;
    }
    public int getMade_extensions(){
        return made_extensions;
    }
    public void set_madeExtensions(){
        made_extensions++;
    }
    public void resetMadeExtensions(){
        made_extensions = 0;
    }
    public LocalDate get_returndate(){
        return _returndate;
    }

    public void set_returndate(Customer customer){
        if(this instanceof Book || this instanceof Magazine){ //instanceof verwenden ist nicht immer gut
            _returndate = LocalDate.now().plusWeeks(customer.get_role().get_lendingtimeBooks());
        }
        else{
            _returndate = LocalDate.now().plusWeeks(customer.get_role().get_lendingtimeBooks());
        }
    }

    public void resetReturndate(){
        _returndate = null;
    }

    public void makeExtension(Customer customer){
        if(this instanceof Book || this instanceof Magazine){
            _returndate = _returndate.plusWeeks(customer.get_role().get_lendingtimeBooks());
        }
        else{
            _returndate = _returndate.plusWeeks(customer.get_role().get_lendingtimeOthers());
        }
    }
}
