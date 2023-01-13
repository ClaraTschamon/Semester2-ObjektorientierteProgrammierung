//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue04;

public class Wall extends Field {

    public Wall(Playground playground) {
        super("Wall", playground);
    }

    public void enter(Player player, Direction direction){
        System.out.println("Ouch, you ran into a wall. Try another direction!");
    }
}
