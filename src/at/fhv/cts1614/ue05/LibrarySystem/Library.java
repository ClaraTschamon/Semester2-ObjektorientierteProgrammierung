//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

import java.util.*;

public class Library {

    private Map<Integer, Object> _objects = new HashMap<>();//Objektnummer, Object
    private List<Employee> _employees = new LinkedList<>();
    private List<Customer> _customers = new LinkedList<>();
    private Map<Object, Customer> _reservations = new HashMap<>();
    private Map<Customer ,ArrayList<Object>> _lended = new HashMap<>(); //ein kunde kann mehrere Reservierungen haben
    private Catalog _catalog = new Catalog(this);

    public Map<Integer, Object> get_objects() {
        return _objects;
    }

    public List<Employee> get_employees() {
        return _employees;
    }

    public List<Customer> get_customers() {
        return _customers;
    }

    public Map<Object, Customer> get_reservations() {
        return _reservations;
    }

    public Map<Customer, ArrayList<Object>> get_lended() {
        return _lended;
    }

    public Catalog get_catalog() {
        return _catalog;
    }
}