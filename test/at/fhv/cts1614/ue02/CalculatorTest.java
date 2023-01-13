//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02;
import at.fhv.cts1614.ue02.Calculator.Calculator;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    void calculate() {
        Calculator calc = new Calculator();
        System.out.println("Ergebnis: " + calc.calculate("12 13 - 10 + ;"));
        System.out.println("Ergebnis: " + calc.calculate("1234 5 + 1 * 1 2 - + ;"));
    }
}