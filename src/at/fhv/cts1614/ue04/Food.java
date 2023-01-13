package at.fhv.cts1614.ue04;

public class Food extends Item {
    private int _energy;

    public Food(String name, Room position, int energy, Playground playground) {
        super(name, position, playground);
        _energy = energy;
    }

    public int get_energy() {
        return _energy;
    }
}
