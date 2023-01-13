package at.fhv.cts1614.importantExercises.TimerMitUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Timer{
    private boolean active;
    private TextField minutes;
    private TextField seconds;

    public Timer(){
        active = false;
        minutes = new TextField("00");
        seconds = new TextField("00");
        makeGUI();
    }

    private void makeGUI(){
        Frame frame = new Frame();
        frame.setName("Stoppuhr");
        frame.setVisible(true);
        frame.setSize(400, 400);

        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Label label = new Label(":");
        Panel mainPanel = new Panel(new FlowLayout());
        Panel southPanel = new Panel(new FlowLayout());

        mainPanel.add(minutes);
        mainPanel.add(label);
        mainPanel.add(seconds);
        frame.add(mainPanel, BorderLayout.CENTER);

        southPanel.add(start);
        southPanel.add(stop);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });


        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active = true;
                Thread threadMinutes = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        minutes.setText("00");
                        seconds.setText("00");
                        int i = 0;
                        while(active){
                            minutes.setText(Integer.toString(i));
                            try {
                                Thread.sleep(60000);
                                //wait(60000); --> zeit war ungenau
                                i++;
                            }catch(InterruptedException e){
                                e.printStackTrace();
                                minutes.setText("00");
                                seconds.setText("00");
                            }
                        }
                    }
                });
                threadMinutes.start();

                Thread threadSeconds = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        minutes.setText("00");
                        seconds.setText("00");
                        int i = 0;
                        while(active){
                            seconds.setText(Integer.toString(i));
                            try{
                                Thread.sleep(1000);
                                //wait(1000);
                                if(i < 60){
                                    i++;
                                }else{
                                    i=0;
                                }

                            }catch(InterruptedException e){
                                e.printStackTrace();
                                minutes.setText("00");
                                seconds.setText("00");
                            }
                        }
                    }
                });
                threadSeconds.start();
            }
        });


		stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                active = false;
            }
        });
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
    }
}
