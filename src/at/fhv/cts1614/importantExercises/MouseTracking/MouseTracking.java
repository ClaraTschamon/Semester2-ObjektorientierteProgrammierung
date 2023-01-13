package at.fhv.cts1614.importantExercises.MouseTracking;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MouseTracking extends Frame{

    public MouseTracking(){
        makeGUI();
        setVisible(true);
    }

    private void makeGUI(){
        setName("MouseTracking");
        setSize(550, 400);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });

        Label label1 = new Label("x= ");
        TextField tf1 = new TextField(3);
        Label label2 = new Label("y= ");
        TextField tf2 = new TextField(3);
        Label label3 = new Label("Action= ");
        TextField tf3 = new TextField(15);

        Panel northPanel = new Panel(new FlowLayout());
        northPanel.add(label1);
        northPanel.add(tf1);
        northPanel.add(label2);
        northPanel.add(tf2);
        northPanel.add(label3);
        northPanel.add(tf3);
        add(northPanel, BorderLayout.NORTH);

        Panel mainPanel = new Panel();
        mainPanel.setBackground(Color.cyan);
        mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                tf3.setText("Mouse Dragged");
                double x = getMousePosition().getX();
                tf1.setText(String.valueOf(x));
                double y = getMousePosition().getY();
                tf2.setText(String.valueOf(y));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                tf3.setText("Mouse Moved");
                double x = getMousePosition().getX();
                tf1.setText(String.valueOf(x));
                double y = getMousePosition().getY();
                tf2.setText(String.valueOf(y));
            }
        });


        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MouseTracking mT = new MouseTracking();
    }
}
