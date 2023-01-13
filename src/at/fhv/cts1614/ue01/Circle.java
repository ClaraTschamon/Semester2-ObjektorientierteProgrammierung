//Clara Tschamon, Gruppe 1

package at.fhv.cts1614.ue01;

import java.util.Objects;

public class Circle {

    public Point _middlePoint; //instanzvariablen sollen privat sein

    public int getRad() {
        return _rad;
    }

    private int _rad;


    //man darf zwei Konstruktoren haben
    //ein Konstruktor muss immer den Namen der Klasse haben
    public Circle(Point _middlePoint, int _rad) {
        this._middlePoint = _middlePoint; //das "this" verhindert hier nur den Namenskonflikt
        //underscore: gehört zu Objekt... andere variablen ohne Unterscore!
        this._rad = _rad;
    }

    public Circle (int _length, int _height, Point PointB){ //Rechteck übergeben!
        int x = PointB.GetX();
        int y = PointB.GetY();
        x = (int) Math.abs(x + (_length/2));
        y = (int) Math.abs(y - (_height/2));
        _middlePoint = new Point (x, y);
        _rad = (int) ((Math.sqrt(_length*_length + _height*_height))/2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return _rad == circle._rad && Objects.equals(_middlePoint, circle._middlePoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_middlePoint, _rad);
    }
}
