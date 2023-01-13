//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02.CaesarCoder;

public class CaesarCoder {
    private String _message;
    private int _shift;

    public CaesarCoder(int shift, String message){ //shift funktioniert noch nicht so gut
        _shift = shift%26;
        _message = message;
    }

    public String encode(){
        String result = doIt();
        return result;
    }

    public String decode(){
        _shift = _shift * (-1);
        String result = doIt();
        return result;
    }

    private String doIt(){
        StringBuilder result = new StringBuilder();
        for(char character : _message.toCharArray()){
            int originalAlphabetPosition;
            int newAlphabetPosition;
            char newCharacter;
            if(64 < character &&  91 > character) {
                originalAlphabetPosition = character - 'A';
                if(_shift >= 0){
                    newAlphabetPosition = (originalAlphabetPosition + _shift) % 26;
                }else{
                    newAlphabetPosition = (originalAlphabetPosition + (26 + _shift)) % 26;
                }
                newCharacter = (char) ('A' + newAlphabetPosition);
                result.append(newCharacter);
            }
            else if(96 < character && 123 > character){
                originalAlphabetPosition = character - 'a';
                if(_shift >= 0){
                    newAlphabetPosition = (originalAlphabetPosition + _shift) % 26;
                }else{
                    newAlphabetPosition = (originalAlphabetPosition + (26 + _shift)) % 26;
                }
                newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            }
            else{
                result.append(character);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        CaesarCoder coder = new CaesarCoder(-1, "AZ az bykex 172{ jr3.. ");
        String message = coder.encode();
        System.out.println(message);
        CaesarCoder coder1 = new CaesarCoder(-1, message);
        message = coder1.decode();
        System.out.println(message);
    }
}
