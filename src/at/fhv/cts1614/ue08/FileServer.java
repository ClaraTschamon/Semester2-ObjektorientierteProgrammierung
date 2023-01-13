//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue08;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileServer {
    private static File sharedirectory;
    private static final int PORT = 9090;
    private static final Properties properties = new Properties();

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    /**
     * stores properties in a property file
     */
    public static void start() throws IOException {
        FileOutputStream os = new FileOutputStream("src/at/fhv/cts1614/ue08NEW/file.properties");
        properties.setProperty("directoryPath", "src/at/fhv/cts1614/ue08NEW/shareDirectory");
        properties.setProperty("anna", "anna1234");
        properties.setProperty("clara", "cts1614"); //Benutzername, Passwort
        properties.setProperty("lukas", "Lukas903");

        properties.store(os, null);

        sharedirectory = new File(properties.getProperty("directoryPath"));
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        start();

        while(true){
            System.out.println("[FILESERVER] Waiting for client connection...");
            try{
                Socket client = server.accept();

                System.out.println("[FILESERVER] Connected to client!");
                ClientHandler clientThread = new ClientHandler(client, properties, sharedirectory);
                clients.add(clientThread);

                pool.execute(clientThread);

            }catch(Exception e){ //Connection Impossible Exception
                e.getMessage();
                e.printStackTrace();
            }
        }
    }

    /**
     * sends a message to all running Clients when a File is added to to shareDirectory
     */
    public static void sendMessage(){
        for (ClientHandler client : clients){
            client.getOut().println("[INFO FROM FILESERVER]: New File added");
        }
    }
}



