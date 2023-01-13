//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue03.a1;

public class Product {
    private String _name;
    private int _size;
    private Point _position;
    private Point _aim;

    public Product(String name, int size, Point position, Point aim) {
        _name = name;
        _size = size;
        _position = position;
        _aim = aim;
    }

    public String get_name(){
        return _name;
    }

    public int get_size(){
        return _size;
    }

    public Point get_aim(){
        return _aim;
    }

    public Point get_position(){
        return _position;
    }

}
