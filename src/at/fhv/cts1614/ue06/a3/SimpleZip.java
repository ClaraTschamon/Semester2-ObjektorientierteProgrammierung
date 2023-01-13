//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue06.a3;


import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class SimpleZip {

    //command = "simplezip -p archievename extension";
    //command = "simplezip -u archievename

    public static void main(String[] args){

        if(!args[0].equals("simplezip")){
            System.err.println("First argument must be 'simplezip'");
            System.exit(1);
        }
        else if(args[1].equals("-p")){
            filter(args[2], args[3]);
        }
        else if (args[1].equals("-u")){
            try{
                unzipFiles(args[2]);
            }catch (IOException e){
                System.out.println("In catchblock");
                System.out.println("Please enter a command in this form: ");
                System.out.println("simplezip -p archievename extension ... to pack");
                System.out.println("simplezip -u archievename ... to unpack");
                e.printStackTrace();
            }
        }
    }

    /**
     * Method used form zipFiles
     * Puts all files in the current workingdirectory with the given extension in a List.
     */
    private static void filter(String archievename, String extension){
        File currdirectory = new File(System.getProperty("user.dir"));
        //System.getProperty("user.dir") funktioniert nur in der Kommandozeile dass ich direkt in mein Verzeichnis komme
        //File currdirectory = new File(System.getProperty("user.dir"));

        File[]directorylisting = currdirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(extension);
            }
        });
        List <File> files_to_add = Arrays.stream(directorylisting).collect(Collectors.toList());
        zipFiles(files_to_add, archievename);
    }

    
    /**
     * zippes all the files in an archieve
     */
    private static void zipFiles(List<File> files, String archievename){
        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            //fileOutputStream = new FileOutputStream("../../../src/at/fhv/cts1614/ue06/a3NEW/" + archievename + ".zip");
            fileOutputStream = new FileOutputStream(System.getProperty("user.dir").concat(archievename).concat(".zip"));
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
            for(File file:files){
                File input = new File(file.getAbsolutePath());
                fileInputStream = new FileInputStream(input);
                ZipEntry zipEntry = new ZipEntry(input.getName());
                System.out.println("Zipping the file: " + input.getName());
                zipOutputStream.putNextEntry(zipEntry);
                byte[] data = new byte[4*1024]; //diese Größe kann als Konstante filegröße festgelegt werden
                //es wäre auch mit fileInputStream.transferTo(zipOutputStream) möglich gewesen.
                int size = 0;
                while((size = fileInputStream.read(data)) != -1){
                    zipOutputStream.write(data, 0, size);
                }
                zipOutputStream.flush();
                fileInputStream.close();
            }
            zipOutputStream.close();
            System.out.println("Done. Zipped the file(s)");
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(fileOutputStream != null) fileOutputStream.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * unzippes all files in the given zip directory in this directory: src/at/fhv/cts1614/ue06/a3
     */
    private static void unzipFiles(String archievename) throws IOException {

        String fileZip = System.getProperty("user.dir").concat(archievename).concat(".zip");
        File destDir = new File(System.getProperty("user.dir"));
        byte[] buffer = new byte[1024];
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null) {
            File destFile = new File(destDir, zipEntry.getName());
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
            int len;
            while ((len = zipInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.close();
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.closeEntry();
        zipInputStream.close();
        System.out.println("Done. Unpacked the file(s)");
    }
}
