//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

import java.util.*;

public class Customer{ //BenutzerId fehlt
    private Role _role;
    private Library _library;
    private String _firstname;
    private String _lastname;
    private String _address;
    private List<String> _messages = new LinkedList<>();
    private List<Object> _past_lendings = new LinkedList<>();

    public Customer(Role role, Library library, String firstname, String lastname, String address){
        _library = library;
        _firstname = firstname;
        _lastname = lastname;
        _address = address;
        _role = role;
        library.get_customers().add(this); //als Methode addCustomer() in Library machen
    }

    public Library get_library(){
        return _library;
    }
    public String get_firstname(){
        return _firstname;
    }

    public  String get_lastname(){ return _lastname;}

    public Role get_role() {
        return _role;
    }

    public void set_role(Role role) {
        _role = role;
    }

    public void searchCatalog(){
        System.out.println("If you want to search by Category type 'C'.");
        System.out.println("If you want to search for Books by Author type 'A'");
        System.out.println("If you want to search by Filmgenre type 'F'" );
        System.out.println("If you want to quit, type 'Q'");
        Scanner input = new Scanner(System.in);
        String string = input.next();
        while (!(string.equals("C")|| string.equals("A") || string.equals("Q") || string.equals("F"))){
            System.out.println("Please type in a valid letter");
            string = input.next();
        }
        switch(string){
            case "C":
                System.out.println("Type in the Category you want to search for (Books, Films, etc...)");
                string = input.next();
                _library.get_catalog().searchByCategory(string);
                break;
            case "A":
                System.out.println("Type in the name of an Author...");
                string = input.next();
                _library.get_catalog().searchByAuthor(string);
                break;
            case "F":
                System.out.println("Type in a Filmgenre");
                string = input.next();
                _library.get_catalog().searchByFilmgenre(string);
            case "Q":
                break;
        }
    }

    public void reserveObject(Object object){
        if(object.get_available() == true){
            System.out.println("The Object is free. You can not put a reservation on it.");
        }
        else if(!(object instanceof Reservable<?>)){
            System.out.println("The Objecttype " + object.getClass().getSimpleName() + " can not be reserved.");
        }
        else{
            if(_library.get_reservations().containsKey(object)){ //Gibt es schon eine Reservierung?
                if(_library.get_reservations().get(object) == this){//bin die Reservierung ich?
                    System.out.println("You already reserved this Item.");
                }
                else if(_library.get_reservations().get(object) != this){//ist die Reservierung wer anders?
                    System.out.println("Somebody else reserved this Item.");
                }
            }
            else if(_library.get_lended().get(this)!= null && _library.get_lended().get(this).contains(object)){
                System.out.println("You can not reseve an object that you have currently lended.");
            }
            else if(!_library.get_reservations().containsKey(object)){
                _library.get_reservations().put(object, this);
            }
        }
    }

    public void deleteReservation(Object object){
        try{
            if(object instanceof Videogame){
                throw new NotPossibleException("You can not reserve a Videogame");
            }
            else{
                if(!_library.get_reservations().containsKey(object)){//wenn die reservierungen dieses Objekt nicht beinhaltet
                    throw new NotPossibleException("You don't have a reservation on this object.");
                }
                else if((_library.get_reservations().containsKey(object)&&!(_library.get_reservations().get(object) == this))){//wenn die Reservierung nicht von mir ist...
                    throw new NotPossibleException("You don't have a reservation on this object.");
                }
                else{
                    _library.get_reservations().remove(object, this);
                    //weniger über getter und setter, mehr über delgation... also in library reservierung machen
                    //dann machen auch exceptions mehr sinn
                }
            }
        }catch (Exception e){ //nicht: catch: Exception
            System.out.println(e.getMessage());
        }
    }

