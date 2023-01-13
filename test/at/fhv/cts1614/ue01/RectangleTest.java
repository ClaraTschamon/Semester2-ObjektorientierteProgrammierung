package at.fhv.cts1614.ue01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void getUmkreis() {
        Point pointA = new Point(4, 1);
        Point pointB = new Point(2,3);
        Rectangle rectangleA = new Rectangle(pointA, pointB);
        Circle circleCompare = new Circle(rectangleA.getLength(), rectangleA.getHeight(), pointB);

        assertEquals(circleCompare, rectangleA.getUmkreis());
    }

    @Test
    void moveRectangle() {
        Point PointA = new Point(4, 1);
        Point PointB = new Point(2,3);
        Rectangle Rectangle_a = new Rectangle(PointA, PointB);
        Rectangle Rectangle_compare = new Rectangle(new Point(6, 3), new Point(4,5));
        assertEquals(Rectangle_compare, Rectangle_a.moveRectangle(2,2));
    }

    @Test
    void isSqare() {
        Point PointA = new Point(4, 1);
        Point PointB = new Point(2,3);
        Rectangle Rectangle_a = new Rectangle(PointA, PointB);
        assertEquals(true, Rectangle_a.isSqare());
    }

    @Test
    void zoom() {
        Point PointA = new Point(4, 1);
        Point PointB = new Point(2,3);
        Rectangle Rectangle_a = new Rectangle(PointA, PointB);

        Rectangle Rectangle_compare = new Rectangle(new Point(4, 1), new Point(0,5));
        assertEquals(Rectangle_compare, Rectangle_a.zoom(2));
    }

    @Test
    void divide() {
        Point PointA = new Point(4, 1);
        Point PointB = new Point(2,3);
        Rectangle Rectangle_a = new Rectangle(PointA, PointB);

        Point expected_point = new Point(3, 2);
        assertEquals(expected_point, Rectangle_a.divide());
    }
}