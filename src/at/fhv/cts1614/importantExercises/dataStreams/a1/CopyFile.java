package at.fhv.cts1614.importantExercises.dataStreams.a1;

import java.io.*;


public class CopyFile {
    public static void main(String[] args) {
        //https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html

        try( //try with resources
                FileReader filereader = new FileReader("src/at/fhv/cts1614/seminar07/a1/text.txt");
                BufferedReader reader = new BufferedReader(filereader);

                FileWriter filewriter = new FileWriter("src/at/fhv/cts1614/seminar07/a1/textCopy.txt");
                BufferedWriter writer = new BufferedWriter(filewriter);)

        {
            int ch=0;
            while((ch = reader.read()) != -1){
                writer.write(ch);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }

        try( //Filereader ist char orientiert, file FileInputStream Byte orientiert
                FileInputStream reader = new FileInputStream("src/at/fhv/cts1614/seminar07/a1/Bild.jpg");

                FileOutputStream writer = new FileOutputStream(new File("src/at/fhv/cts1614/seminar07/a1/BildCopy.jpg"));)

        {
            int ch=0;
            while((ch = reader.read()) != -1){
                writer.write(ch);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }
}
