//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue09;

//https://www.wolframalpha.com/input?i=x**2+%2B+2x+%2B+3

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/*
Skalierung: bei jeder Funktion die dazu kommt wird geschaut ob neu skaliert werden soll.
In GUI auswählen, welche angezeigt werden sollen.
Erkennbar machen, welche welche Funktion ist.
 */

public class MyCanvas extends Canvas {

    private final static int BORDER = 10;
    private final static int STEP = 2;
    private static int RANGE = 20; //Koordinatensystem geht von -10 bis 10
    private Point middlePoint;
    private HashMap<String, int[]> coeffs = new HashMap<>();
    private HashMap<String, Polynom> polynoms = new HashMap<>();
    private XAxis xAxis;
    private YAxis yAxis;

    public MyCanvas(){
        setSize(400, 300);
        setBackground(Color.gray);
        setVisible(true);
        this.middlePoint = new Point(this.getWidth()/2, this.getHeight()/2);
    }

    /**
     * wird aus Klasse myFrame aus aufgerufen
     * @param description ax^2 + bx + c
     */
    public void addPolynom(String description, int[]coeffs){
        this.coeffs.put(description, coeffs);
        makePolynom(description, coeffs, getGraphics());
        repaint();
    }

    public HashMap<String, Polynom> getPolynoms() {
        return polynoms;
    }

    public HashMap<String, int[]> getCoeffs(){
        return coeffs;
    }

    public void paint(Graphics graphics){
        middlePoint.x = getWidth()/2;
        middlePoint.y = getHeight()/2;
        drawCoordinates(graphics);

        for(Polynom polynom : polynoms.values()){
            drawPolynom(polynom, getGraphics());
        }
    }

    public void drawCoordinates(Graphics g){
        xAxis = new XAxis((int)middlePoint.getY(),5);
        xAxis.paint(getGraphics());
        yAxis = new YAxis((int)middlePoint.getX(), 5);
        yAxis.paint(getGraphics());
    }

    /**
     * von addPolynom aufgerufen.
     * die Punkte des Polynoms werden berechnet. aber noch nicht mit viewTransform()
     * in Pixel umgewandelt
     */
    private void makePolynom(String description, int [] coeffs, Graphics g){
        double num = (RANGE / 2) * -1;

        Random r = new Random();
        Color color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        Polynom polynom = new Polynom(color);

        while(num <= RANGE / 2){
            double y = (coeffs[0] * (num*num) + coeffs[1] * num + coeffs[2]) * (-1);
            Point2D.Double point  = new Point2D.Double(num, y);
            polynom.setPoints(point);
            num+=0.1;
        }
        drawPolynom(polynom, getGraphics());
        polynoms.put(description, polynom);
    }

    /**
     * von makePolynom() aufgerufen.
     * Punkte werden in Pixel umgewandelt und Linien werden von Punkt zu Punkt gezeichnet.
     */
    private void drawPolynom(Polynom polynom, Graphics g){

        LinkedList<Point2D.Double> myPoints = polynom.getPoints();
        int i = 0;
        while(i+1 < polynom.getPoints().size()){
            Point2D.Double p1 = myPoints.get(i);
            double x1T = viewTransform(p1.getX(), getWidth(), 0);
            double y1T = viewTransform(p1.getY(), getHeight(), 0);
            Point p1T = new Point();
            p1T.setLocation(x1T, y1T);

            Point2D.Double p2 = myPoints.get(i+1);
            double x2T = viewTransform(p2.getX(), getWidth(), 0);
            double y2T = viewTransform(p2.getY(), getHeight(), 0);
            Point p2T = new Point();
            p2T.setLocation(x2T, y2T);

            i++;
            System.out.println("Draw line from: " + p1T.getX() + ", "+  p1T.getY() + " to: " + p2T.getX() + ", " + p2T.getY());
            Line2D.Double line = new Line2D.Double(p1T, p2T);
            polynom.setLines(line);
            g.setColor(polynom.getColor());
            ((Graphics2D) g).draw(line);
        }
    }

    /**
     * Wandelt Wert von einem Punkt in Pixel auf dem Bildschirm um
     */
    private static double viewTransform(double x, double width, int border){
        width -= STEP * border;
        return ((x * (width / RANGE)) + width / 2 + border);
    }

    public class XAxis {

        private int ypos;
        private int scaleSize;
        private int xMin;
        private int xMax;

        public XAxis(int ypos, int scaleSize){
            this.ypos = ypos;
            this.scaleSize = scaleSize;
            this.xMin = (RANGE / 2) * (-1);
            this.xMax = RANGE / 2;
        }

        public void paint(Graphics g){
            g.setColor(Color.black);
            for(int x = xMin; x <= xMax; x+=STEP){
                double px = viewTransform(x, getWidth(), BORDER);
                g.drawLine((int)px, ypos - scaleSize / 2, (int)px, ypos + scaleSize / 2); //Senkrechte Striche
                g.drawString(String.valueOf((int)(x)), (int) (px-4), ypos+12);

            }
            g.drawLine(middlePoint.x - getWidth() /2 + BORDER, middlePoint.y, middlePoint.x + getWidth()/2 - BORDER, middlePoint.y);
            g.drawString("x", (int) (middlePoint.getX() + getWidth()/2) - BORDER - 3, ypos-10);
        }
    }

    public class YAxis {

        private final static int BORDER = 5;
        private int xpos;
        private int scaleSize;
        private int yMin;
        private int yMax;

        public YAxis(int xpos, int scaleSize){
            this.xpos = xpos;
            this.scaleSize = scaleSize;
            this.yMin = -10;
            this.yMax = 10;
        }

        public void setyMin(int yMin) {
            this.yMin = yMin;
        }

        public void setyMax(int yMax) {
            this.yMax = yMax;
        }

        public void paint(Graphics g){
            g.setColor(Color.black);
            for(int y = yMin; y <= yMax; y+=STEP){
                double px = viewTransform(y, getHeight(), BORDER);
                g.drawLine(xpos - scaleSize / 2, (int)px, xpos + scaleSize / 2, (int)px); //Senkrechte Striche
                g.drawString(String.valueOf(y*(-1)), xpos+4, (int) (px+3));
            }
            g.drawLine(middlePoint.x, middlePoint.y - getHeight()/2 + BORDER, middlePoint.x, middlePoint.y + getHeight()/2 - BORDER);
            g.drawString("y",xpos-10, yMax-BORDER + 3);
        }
    }
}
