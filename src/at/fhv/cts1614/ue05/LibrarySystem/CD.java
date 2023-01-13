//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

public class CD extends Object implements Reservable{
    String _title;
    String _interpret;
    Enums.MusicGenre _genre;

    public CD (Library library, String title, String interpret, Enums.MusicGenre genre){
        super(library);
        _title = title;
        _interpret = interpret;
        _genre = genre;
    }

    public Enums.MusicGenre get_genre() {
        return _genre;
    }

    @Override
    public <T> void printInformation(T object){
        System.out.println("Title: " + _title);
        System.out.println("------------------------------------");
        System.out.println("Interpret: " + _interpret);
        System.out.println("------------------------------------");
        System.out.println("Genre: " + _genre);
        System.out.println("------------------------------------");
    }
}
