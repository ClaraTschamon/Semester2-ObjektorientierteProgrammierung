//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

//responsible for handeling connections to the server
public class FileServerConnection implements Runnable{
    private Socket fileServer;
    private BufferedReader in;

    public FileServerConnection(Socket s) throws IOException {
        this.fileServer = s;
        in = new BufferedReader(new InputStreamReader(fileServer.getInputStream()));
    }

    /**
     * listens to the server and prints the server response to the client console
     */
    @Override
    public void run() {
        //loop that waits for the server to say something and then displays it to the user
        try{
            while(true){

                String serverResponse = in.readLine(); //get the response from the Server

                if(serverResponse == null){
                    break;
                }

                System.out.println(serverResponse); //display the response from the Server
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally{
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
