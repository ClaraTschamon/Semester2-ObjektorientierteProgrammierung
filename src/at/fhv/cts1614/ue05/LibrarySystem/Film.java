//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

public class Film extends Object implements Reservable{

    String _tile;
    Enums.FilmGenre _genre;
    Enums.FSK _fsk;

    public Film(Library library, String title, Enums.FilmGenre genre, Enums.FSK FSK){
        super(library);
        _tile = title;
        _genre = genre;
        _fsk = FSK;
    }

    public Enums.FilmGenre get_genre() {
        return _genre;
    }

    @Override
    public <T> void printInformation(T object) {
        System.out.println("Title: " + _tile);
        System.out.println("------------------------------------");
        System.out.println("Genre: " + _genre);
        System.out.println("------------------------------------");
        System.out.println("FSK: " + _fsk);
        System.out.println("------------------------------------");

    }
}
