//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue06.a2;
import java.io.*;

public class MainCaesar {
    public static void main(String[] args) throws IOException {

        int shift_encode = 7;
        int shift_decode = -7;

        //Encode mit CaesarWriter
        try(
                //ich mache alles so verschachtelt weil sonst Fehlermeldungen kommen beim closen.
                BufferedReader bufferedReader = new BufferedReader(new FileReader("src/at/fhv/cts1614/ue06/a2NEW/Original.txt"));
                BufferedWriter bufferedWriter = new BufferedWriter(new CaesarWriter(new FileWriter("src/at/fhv/cts1614/ue06/a2NEW/Encoded.txt"), shift_encode));)

        {
            int ch=0;
            while((ch = bufferedReader.read()) != -1){
                bufferedWriter.write(ch);
            }
        }catch(IOException e){
            e.printStackTrace();
        }



        //Decode mit CaesarWriter
        try(
            FileReader fileReader = new FileReader("src/at/fhv/cts1614/ue06/a2NEW/Encoded.txt");
            CaesarReader caesarReader = new CaesarReader(fileReader, shift_decode);
            BufferedReader bufferedReader = new BufferedReader(caesarReader);

            FileWriter fileWriter = new FileWriter("src/at/fhv/cts1614/ue06/a2NEW/Decoded.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);)
        {
            int ch=0;
            while((ch = bufferedReader.read()) != -1){
                bufferedWriter.write(ch);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
