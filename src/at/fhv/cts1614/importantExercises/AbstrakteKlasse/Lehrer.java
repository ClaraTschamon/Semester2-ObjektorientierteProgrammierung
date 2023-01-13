package at.fhv.cts1614.importantExercises.AbstrakteKlasse;

public class Lehrer extends Persion implements Readable{

    int age = x;

    public Lehrer(){

    }

    public static void main(String[] args) {
        Lehrer lehrer = new Lehrer();
        lehrer.print();
        lehrer.walk();
    }

    @Override
    public void walk() {

    }
}
