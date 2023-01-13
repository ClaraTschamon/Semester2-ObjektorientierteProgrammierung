//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//TODO: zuhause austesen mit zwei computern
//TODO: ConnectionImpossibleException anwenden
//TODO: was passiert wenn Datei nicht existiert?


public class Client {
    //damit Client auf nachricht warten kann und sie sofort printen
    //kann, braucht es für den Client eine ServerConnection
    private static final String FILESERVER_IP = "localhost";
    private static final int FILESERVER_PORT = 9090;
    private static BufferedReader keyboard;
    private static PrintWriter out;
    private static Socket fileServerSocket;

    public static void main(String[] args) throws IOException {
        fileServerSocket = new Socket(FILESERVER_IP, FILESERVER_PORT);
        FileServerConnection fileServerConnection = new FileServerConnection(fileServerSocket);

        keyboard = new BufferedReader(new InputStreamReader(System.in)); //read from the keyboard
        out = new PrintWriter(fileServerSocket.getOutputStream(), true); //writes to the Server

        new Thread(fileServerConnection).start();

        getServerResponse();
    }

    /**
     * sends commands to the Server
     */
    private static void getServerResponse() throws IOException {
        while(true){
            String command = keyboard.readLine();
            out.println(command); //send the command to the server

            if(command.equalsIgnoreCase("quit")){
                System.out.println("Closing FileServerConnection...");
                break;
            }
            //waiting for the server reply is done in the ServerConnection class.
        }
    }

}
