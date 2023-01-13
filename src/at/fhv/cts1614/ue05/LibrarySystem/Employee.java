//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

import java.util.*;

public class Employee {
    private String _name;
    private Library _library;

    public Employee(String name, Library library){
        _name = name;
        _library=library;
        library.get_employees().add(this);
    }

    public void removeObject(Object object){
        try{
            if(!(_library.get_objects().containsValue(object))){
                throw new NotPossibleException("This Object doesn't exist");
            }
            else{
                _library.get_objects().remove(object.get_objectnumber());
            }
        }catch(NotPossibleException e){
            System.out.println(e.getMessage());
        }
    }

    public void printInventar(){
        System.out.println("..................................................");
        System.out.println("Inventar: ");
        Set<Integer> keys = _library.get_objects().keySet();
        Iterator<Integer> iterator = keys.iterator();

        while(iterator.hasNext()) {
            Integer x = iterator.next();
            System.out.print(_library.get_objects().get(x).getClass().getSimpleName());
            System.out.println(" with objectnumber: " + x);
        }
        System.out.println("Do you want to get more information on an item? If yes type 'yes', if no type 'no'");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        while(!(string.equals("yes")|| string.equals("no"))){
            System.out.println("Please type either 'yes' or 'no'...");
            string = scanner.next();
        }
        switch (string){
            case "no":
                break;
            case "yes":
                try{
                    System.out.println("Please type in the objectnumber...");
                    Scanner intscanner = new Scanner(System.in);
                    int objectnumber = intscanner.nextInt();

                    while(!(objectnumber > 0 && objectnumber <= _library.get_objects().size())){
                        System.out.println("Please enter a valid objectnumber...");
                        objectnumber = intscanner.nextInt();
                    }
                    getInformation(objectnumber);
                    break;
                }catch(InputMismatchException ime){
                    System.out.println("This was not a number.");
                }

        }
    }

    public void getInformation(int objectnumber){
        System.out.println("..................................................");
        Object object = _library.get_objects().get(objectnumber);
        System.out.println("Information on: \n" + object.getClass().getSimpleName() +
                " with objectnumber: "+ object.get_objectnumber());
        System.out.println("------------------------------------");
        object.printInformation(object);
        System.out.println("Available: " + object.get_available());
        System.out.println("------------------------------------");
        System.out.print("Reservation ");
        if(object instanceof Reservable<?> && _library.get_reservations().containsKey(object)){
            System.out.println("by : " + _library.get_reservations().get(object).get_firstname() + " " +
            _library.get_reservations().get(object).get_lastname());
        }
        else{
            System.out.println(": No reservation");
        }
        System.out.println("------------------------------------");
        System.out.println("Lended: ");

        if(object.get_available() == true){
            System.out.println("No");
        }

        for (Customer key : _library.get_lended().keySet()) {
            ArrayList<Object> value = _library.get_lended().get(key);
            if(value.contains(object)){
                System.out.println("by " + key.get_firstname() + " " + key.get_lastname());
                System.out.println("returndate: " + object.get_returndate());
            }
        }
        System.out.println("------------------------------------");
    }

    public static void changeCustomerRole(Customer customer, Role role){
        //gehört static aber dann kann ich es nicht aus dem main ausrufen...
        customer.set_role(role);
        System.out.println(customer.get_firstname() + " is now  a " + role.getClass().getSimpleName());
    }
}
