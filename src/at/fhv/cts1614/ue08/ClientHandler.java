//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue08;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class ClientHandler implements Runnable{

    private final BufferedReader in;
    private final PrintWriter out;
    private final Properties properties;
    private final File sharedirectory;
    private final Socket serverSocket;

    public ClientHandler(Socket server, Properties properties, File sharedirectory) throws IOException {
        serverSocket = server;
        in = new BufferedReader(new InputStreamReader(server.getInputStream())); //get message from Server
        out = new PrintWriter(server.getOutputStream(), true); //get message out to Server
        this.properties = properties;
        this.sharedirectory = sharedirectory;
    }

    public PrintWriter getOut(){
        return out;
    }

    /**
     * entering username and password
     */
    @Override
    public void run(){
        try{
            out.println("FileServer responding: For connection type your username and your password...");
            while(true){
                String request = in.readLine();
                String[] requestArr = request.split(" ");

                if(properties.containsKey(requestArr[0])){ //schauen, ob properties datei username enthält
                    if(properties.getProperty(requestArr[0]).equals(requestArr[1])){
                        connection(in, out);
                    }
                    else{
                        out.println("Invalid Password");
                        break;
                    }
                }
                else{
                    out.println("Invalid Username");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            out.close();
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * gets called when username and password were entered right... PUT,  DIR and GET happen in here
     */
    private void connection(BufferedReader in, PrintWriter out) throws IOException {
        out.println("connection successful");

        while (true) {
            out.println("Type: 'PUT filename.extension', 'DIR' or 'GET filename.extension'");
            String request = in.readLine();
            String[] requestArr = request.split(" ");

            if (requestArr[0].equalsIgnoreCase("PUT") && requestArr[1] != null) {

                File file = new File(System.getProperty("user.dir") + "/" + requestArr[1]);

                try (
                        FileInputStream reader = new FileInputStream(file);
                        BufferedInputStream bufferedReader = new BufferedInputStream(reader);

                        FileOutputStream writer = new FileOutputStream(new File(sharedirectory.getPath() + "\\" + file.getName()));
                        BufferedOutputStream bufferedWriter = new BufferedOutputStream(writer);) {
                    int ch = 0;
                    while ((ch = bufferedReader.read()) != -1) {
                        bufferedWriter.write(ch);
                    }
                    out.println("Done");

                    //alle clients benachrichtigen
                    FileServer.sendMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else if (request.equalsIgnoreCase("DIR")) {
                //list files
                File[] directorylisting = sharedirectory.listFiles();
                if (directorylisting.length != 0) {
                    for (File file : directorylisting) {
                        out.println(file.getName());
                    }
                } else {
                    out.println("Empty");
                }
            } else if (requestArr[0].equalsIgnoreCase("GET") && requestArr[1] != null) {

                File file = new File(sharedirectory.getPath() + "\\" + requestArr[1]);

                try (
                        FileInputStream reader = new FileInputStream(file);
                        BufferedInputStream bufferedReader = new BufferedInputStream(reader);

                        FileOutputStream writer = new FileOutputStream(System.getProperty("user.dir") + "/" + file.getName());
                        BufferedOutputStream bufferedWriter = new BufferedOutputStream(writer);) {
                    int ch = 0;
                    while ((ch = bufferedReader.read()) != -1) {
                        bufferedWriter.write(ch);
                    }
                    out.println("Done");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (request.equalsIgnoreCase("QUIT")) {
                out.close();
                try{
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