    public void lendObject(Object object){
        if(_library.get_lended().containsKey(this) && _library.get_lended().get(this).contains(object)){
            System.out.println("You already have this Object at home. The returndate is: " + object.get_returndate());
            System.out.println("Do you want to make an extension? If yes type 'yes', if no type 'no'");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();
            while(!(answer.equals("yes")||answer.equals("no"))){ //gehört in die Bibliothek
                System.out.println("Please enter either 'yes' or 'no'");
                answer = scanner.next();
            }
            if(answer.equals("yes")){
                makeExtension(object);
            }
        }
        else if(object.get_available() == false) {
            System.out.println("This Object is not available at the moment");
        }
        else if(_library.get_reservations().containsKey(object)&&!(_library.get_reservations().get(object) == this)){
            System.out.println("This Object is already reserved by somebody else");
        }
        else{
            if(_role.get_lendingfee() > 0){
                System.out.println("You have to pay "+ _role.get_lendingfee() + "€ lendingfee.");
                System.out.println("Type: 'pay' to pay. Or 'no' to cancel'.");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.next();
                while(! (answer.equals("pay")|| answer.equals("no"))){
                    System.out.println("Please enter either 'pay' or 'no'...");
                    answer = scanner.next();
                }
                if(answer.equals("no")){
                    System.out.println("You cancelled the lending process.");
                }
                else{
                    lendIt(object);
                }
            }
            else{
                lendIt(object);
            }
        }
    }

    private void lendIt(Object object){
        if(!_library.get_lended().containsKey(this)){
            _library.get_lended().put(this, new ArrayList<Object>());

        }
        _library.get_lended().get(this).add(object);
        object.set_available(false);
        object.set_returndate(this);
        System.out.println("Please return Object until: " + object.get_returndate());

        //meine reservierung löschen falls diese vorhanden war
        if(_library.get_reservations().containsKey(object)&&_library.get_reservations().get(object) == this){
            _library.get_reservations().remove(object,this);
        }
    }

    public void returnObject(Object object){
        try{
            if(!_library.get_lended().containsKey(this)){ //ist für mich ein Eintrag vorhanden?
                throw new NotPossibleException("You didn't lend a book.");
            }
            else if(!_library.get_lended().get(this).contains(object)){
                throw new NotPossibleException("You didn't lend this book.");
            }
            else{
                _library.get_lended().get(this).remove(object);
                object.set_available(true);
                object.resetReturndate(); //setzt returndate auf null
                _past_lendings.add(object);
                System.out.println("Object: " + object.get_objectnumber() + " returned.");
                object.resetMadeExtensions();
                //falls Reservierung vorhanden ist Nachricht an Person senden, dass das Objekt wieder frei ist.
                if(_library.get_reservations().containsKey(object)){
                    Customer customer = _library.get_reservations().get(object);
                    customer._messages.add("The object '" + object.get_objectnumber() +"' that you have a reservation on is now available.");
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void makeExtension(Object object){
        if(_library.get_reservations().containsKey(object)){
            System.out.println("This object is reserved for somebody. You can not make an extension.");
        }
        else if(object.getMade_extensions() < _role.getMAXEXTENSIONS()){
            object.makeExtension(this);
            object.set_madeExtensions();
            System.out.println("New returndate: " + object.get_returndate());
        }
        else{
            System.out.println("You can not make another extension.");
        }
    }

    public void printMyObjects(){
        int i = 0;
        System.out.println("You have currently lended these Objects: ");
        while(i < _library.get_lended().get(this).size()){
            System.out.println(_library.get_lended().get(this).get(i).get_objectnumber());
            i++;
        }
        System.out.println("Total number: " + _library.get_lended().get(this).size());
    }

    public void printMyReservations(){
        System.out.println("Your reservations: ");
        if(!_library.get_reservations().containsValue(this)){
            System.out.println("You don't have any reservations");
        }
        else{
            Set<Object> keys = _library.get_reservations().keySet();
            Iterator<Object> iterator = keys.iterator();
            while(iterator.hasNext()) {
                Object x = iterator.next();
                if (_library.get_reservations().get(x) == this) { //auch Bibliothek machen lassen
                    System.out.print(x.getClass().getSimpleName() + "\t");
                    System.out.println(x.get_objectnumber());
                }
            }
        }
    }

    public void printMyMessages(){
        for(int i = 0; i < _messages.size(); i++){
            System.out.println(_messages.get(i));
        }
    }

    public void printPastLendings(){
        System.out.println("Your past Lendings: ");
        for(Object o : _past_lendings){
            System.out.println(o.getClass().getSimpleName() + " " + o.get_objectnumber());
            o.printInformation(o);
        }
    }
}
