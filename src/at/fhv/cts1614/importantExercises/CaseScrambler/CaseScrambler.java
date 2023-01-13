package at.fhv.cts1614.importantExercises.CaseScrambler;

import java.io.*;

public class CaseScrambler extends Reader {

    private final Reader reader;

    public CaseScrambler(Reader reader){
        this.reader = reader;
    }

    @Override
    public int read(char[] text, int off, int len) throws IOException {
        int readLen = reader.read(text, off, len);
        for (int i = 0; i < readLen; i++) {
            if(i == 0){
                text[i] = Character.toLowerCase(text[i]);
            }
            else{
                text[i] = (char)scramble(text[i]);
            }
        }
        return readLen;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    private int scramble(int character){
        if(character >= 'a' && character <= 'z'){
            return Character.toUpperCase(character);
        }
        else if(character >= 'A' && character <= 'Z'){
            return Character.toLowerCase(character);
        }
        return character;
    }

    public static void main(String[] args) {
        try(
                BufferedReader reader = new BufferedReader(new CaseScrambler(new FileReader("src/at/fhv/cts1614/Excercise/CaseScrambler/text.txt")));
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/at/fhv/cts1614/Excercise/CaseScrambler/result.txt"))){

            String line = reader.readLine();
            while(line != null){
                writer.write(line);
                line = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
