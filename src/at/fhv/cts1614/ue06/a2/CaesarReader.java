package at.fhv.cts1614.ue06.a2;

import java.io.IOException;
import java.io.Reader;

public class CaesarReader extends Reader {

    private final Reader _reader;
    int _shift;

    public CaesarReader(Reader reader, int shift){
        _reader = reader;
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
    public int read(char[] cbuf, int off, int len) throws IOException {
        int readLen = _reader.read(cbuf, off, len);
        for (int i = 0; i < cbuf.length; i += 1) {
            cbuf[i] = encode(cbuf[i]);
        }
        return readLen;
    }

    @Override
    public void close() throws IOException {
        _reader.close();
    }
}
