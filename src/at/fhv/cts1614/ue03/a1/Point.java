//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue03.a1;

public class Point {
    private int _x;
    private int _y;

    public Point(int x, int y){
        _x = x;
        _y = y;
    }

    public int get_x(){
        return _x;
    }

    public void set_x(int x){
        _x = x;
    }

    public int get_y(){
        return _y;
    }

    public void set_y(int y){
        _y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return _x == point._x && _y == point._y;
    }
}
