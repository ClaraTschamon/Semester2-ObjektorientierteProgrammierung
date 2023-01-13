//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void main() { //warum funktioniert public static void main nicht??
        int length = 8;
        PasswordGenerator generator = new PasswordGenerator(1234);
        LinkedList <String> studentList = new LinkedList <String>();
        studentList = generator.newPasswords(length, 22, studentList);
        studentList = generator.generateMore(studentList, length, 4);
        studentList = generator.generateMore(studentList, length, 1);

        //Liste untereinander ausgeben:
        for (int i = 0; i < studentList.size(); i++){
            System.out.println("Passwort " + (i) + ": " + studentList.get(i));
        }

        assertEquals(17, studentList.indexOf("pqthhibk"));
        assertEquals(26, studentList.indexOf("boqynovx"));
    }
}