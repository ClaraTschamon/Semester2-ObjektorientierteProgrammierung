package at.fhv.cts1614.ue05.LibrarySystem;

public class Controller {
    public static void main(String[] args) {

        /*
            Controller wäre gewesen: Bibliothek mit Methode registriereBenutzer, leihe aus,...
         */
        Library myLibrary = new Library();
        Customer clara = new Customer( new ExternPerson(), myLibrary, "Clara", "Tschamon", "Hummelweg");
        Customer irmi = new Customer(new Teacher(), myLibrary, "Irmi", "Musterfrau", "Hummelweg 1");
        Customer elisa = new Customer(new Student(), myLibrary, "Elisa", "Gotwald", "Baumgarten 27");
        Employee hannah = new Employee("Hannah", myLibrary);

        Book book1 = new Book(myLibrary, "book1", 1915, "Penguin", Enums.BookGenre.Krime, "Somebody");
        Book book2 = new Book(myLibrary, "book2", 1392, "Brockhaus", Enums.BookGenre.RomantikNovel, "Somebody");
        Film mammaMia = new Film(myLibrary, "Mamma Mia", Enums.FilmGenre.Comedy, Enums.FSK.Six);
        Videogame MarioBros = new Videogame(myLibrary, "MarioBros", Enums.Console.Nintendo, Enums.FSK.Eight);
        CD cd1 = new CD(myLibrary, "Christmasmix2000", "Wham", Enums.MusicGenre.Other);

        clara.lendObject(book1);
        clara.makeExtension(book1);
        clara.makeExtension(book1);
        System.out.println(book1.getMade_extensions());
        clara.returnObject(book1);
        System.out.println(book1.getMade_extensions());
        clara.lendObject(book2);
        clara.makeExtension(book2);

    }
}
