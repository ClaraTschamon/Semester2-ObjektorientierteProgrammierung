//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02;
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;

public class PasswordGenerator {
    private final Random random;

    public PasswordGenerator(int seed) {
        random = new Random(seed);
    }

    /**
     Creates a random Password out of small letters.
     * @param length length of the password
     */
    public String create(int length){
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++){
            int position = random.nextInt(26);
            password.append(letters.charAt(position));
        }
        return password.toString();
    }

    /**
     Puts the new passwords in a list.
     * @param length length of the password
     * @param numberOfPasswords number of passwords to generate
     */
    public LinkedList newPasswords(int length, int numberOfPasswords, LinkedList list){

        double maxPasswords = Math.pow(26, length);

        for (int i = 0; i < numberOfPasswords; i++){
            if(numberOfPasswords > maxPasswords){
                System.out.println("Es können nur " + (int) maxPasswords + " EINZIGARTIGE Passwörter mit der Länge " + length + " erstellt werden!");
                break;
            }
            String newPw = create(length);
            if (list.contains(newPw)){
                i--;
                continue;
            }
            list.add(newPw);
        }
        return list;
    }

    /**
     Adds more passwords to the passwordlist
     * @param list the already existing passwordlist
     * @param length length of the password
     * @param addsize how many passwords to be added
     */
    public LinkedList generateMore(LinkedList list, int length, int addsize){
        double maxPasswords = Math.pow(26, length);
        if(list.size()+addsize > maxPasswords){
            System.out.println("Es können nur " + (int) maxPasswords + " EINZIGARTIGE Passwörter mit der Länge " + length + " erstellt werden!");
        }
        else{
            newPasswords(length, addsize, list);
            return list;
        }
        return list;
    }

    public static void main(String[] args) {

    }
}
