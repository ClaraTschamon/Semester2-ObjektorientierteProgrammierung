//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

public class Book extends Object implements Reservable{

    private String _title;
    private String _author;
    private Enums.BookGenre _genre;
    private int _isbn;
    private String _publisher;

    public Book(Library library, String title, int ISBN, String publisher, Enums.BookGenre genre, String author){
        super(library);
        _title = title;
        _author = author;
        _genre = genre;
        _isbn = ISBN;
        _publisher = publisher;
    }

    public String get_author() {
        return _author;
    }

    public Enums.BookGenre get_genre() {
        return _genre;
    }

    @Override
    public <T> void printInformation(T object) {
        System.out.println("Title: "+ _title);
        System.out.println("------------------------------------");
        System.out.println("Author: " + _author);
        System.out.println("------------------------------------");
        System.out.println("Genre: " + _genre);
        System.out.println("------------------------------------");
        System.out.println("ISBN: " + _isbn);
        System.out.println("------------------------------------");
        System.out.println("Publisher: " + _publisher);
        System.out.println("------------------------------------");
    }
}
