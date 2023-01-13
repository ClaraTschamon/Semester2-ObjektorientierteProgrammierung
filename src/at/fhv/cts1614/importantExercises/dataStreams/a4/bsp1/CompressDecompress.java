package at.fhv.cts1614.importantExercises.dataStreams.a4.bsp1;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressDecompress {
    public static void compress(File source, File destination) throws IOException{
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        GZIPOutputStream gzos = new GZIPOutputStream(fos);
        int read;

        while((read = fis.read(buffer))!= -1){
            gzos.write(buffer, 0, read);
        }
        gzos.finish();
        gzos.close();
        fos.close();
        fis.close();
    }

    public static void decompress(File source, File destination) throws IOException{
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(source);
        GZIPInputStream gzis = new GZIPInputStream(fis);
        FileOutputStream fos = new FileOutputStream(destination);
        int read;

        while((read = gzis.read(buffer))!= -1){
            fos.write(buffer, 0, read);
        }
        gzis.close();
        fis.close();
        fos.close();
    }

    public static void compressNew(File source, File destination) throws IOException{
        //warum funktioniert das nicht? meine Datei hat jetzt mehr Byte. Nicht weniger.

        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        GZIPOutputStream gzos = new GZIPOutputStream(fos);

        int read;
        while((read = fis.read())!= -1){
            gzos.write(read);
        }
        gzos.finish();
        gzos.close();
        fos.close();
        fis.close();
    }

    public static void decompressNew(File source, File destination) throws IOException{

        FileInputStream fis = new FileInputStream(source);
        GZIPInputStream gzis = new GZIPInputStream(fis);
        FileOutputStream fos = new FileOutputStream(destination);
        int read;

        while((read = gzis.read())!= -1){
            fos.write(read);
        }
        gzis.close();
        fis.close();
        fos.close();
    }



    public static void main(String[] args) {

        //compress
        File source = new File("src/at/fhv/cts1614/seminar07/a4/bsp1/meineDatei.txt");
        File destination = new File("src/at/fhv/cts1614/seminar07/a4/bsp1/compressed.txt");

        try{
           compress(source,destination);
           //Ergebnis: meineDatei.txt (source) hat 98 Bytes und compressed.txt (destination) hat 59 Bytes
        }catch (IOException e){
            System.out.println(e);
        }

        //decompress
        File decompressSource = new File("src/at/fhv/cts1614/seminar07/a4/bsp1/compressed.txt");
        File decompressedDestination = new File("src/at/fhv/cts1614/seminar07/a4/bsp1/decompressed.txt");

        try{
            decompress(decompressSource, decompressedDestination);
            //Funktioniert... jetzt haben meinDatei.txt und decompressed.txt beide 137Bytes
        }catch(IOException e){
            System.out.println(e);
        }

//        //compressNew
//        try{
//            compressNew(source,destination);
//        }catch (IOException e){
//            System.out.println(e);
//        }
//
//        //decompressNew
//        try{
//            decompressNew(decompressSource, decompressedDestination);
//        }catch(IOException e){
//            System.out.println(e);
//        }
    }
}
