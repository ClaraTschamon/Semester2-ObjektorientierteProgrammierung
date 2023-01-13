//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue10.a2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 7070;
    private static ServerSocket serverSocket;
    private static Socket client;
    private static Frame frame;
    private static Map<Integer, String> orders = new HashMap<>();
    private static Map<Integer, Label> labels = new HashMap<>();
    private static Panel eastPanel;

    public static void main(String[] args) throws IOException {
        orders.put(1, " Pommes");
        orders.put(2, " Cheesburger");
        orders.put(3, " VeggieBurger");
        orders.put(4, " Pommes");
        orders.put(5, " Pommes");
        orders.put(6, " Cheeseburger");
        makeGUI();
        frame.setVisible(true);
        serverSocket = new ServerSocket(PORT);
        System.out.println("[Server] waiting for Client connection");
        client = serverSocket.accept();
        System.out.println("[Server] connected to Client");
    }

    private static void makeGUI(){
        frame = new Frame();
        frame.setName("Bestellungen");
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });

        Panel northPanel = new Panel(new BorderLayout());
        frame.add(northPanel, BorderLayout.NORTH);

        Panel southPanel = new Panel(new BorderLayout());
        southPanel.setVisible(true);
        frame.add(southPanel, BorderLayout.SOUTH);

        Label labelInfo = new Label("Type in the number:");
        northPanel.add(labelInfo, BorderLayout.NORTH);

        TextField textField1 = new TextField();
        northPanel.add(textField1, BorderLayout.CENTER);

        Button send = new Button("Send");
        northPanel.add(send, BorderLayout.EAST);
        northPanel.setVisible(true);

        Label label1 = new Label();
        northPanel.add(label1, BorderLayout.SOUTH);
        label1.setBackground(Color.red);
        label1.setVisible(false);

        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    label1.setVisible(false);
                    int text = Integer.parseInt(textField1.getText());
                    if(orders.containsKey(text)){
                        StringBuilder message = new StringBuilder();
                        message.append(text);
                        message.append(orders.get(text));
                        PrintWriter out =  new PrintWriter(client.getOutputStream(), true);
                        out.println(message);
                    }
                    else{
                        label1.setText("This was not the number of an order!");
                        label1.setVisible(true);
                    }
                    textField1.setText("");

                } catch (NumberFormatException | IOException exc) {
                    exc.printStackTrace();
                    label1.setText("Please enter a number!");
                    label1.setVisible(true);
                    frame.pack();
                }
            }
        });

        eastPanel = new Panel(new GridLayout(4,2));
        for(Map.Entry<Integer, String> entry : orders.entrySet()){
            Label label = new Label();
            StringBuilder string = new StringBuilder();
            string.append(entry.getKey());
            string.append(entry.getValue());
            label.setText(string.toString());
            label.setBackground(new Color(205, 250, 236));
            labels.put(entry.getKey(), label);
            eastPanel.add(label);
        }
        eastPanel.setVisible(true);
        frame.add(eastPanel, BorderLayout.CENTER);

        Label labelDelete = new Label("Enter the number you want to delete:");
        southPanel.add(labelDelete, BorderLayout.NORTH);

        TextField textFDelete = new TextField();
        southPanel.add(textFDelete, BorderLayout.CENTER);

        Label label2 = new Label("Please enter a number!");
        southPanel.add(label2, BorderLayout.SOUTH);
        label2.setBackground(Color.red);
        label2.setVisible(false);

        Button delete = new Button("Delete");
        southPanel.add(delete, BorderLayout.EAST);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    label2.setVisible(false);
                    StringBuilder textB = new StringBuilder();
                    textB.append("Delete ");
                    int text = Integer.parseInt(textFDelete.getText());
                    textB.append(text);
                    textB.append(orders.get(text));
                    String message = textB.toString();
                    PrintWriter out =  new PrintWriter(client.getOutputStream(), true);
                    out.println(message);
                    orders.remove(text);
                    textFDelete.setText("");
                    Label label = labels.get(text);
                    eastPanel.remove(label);

                } catch (NumberFormatException | IOException exc) {
                    exc.printStackTrace();
                    label2.setVisible(true);
                    frame.pack();
                }
            }
        });

        frame.pack();
    }
}
