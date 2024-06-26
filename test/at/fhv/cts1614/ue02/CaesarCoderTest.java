//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02;

import at.fhv.cts1614.ue02.CaesarCoder.CaesarCoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCoderTest {

    @Test
    void testEncode() {
        CaesarCoder message = new CaesarCoder(3, "AB CX");
        assertEquals("DE FA", message.encode().toString());
    }

    @Test
    void testDecode() {
        CaesarCoder message = new CaesarCoder(3, "DE BA");
        assertEquals("AB YX", message.decode().toString());
    }
}