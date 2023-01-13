//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue06.a2;

import java.io.IOException;
import java.io.Writer;

public class CaesarWriter extends Writer {

    private final Writer _writer;
    private int _shift;

    public CaesarWriter(Writer writer, int shift){
        _writer = writer;
        _shift = shift % 26;
    }

    private char encode(char character){
        int originalAlphabetPosition;
        int newAlphabetPosition;
        if(64 < character &&  91 > character) {
            originalAlphabetPosition = character - 'A';
            if(_shift >= 0){
                newAlphabetPosition = (originalAlphabetPosition + _shift) % 26;
            }else{
                newAlphabetPosition = (originalAlphabetPosition + (26 + _shift)) % 26;
            }
            return (char) ('A' + newAlphabetPosition);
        }
        else if(96 < character && 123 > character){
            originalAlphabetPosition = character - 'a';
            if(_shift >= 0){
                newAlphabetPosition = (originalAlphabetPosition + _shift) % 26;
            }else{
                newAlphabetPosition = (originalAlphabetPosition + (26 + _shift)) % 26;
            }
            return (char) ('a' + newAlphabetPosition);
        }
        else{
            return character;
        }
    }

    @Override
    public void write(char[] text, int shift, int len) throws IOException {
        for (int i = 0; i < text.length; i += 1) {
            text[i] = encode(text[i]); //achtung: ich veränder hier das original mit text[i]
        }
        _writer.write(text, shift, len);
    }

    @Override
    public void flush() throws IOException{
        _writer.flush();
    }

    @Override
    public void close() throws IOException{
        flush();
        _writer.close();
    }
}
