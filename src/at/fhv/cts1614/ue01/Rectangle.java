package at.fhv.cts1614.ue01;
import java.util.Objects;

import static java.lang.Math.*;

public class Rectangle {

    public Point _pointA;
    public Point _pointB;

    public Rectangle(Point pointA, Point pointB){ //Konstruktor
        _pointA = pointA;
        _pointB = pointB;
    }
    
    public int getHeight(){
        int height = Math.abs((_pointB.GetY() - _pointA.GetY()));
        return height;
    }
    
    public int getLength(){
        int length = Math.abs(_pointA.GetX()) - Math.abs(_pointB.GetX());
        return length;
    }

    public Circle getUmkreis(){
        int x = _pointB.GetX();
        int y = _pointB.GetY();
        x = (int) abs(x + (getLength()/2));
        y = (int) abs(y - (getHeight()/2));
        Point _middlePoint = new Point (x,  y);
        int _rad = (int)((sqrt(getLength()*getLength() + getHeight()*getHeight()))/2);
        return new Circle(_middlePoint,_rad);
    }

    public Rectangle moveRectangle(int moveX, int moveY){
        _pointA.SetX(_pointA.GetX() + moveX); //Punkt A: x Koordinate verschoben
        _pointA.SetY(_pointA.GetY() + moveY);
        _pointB.SetX(_pointB.GetX() + moveX);
        _pointB.SetY(_pointB.GetY() + moveY);
        return this;
    }

    public Boolean isSqare(){
        return getLength() == getHeight();
    }

    public Rectangle zoom(int factor) { //
        int new_length = (-getLength()) * factor;
        int new_height = this.getHeight() * factor;
        int x = _pointA.GetX() + new_length;
        int y = _pointA.GetY() + new_height;
        Point newB = new Point(x, y);
        return new Rectangle(_pointA, newB);
    }

    public Point divide (){
        int x = _pointA.GetX();
        int y = _pointA.GetY();
        Point new_point = new Point((int) abs(x-getLength()/2), (int) abs(y + getHeight()/2));
        Rectangle rectangle_new = new Rectangle(_pointA, new_point);
        return new_point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return getLength() == rectangle.getLength() && getHeight() == rectangle.getHeight() && Objects.equals(_pointA, rectangle._pointA) && Objects.equals(_pointB, rectangle._pointB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_pointA, _pointB, getLength(), getHeight());
    }
}

