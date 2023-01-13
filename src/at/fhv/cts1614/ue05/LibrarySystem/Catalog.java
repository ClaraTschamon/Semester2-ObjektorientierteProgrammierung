//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;
import java.util.Map;

public class Catalog {

    private Library _library;
    public Catalog(Library library){
        _library = library;
    }

    public void searchByCategory(String category){
        switch (category){
            case "Books":
            case "Book":
                Book b = new Book(_library, "Example", 0000, "Example", Enums.BookGenre.Other, "Example");
                searchListCategory(b);
                break;
            case "Films":
            case "Film":
                Film f = new Film(_library, "Example", Enums.FilmGenre.Other, Enums.FSK.Eight);
                searchListCategory(f);
                break;
            case "CDs":
            case "CD":
                CD cd = new CD(_library, "Example", "Example", Enums.MusicGenre.Other);
                searchListCategory(cd);
                break;
            case "Magazines":
            case "Magazine":
                Magazine m = new Magazine(_library, "Example");
                searchListCategory(m);
                break;
            case "Videogames":
            case "Games":
            case "Videogame":
                Videogame v = new Videogame(_library, "Example", Enums.Console.Other, Enums.FSK.Eighteen);
                searchListCategory(v);
                break;
        }
    }

    private <T extends Object> void searchListCategory(T object){
        for (Map.Entry<Integer, Object> entry : _library.get_objects().entrySet()) {
            if(entry.getValue().getClass().equals(object.getClass())){
                getInformation(entry.getValue());
            }
        }
    }

    public void searchByAuthor(String author) {
        for (Map.Entry<Integer, Object> entry : _library.get_objects().entrySet()) {
            if (entry.getValue() instanceof Book) {
                if (((Book) entry.getValue()).get_author().equals(author)) {
                    getInformation(entry.getValue());
                }
            }
        }
    }

    public void searchByFilmgenre(String genre){
        switch (genre){
            case "Comedy":
                printGenres(Enums.FilmGenre.Comedy);
                break;
            case "Action":
                printGenres(Enums.FilmGenre.Action);
                break;
            case "ScienceFiction":
                printGenres(Enums.FilmGenre.ScienceFiction);
                break;
            case "Horror":
                printGenres(Enums.FilmGenre.Horror);
                break;
            case "Documentary":
                printGenres(Enums.FilmGenre.Dokumentary);
                break;
            case "Drama":
                printGenres(Enums.FilmGenre.Drama);
                break;
        }
    }

    private void getInformation(Object object){
        object.printInformation(object);
        System.out.println("Available: " + object.get_available());
        System.out.println("------------------------------------");
        System.out.print("Reserved: ");
        if(object instanceof Reservable<?> && _library.get_reservations().containsKey(object)){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
        System.out.println("------------------------------------\n");
    }

    private void printGenres(Enum genre){
        for (Map.Entry<Integer, Object> entry : _library.get_objects().entrySet()) {
            /*
            if(entry.getValue() instanceof Film || entry.getValue() instanceof Book || entry.getValue() instanceof CD){
                if((entry.getValue().getClass())entry.getValue().get_genre().equals(genre)){
                    //warum geht das nicht?????????
                }
            }*/

            if (entry.getValue() instanceof Film){
                if(((Film) entry.getValue()).get_genre().equals(genre)) {
                    getInformation(entry.getValue());
                }
            }else if(entry.getValue() instanceof Book) {
                if(((Book) entry.getValue()).get_genre().equals(genre)) {
                    getInformation(entry.getValue());
                }
            }else if(entry.getValue() instanceof  CD) {
                if (((CD) entry.getValue()).get_genre().equals(genre)) {
                    getInformation(entry.getValue());
                }
            }
        }
    }
}
