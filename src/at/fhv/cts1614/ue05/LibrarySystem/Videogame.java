//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

public class Videogame extends Object{
    String _name;
    Enums.Console _console;
    Enums.FSK _fsk;

    public Videogame(Library library, String name, Enums.Console console, Enums.FSK FSK) {
        super(library);
        _name = name;
        _console = console;
        _fsk = FSK;
    }

    @Override
    public <T> void printInformation(T object) {
        System.out.println("Name: " + _name);
        System.out.println("------------------------------------");
        System.out.println("Console: " + _console);
        System.out.println("------------------------------------");
        System.out.println("FKS: " + _fsk);
        System.out.println("------------------------------------");
    }
}
