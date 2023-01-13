package at.fhv.cts1614.ue05.LibrarySystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void Test1() {
        Library myLibrary = new Library();

        Book book1 = new Book(myLibrary, "book1", 1915, "Penguin", Enums.BookGenre.Krime, "Somebody");
        Book book2 = new Book(myLibrary, "book2", 1392, "Brockhaus", Enums.BookGenre.RomantikNovel, "Somebody");
        Film mammaMia = new Film(myLibrary, "Mamma Mia", Enums.FilmGenre.Comedy, Enums.FSK.Six);
        Videogame videogame1 = new Videogame(myLibrary, "MarioBros", Enums.Console.Nintendo, Enums.FSK.Eight);
        CD cd1 = new CD(myLibrary, "Christmasmix2000", "Wham", Enums.MusicGenre.Other);

        assertEquals(5, myLibrary.get_objects().size());
    }

    @Test
    void Test2(){
        Library myLibrary = new Library();
        Customer clara = new Customer( new ExternPerson(), myLibrary, "Clara", "Tschamon", "Hummelweg");
        Customer irmi = new Customer(new Teacher(), myLibrary, "Irmi", "Musterfrau", "Hummelweg 1");
        Customer elisa = new Customer(new Student(), myLibrary, "Elisa", "Gotwald", "Baumgarten 27");
        Employee hannah = new Employee("Hannah", myLibrary);

        Book book1 = new Book(myLibrary, "book1", 1915, "Penguin", Enums.BookGenre.Krime, "Somebody");
        Book book2 = new Book(myLibrary, "book2", 1392, "Brockhaus", Enums.BookGenre.RomantikNovel, "Somebody");
        Film mammaMia = new Film(myLibrary, "Mamma Mia", Enums.FilmGenre.Comedy, Enums.FSK.Six);
        Videogame videogame1 = new Videogame(myLibrary, "MarioBros", Enums.Console.Nintendo, Enums.FSK.Eight);
        CD cd1 = new CD(myLibrary, "Christmasmix2000", "Wham", Enums.MusicGenre.Other);

        irmi.lendObject(book1);
        assertEquals(1, myLibrary.get_lended().size());
    }

    @Test
    void Test3(){
        Library myLibrary = new Library();

        Customer irmi = new Customer(new Teacher(), myLibrary, "Irmi", "Musterfrau", "Hummelweg 1");
        Customer elisa = new Customer(new Student(), myLibrary, "Elisa", "Gotwald", "Baumgarten 27");

        Book book1 = new Book(myLibrary, "book1", 1915, "Penguin", Enums.BookGenre.Krime, "Somebody");

        irmi.reserveObject(book1); //reservieren funktioniert nur, wenn das objekt nicht available ist
        irmi.lendObject(book1);
        elisa.reserveObject(book1);
        irmi.returnObject(book1);
        elisa.printMyMessages();
    }

    @Test
    void Test4(){
        Library myLibrary = new Library();

        Customer clara = new Customer( new ExternPerson(), myLibrary, "Clara", "Tschamon", "Hummelweg");
        Customer irmi = new Customer(new Teacher(), myLibrary, "Irmi", "Musterfrau", "Hummelweg 1");
        Customer elisa = new Customer(new Student(), myLibrary, "Elisa", "Gotwald", "Baumgarten 27");
        Employee hannah = new Employee("Hannah", myLibrary);

        assertEquals(4, clara.get_role().get_lendingtimeBooks());
        assertEquals(0.5, clara.get_role().get_lendingfee());
        hannah.changeCustomerRole(clara, new Teacher());
        assertEquals(8, clara.get_role().get_lendingtimeBooks());
        assertEquals(0, clara.get_role().get_lendingfee());
    }
}