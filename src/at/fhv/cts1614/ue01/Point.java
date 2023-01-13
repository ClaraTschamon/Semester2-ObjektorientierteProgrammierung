//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue01;

import java.util.Objects;

public class Point {
    private int _x;
    private int _y;

    //Getter braucht man, damit man diese Variablen nicht public machen muss.
    public int GetX(){ //in Java Methoden klein schreiben!
        return _x;
    }
    public void SetX(int value){
        _x = value;
    }
    public int GetY(){
        return _y;
    }
    public void SetY(int value){
        _y = value;
    }


    public Point (int x, int y){
        _x = x;
        _y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; //damit nicht die Referenzen vergliechen werden
        Point point = (Point) o;
        return _x == point._x && _y == point._y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y);
    } //Automatisch erstellt

    public boolean isAchsenparallel(){
       return false;
    }

}
