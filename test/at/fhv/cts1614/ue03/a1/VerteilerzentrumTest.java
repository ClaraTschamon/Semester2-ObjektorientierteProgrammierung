package at.fhv.cts1614.ue03.a1;

import at.fhv.cts1614.ue03.a1.Point;
import at.fhv.cts1614.ue03.a1.Product;
import at.fhv.cts1614.ue03.a1.Trolley;
import at.fhv.cts1614.ue03.a1.Verteilerzentrum;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class VerteilerzentrumTest {

    @Test
    void Test1() {
        LinkedList<Product> general_products = new LinkedList<Product>();
        LinkedList <Trolley> trolleys = new LinkedList <Trolley>();
        Verteilerzentrum verteilerzentrum = new Verteilerzentrum(10, 10, trolleys);

        Trolley trolley1 = new Trolley(general_products, 1, 3);
        trolleys.add(trolley1);
        Trolley trolley2 = new Trolley(general_products, 4, 8);
        trolleys.add(trolley2);

        general_products.add(new Product("lavendel", 1, new Point(1,1), new Point(6,7)));
        general_products.add(new Product("rosen", 4, new Point(4,5), new Point(6,7)));
        general_products.add(new Product("pommes",1, new Point(6,5), new Point(8,2)));
        general_products.add(new Product("ketchup", 2, new Point(6,5), new Point(8,2)));

        verteilerzentrum.cleanUp(trolley1);

        assertEquals(1, general_products.size());
    }

    @Test
    void Test2(){
        LinkedList<Product> general_products = new LinkedList<Product>();
        LinkedList <Trolley> trolleys = new LinkedList <Trolley>();
        Verteilerzentrum verteilerzentrum = new Verteilerzentrum(10, 10, trolleys);

        Trolley trolley1 = new Trolley(general_products, 1, 3);
        trolleys.add(trolley1);
        Trolley trolley2 = new Trolley(general_products, 4, 8);
        trolleys.add(trolley2);

        general_products.add(new Product("lavendel", 1, new Point(1,1), new Point(6,7)));
        general_products.add(new Product("rosen", 4, new Point(4,5), new Point(6,7)));
        general_products.add(new Product("pommes",1, new Point(6,5), new Point(8,2)));
        general_products.add(new Product("ketchup", 2, new Point(6,5), new Point(8,2)));

        verteilerzentrum.cleanUp(trolley1);
        verteilerzentrum.cleanUp(trolley2);

        assertEquals(0, general_products.size());
    }

    @Test
    void Test3(){
        LinkedList<Product> general_products = new LinkedList<Product>();
        LinkedList <Trolley> trolleys = new LinkedList <Trolley>();
        Verteilerzentrum verteilerzentrum = new Verteilerzentrum(10, 10, trolleys);

        Trolley trolley1 = new Trolley(general_products, 1, 3);
        trolleys.add(trolley1);
        Trolley trolley2 = new Trolley(general_products, 4, 8);
        trolleys.add(trolley2);

        general_products.add(new Product("lavendel", 10, new Point(1,1), new Point(6,7)));
        general_products.add(new Product("rosen", 4, new Point(4,5), new Point(6,7)));
        general_products.add(new Product("pommes",1, new Point(6,5), new Point(8,2)));
        general_products.add(new Product("ketchup", 2, new Point(6,5), new Point(8,2)));

        verteilerzentrum.cleanUp(trolley1);
        verteilerzentrum.cleanUp(trolley2);

        assertEquals(1, general_products.size());
    }
}