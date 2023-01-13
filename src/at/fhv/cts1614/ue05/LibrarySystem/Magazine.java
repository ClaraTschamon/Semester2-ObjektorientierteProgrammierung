//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

public class Magazine extends Object implements Reservable{
    String _title;

    public Magazine(Library library, String title){
        super(library);
        _title = title;
    }

    @Override
    public <T> void printInformation(T object) {
        System.out.println("Title: " + _title);
        System.out.println("------------------------------------");
    }
}
