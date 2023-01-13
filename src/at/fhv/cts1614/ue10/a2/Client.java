//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue10.a2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 7070;
    private static Socket server;
    private static BufferedReader in;
    private static PrintWriter out;
    private static Frame frame;
    private static Label labelCenter;
    private static Panel mainPanel;
    private static HashMap<String, Label> labels = new HashMap<>();

    public static void main(String[] args) throws IOException {
        makeGUI();
        frame.setVisible(true);
        server = new Socket(SERVER_IP, SERVER_PORT);

        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(), true);

        while(true){
            String message = in.readLine();
            String[] text = message.split(" ");
            if(text[0].equals("Delete")){
                StringBuilder mess = new StringBuilder();
                mess.append(text[1]);
                mess.append(" ");
                mess.append(text[2]);
                delete(mess.toString()); //1 Pommes
            }
            else {
                if(!labels.containsKey(message)){
                    labelCenter = new Label(message);
                    labelCenter.setBackground(new Color(205, 250, 236));
                    labelCenter.setSize(50, 50);
                    labels.put(message, labelCenter);
                    System.out.println(message);
                    illustrate();
                }
            }
        }
    }

    private static void makeGUI() throws IOException {
        frame = new Frame();
        //frame.setVisible(true);
        frame.setSize(400, 400);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing (WindowEvent e) {
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });

        Label label = new Label("Orders ready: ");
        frame.add(label, BorderLayout.NORTH);
        mainPanel = new Panel(new GridLayout(8, 4));
        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private static void delete(String message){
        try{
            Label label = labels.get(message);
            mainPanel.remove(label);
            labels.remove(message);
            illustrate();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private static void illustrate(){
        for (Map.Entry<String, Label> label : labels.entrySet()) {
            mainPanel.add(label.getValue());
        }
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
    }

}
