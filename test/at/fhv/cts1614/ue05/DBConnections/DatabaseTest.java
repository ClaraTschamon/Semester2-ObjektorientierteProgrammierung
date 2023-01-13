package at.fhv.cts1614.ue05.DBConnections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void Test1(){
        Database mydatabase = new Database("myDB");
        DBConnection connection1 = new DBConnection("Clara");
        DBConnection connection2 = new DBConnection("Lukas");
        DBConnection connection3 = new DBConnection("David");
        DBConnection connection4 = new DBConnection("Hänsel");
        DBConnection connection5 = new DBConnection("Gretel");
        DBConnection connection6 = new DBConnection("Peter");
        DBConnection connection7 = new DBConnection("Max");
        DBConnection connection8 = new DBConnection("Moritz");

        mydatabase.connect(connection1);
        mydatabase.connect(connection1);
        mydatabase.connect(connection2);
        mydatabase.connect(connection3);
        mydatabase.connect(connection4);
        mydatabase.connect(connection5);
        assertEquals(5, mydatabase.get_active_connections().size());
        mydatabase.connect(connection6);

        mydatabase.disconnect(connection1);
        mydatabase.connect(connection6);
        mydatabase.connect(connection7);
        mydatabase.disconnect(connection8);

        mydatabase.connect(connection8);
        //löschen eines Objekts durch referenz auf null setzen...
    }

    @Test
    void Test2(){
        Database mydatabase = new Database("myDB");
        DBConnection connection1 = new DBConnection("Clara");
        DBConnection connection2 = new DBConnection("Lukas");
        DBConnection connection3 = new DBConnection("David");
        DBConnection connection4 = new DBConnection("Hänsel");
        DBConnection connection5 = new DBConnection("Gretel");
        DBConnection connection6 = new DBConnection("Peter");
        DBConnection connection7 = new DBConnection("Max");
        DBConnection connection8 = new DBConnection("Moritz");

        mydatabase.connect(connection1);
        mydatabase.connect(connection1);
        mydatabase.connect(connection2);
        mydatabase.connect(connection3);
        mydatabase.connect(connection4);
        mydatabase.connect(connection5);
        mydatabase.connect(connection6);
        assertEquals(5, mydatabase.get_active_connections().size());

        mydatabase.disconnect(connection1);
        mydatabase.connect(connection6);
        mydatabase.connect(connection7);
        mydatabase.disconnect(connection8);

        mydatabase.connect(connection8);
    }

    @Test
    void Test3(){
        Database mydatabase = new Database("myDB");
        DBConnection connection1 = new DBConnection("Clara");
        DBConnection connection2 = new DBConnection("Lukas");
        DBConnection connection3 = new DBConnection("David");
        DBConnection connection4 = new DBConnection("Hänsel");
        DBConnection connection5 = new DBConnection("Gretel");
        DBConnection connection6 = new DBConnection("Peter");
        DBConnection connection7 = new DBConnection("Max");
        DBConnection connection8 = new DBConnection("Moritz");

        mydatabase.connect(connection1);
        mydatabase.connect(connection1);
        mydatabase.connect(connection2);
        mydatabase.connect(connection3);
        mydatabase.connect(connection4);
        mydatabase.connect(connection5);
        mydatabase.connect(connection6);
        mydatabase.disconnect(connection1);
        assertEquals(4, mydatabase.get_active_connections().size());
    }

}