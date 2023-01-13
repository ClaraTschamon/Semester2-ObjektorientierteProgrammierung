//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue03.a2;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void Test1(){

        Person Donald = new Person("Donald", "Duck", Person.Gender.male, "01.01.1999");
        Person Daisy = new Person("Daisy", "Queen", Person.Gender.female, "02.02.1998");
        Person Mickey = new Person("Mickey", "Mouse", Person.Gender.male, "03.03.2003");
        Person Dagobert = new Person("Dagobert", "Duck", Person.Gender.male, "04.04.2002");

        Daisy.marry(Donald, Dagobert, Mickey);
        assertEquals(Donald, Daisy.get_married_with());
        assertEquals(Daisy, Donald.get_married_with());
    }

    @Test
    void Test2(){
        Person Donald = new Person("Donald", "Duck", Person.Gender.male, "01.01.1999");
        Person Daisy = new Person("Daisy", "Queen", Person.Gender.female, "02.02.1998");
        Person Mickey = new Person("Mickey", "Mouse", Person.Gender.male, "03.03.2003");
        Person Dagobert = new Person("Dagobert", "Duck", Person.Gender.male, "04.04.2002");

        Daisy.marry(Donald, Dagobert, Mickey);
        Assertions.assertEquals(Person.Married.yes, Donald.get_married());
    }

    @Test
    void Test3(){
        Person Donald = new Person("Donald", "Duck", Person.Gender.male, "01.01.1999");
        Person Daisy = new Person("Daisy", "Queen", Person.Gender.female, "02.02.1998");
        Person Mickey = new Person("Mickey", "Mouse", Person.Gender.male, "03.03.2003");
        Person Dagobert = new Person("Dagobert", "Duck", Person.Gender.male, "04.04.2002");

        Daisy.marry(Donald, Dagobert, Mickey);
        assertEquals(23, Donald.get_age());
    }

    @Test
    void Test4() {
        Person Donald = new Person("Donald", "Duck", Person.Gender.male, "01.01.2002");
        Person Daisy = new Person("Daisy", "Queen", Person.Gender.female, "02.02.1998");
        Person Mickey = new Person("Mickey", "Mouse", Person.Gender.male, "03.03.2003");
        Person Dagobert = new Person("Dagobert", "Duck", Person.Gender.male, "04.04.2002");

        Daisy.marry(Donald, Mickey, Dagobert);
        Daisy.print_marriage_record();
        Daisy.divorce(Donald, "alkohol");
        Daisy.print_divorce_record();
        assertEquals(null, Daisy.get_married_with());
    }

    @Test
    void Test5(){
        Person Donald = new Person("Donald", "Duck", Person.Gender.male, "01.01.2002");
        Person Daisy = new Person("Daisy", "Queen", Person.Gender.female, "02.02.1998");
        Person Mickey = new Person("Mickey", "Mouse", Person.Gender.male, "03.03.2003");
        Donald.marry(Donald, Daisy, Mickey);
        assertEquals(null, Donald.get_married_with());
    }
}