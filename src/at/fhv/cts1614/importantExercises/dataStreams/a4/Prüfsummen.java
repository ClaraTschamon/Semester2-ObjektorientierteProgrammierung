package at.fhv.cts1614.importantExercises.dataStreams.a4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.*;

public class Prüfsummen {
    private static String SOURCE_FILE = "src/at/fhv/cts1614/seminar07/a4/SourceFile.txt";
    private static String TARGET_FILE = "src/at/fhv/cts1614/seminar07/a4/TargetFile.zip";

    public static void main(String[] args) {
        try {

            createZipFile();

            FileInputStream fileInputStream= new FileInputStream(TARGET_FILE);
            CheckedInputStream checksum = new CheckedInputStream(fileInputStream, new CRC32());
            //CheckedInputStream checksum = new CheckedInputStream(fileInputStream, new Adler32());
            byte[] buffer = new byte[1024];
            while(checksum.read(buffer, 0, buffer.length) >= 0){

            }
            System.out.println("Checksum: " + checksum.getChecksum().getValue());

        } catch(IOException ioe) {
            System.out.println("IOException : " + ioe);
        }
    }

    private static void createZipFile() throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(TARGET_FILE);
        CheckedOutputStream checksum = new CheckedOutputStream(fileOutputStream, new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checksum);

        FileInputStream fin = new FileInputStream(SOURCE_FILE);
        zipOutputStream.putNextEntry(new ZipEntry(SOURCE_FILE));
        int length;
        byte[] buffer = new byte[1024];
        while((length = fin.read(buffer)) > 0) {
            zipOutputStream.write(buffer, 0, length);
        }

        zipOutputStream.closeEntry();
        fin.close();
        zipOutputStream.close();
    }
}
