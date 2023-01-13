//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue10.a1;

public class Vector {

    private Point a;
    private Point b;

    private float angle;
    private float length;

    private double vectorX;
    private double vectorY;

    public Vector(Point p){
        vectorX = p.getX();
        vectorY = p.getY();
    }

    public Vector(Point a, Point b){
        this.a = a;
        this.b = b;
        versionOne();
    }

    public Vector(float angle, float length){
        this.angle = angle; //Winkel zur x-Achse
        this.length = length;

        versionTwo();
    }

    public void versionOne(){
        int x = Math.abs(a.getX() - b.getX());
        int y = Math.abs(a.getY() - b.getY());
        this.vectorX = x;
        this.vectorY = y;
        System.out.println("Vector = " + x + ", " + y);

    }

    public void versionTwo(){
        double x = length * Math.cos(Math.toRadians(angle));
        double y = length * Math.sin(Math.toRadians(angle));
        this.vectorX = x;
        this.vectorY = y;
        System.out.println("Vector = " + x + ", " + y);
    }

    public static void scalarMult(Vector vector1, Vector vector2){
        double result = vector1.vectorX * vector2.vectorX + vector1.vectorY + vector2.vectorY;
        System.out.println("Result of scalarMult = " + result);
    }

    public static void length(Vector vector){
        double length = Math.sqrt((vector.vectorX * vector.vectorX) + (vector.vectorY * vector.vectorY));
        System.out.println("Length = " + length);
    }

    public static void normVector(Vector vector){
        Point p = new Point((int)vector.vectorY * (-1), (int) vector.vectorX);
        Vector v = new Vector(p);
        System.out.println("NormVector: " + v.vectorX + ", " + v.vectorY);
    }

    public static void makeGeradengleichung(Point p1, Point p2){
        Point p = new Point((p1.getX() - p2.getX()), p1.getY() - p2.getY());
        Vector rv = new Vector(p);
        System.out.println("Geradengleichung = (" + p1.getX() + ", " + p1.getY() + ") + k * (" + rv.vectorX + ", " + rv.vectorY + ")");
    }

    public static void main(String[] args) {
        Point a = new Point(3,3);
        Point b = new Point(8, 5);
        Vector vector = new Vector(a, b);

        Vector vector1 = new Vector(45f, 1.4f);
        scalarMult(vector, vector1);

        length(vector);
        Vector v = new Vector(new Point(2, 1));
        normVector(v); //expected: -1, 2

        Point p1 = new Point(2, 2);
        Point p2 = new Point(4, 6);

        makeGeradengleichung(p1, p2);

    }
}
