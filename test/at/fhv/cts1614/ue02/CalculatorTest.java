//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void calculate() {
        Calculator calc = new Calculator();
        System.out.println("Ergebnis: " + calc.calculate("12 13 - 10 + ;"));
        System.out.println("Ergebnis: " + calc.calculate("1234 5 + 1 * 1 2 - + ;"));
    }
}