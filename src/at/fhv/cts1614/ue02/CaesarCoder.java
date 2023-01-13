//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02;

import java.util.Locale;

public class CaesarCoder {
    private String _message;
    private int _shift;

    public CaesarCoder(int shift, String message){
        _shift = shift;
        _message = message;
    }

    public String encode(){
        StringBuilder result = new StringBuilder();
        for(char character : _message.toCharArray()){
            int originalALphabetPosition;
            int newAlphabetPosition;
            char newCharacter;
            if(64 < character &&  91 > character) {
                originalALphabetPosition = character - 'A';
                newAlphabetPosition = (originalALphabetPosition + _shift) % 26;
                newCharacter = (char) ('A' + newAlphabetPosition);
                result.append(newCharacter);
            }
            else if(96 < character && 123 > character){
                int originalAlphabetPosition = character - 'a';
                newAlphabetPosition = (originalAlphabetPosition + _shift) % 26;
                newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            }
            else{
                result.append(character);
            }
        }
        return result.toString();
    }

    public String decode(){
        int shift = _shift * (-1);
        StringBuilder result = new StringBuilder();
        for(char character : _message.toCharArray()){
            int newASCIIPoistion;
            char newCharacter;
            int originalALphabetPosition;
            if((64 < character &&  91 > character) || (96 < character && 123 > character)) {
                if((64 < character &&  91 > character)){
                    originalALphabetPosition = character - 'A';
                }
                else{
                    originalALphabetPosition = character - 'a';
                }
                if((originalALphabetPosition+shift) < 0){
                    newASCIIPoistion = character + (26 - _shift);
                    newCharacter = (char) newASCIIPoistion;
                }
                else{
                    newASCIIPoistion = character + shift;
                    newCharacter = (char) newASCIIPoistion;
                }
                result.append(newCharacter);
            }
            else{
                result.append(character);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        CaesarCoder coder = new CaesarCoder(7, "My.. jadsoi 192 Message zz yy & a A H Ö äÄ");
        String message = coder.encode();
        System.out.println(message);
        CaesarCoder coder1 = new CaesarCoder(7, message);
        message = coder1.decode();
        System.out.println(message);

    }
}
