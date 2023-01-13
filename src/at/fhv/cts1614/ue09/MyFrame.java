//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue09;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.Integer.parseInt;

public class MyFrame extends Frame{
    private Button plot_button;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Panel mainPanel;

    public MyFrame(){
        super("GraphPlotter");
        mainPanel = new Panel();
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        plot_button = new Button("Plot");
        setupWindow();
    }

    public void setupWindow(){

        setSize(400, 300);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                setVisible(false);
                dispose();
                /*dispose gibt alle ressourcen vom Frame frei und wenn die freigegeben
                  sind wird auch der AWT Thread frei gegeben*/
            }
        });

        mainPanel.setLayout(gbl);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        TextField a = new TextField();
        gridBagHandler(0, 0, a);

        Label l1 = new Label("x^2  +  ");
        gridBagHandler(1, 0, l1);

        TextField b = new TextField();
        gridBagHandler(2, 0, b);

        Label l2 = new Label("x  +  ");
        gridBagHandler(3, 0, l2);

        TextField c = new TextField();
        gridBagHandler(4, 0, c);

        plot_button.setBackground(new Color(102, 205, 0));
        gridBagHandler(5, 0, plot_button);

        addHelpMenu();

        MyCanvas lineCanvas = new MyCanvas();
        add(lineCanvas, BorderLayout.CENTER);

        Panel southPanel = new Panel();
        southPanel.setLayout(new GridBagLayout());
        List list = new List();
        list.setBounds(100, 100, 75, 75);
        southPanel.add(list);

        Label d = new Label(" ");

        plot_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] numbers = new int[3];
                try{
                    mainPanel.remove(d);
                    d.setVisible(false);
                    pack();
                    if(a.getText().equals("")){
                        a.setText("0");
                    }
                    if(b.getText().equals("")){
                        b.setText("0");
                    }
                    if(c.getText().equals("")){
                        c.setText("0");
                    }
                    numbers[0] = parseInt(a.getText());
                    numbers[1]= parseInt(b.getText());
                    numbers[2] = parseInt(c.getText());

                    StringBuilder polynom = new StringBuilder();
                    polynom.append(numbers[0]).append("x^2 + ").append(numbers[1]).append("x + ").append(numbers[2]);
                    lineCanvas.addPolynom(polynom.toString(), numbers);
                    list.add(polynom.toString());
                    a.setText("");
                    b.setText("");
                    c.setText("");

                }catch (NumberFormatException exc){
                    d.setText("Please enter integer numbers!");
                    d.setBackground(new Color(255, 69, 0));
                    d.setVisible(true);
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 8;
                    gbc.gridheight = 1;
                    gbl.setConstraints(d, gbc);
                    mainPanel.add(d);
                    pack();
                }
            }
        });
        add(mainPanel, BorderLayout.NORTH);

        Button delete_btn = new Button("Delete");
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = list.getSelectedItem();
                lineCanvas.getCoeffs().remove(s);
                lineCanvas.getPolynoms().remove(s);
                list.remove(s);
                lineCanvas.repaint();
            }
        });
        b.setBounds(200, 150, 80, 30);
        southPanel.add(delete_btn);

        add(southPanel, BorderLayout.SOUTH);

        pack();
    }

    private void gridBagHandler(int gridx, int gridy, Component c){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(c, gbc);
        mainPanel.add(c);
    }

    public void addHelpMenu(){
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem help = new MenuItem("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame popUp = new Frame();
                TextArea area = new TextArea();
                area.setText("This is a GraphPlotter. Type in the a, b and c values (as integer numbers) of your Polynom of degree 2 and plot it.");
                popUp.addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent e){
                        popUp.setVisible(false);
                        popUp.dispose();
                    }
                });
                popUp.add(area);
                popUp.setVisible(true);
                popUp.pack();
            }
        });
        menu.add(help);
        menuBar.add(menu);
        setMenuBar(menuBar);
    }

    public static void main(String[] args) {
        MyFrame myframe = new MyFrame();
        myframe.setVisible(true);
    }
}
