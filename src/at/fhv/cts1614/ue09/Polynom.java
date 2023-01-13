//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue09;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Polynom {

    private List<Line2D> lines;
    private LinkedList<Point2D.Double> points;
    private final Color color;

    public Polynom(Color color){
        this.color = color;
        lines = new LinkedList<>();
        points = new LinkedList<>();
    }

    public void setLines(Line2D line) {
        this.lines.add(line);
    }

    public LinkedList<Point2D.Double> getPoints(){
        return points;
    }

    public void setPoints(Point2D.Double point){
        this.points.add(point);
    }

    public Color getColor(){
        return color;
    }
}
