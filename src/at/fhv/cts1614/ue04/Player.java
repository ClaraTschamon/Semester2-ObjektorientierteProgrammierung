//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue04;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean _exit = false;
    private String _name;
    private Room _position;
    private int _energy;
    LinkedList<Item> _inventar = new LinkedList<>();

    public Player (String name, Room position, Playground playground){
        _name = name;
        _position = position;
        _energy = 60;
        playground.get_players().add(this);
    }

    public String get_name(){
        return _name;
    }

    public Room get_position(){
        return _position;
    }

    public void set_position(Room position) {
        _position = position;
    }

    public LinkedList<Item> get_inventar(){
        return _inventar;
    }

    public int get_energy(){
        return _energy;
    }

    public void set_energyUp(int energy){
        _energy = _energy + energy;
    }

    public void set_energyDown(){
        _energy = _energy - 10;
        if(_energy <= 20){
            System.out.printf("Warning: you only have " + _energy + " % left!");
        }
    }

    public void play(Playground playground){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello " + _name + " do you want to enter the game? If yes, type: 'yes'. " +
                "If no, type: 'no'\n");
        playground.reloadOrCreate(playground);
        String string = scanner.next();
        while (! (string.equals("yes") || string.equals("no"))){
            System.out.println("Please enter either: 'yes' or: 'no'...");
            string = scanner.next();
        }
        if(string.equals("yes")){
            _position.enter(this, null);
        }
        else if(string.equals("no")){
            playground.exit(this);
            return;
        }

        while(_exit != true){
            whatNow(playground);
        }
    }

    private void whatNow(Playground playground){
        System.out.println("\nWhat do you want to do now?");
        System.out.println("If you want to see your surrounding, type 'S'");
        System.out.println("If you want to see a list of Items, type 'I'.");
        System.out.println("If you want to look in your Inventar type 'X'.");
        System.out.println("If you want to move, type 'M'.");
        System.out.println("If you want to see your energy Level, type 'E'.");
        System.out.println("If you want to see the other players in your room, type 'P'.");
        System.out.println("If you want to exit, type 'Q'.");

        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        while(!(string.equals("S") || string.equals("I") || string.equals("M") || string.equals("P") || string.equals("E") || string.equals("X") || string.equals("Q"))){
            System.out.println("Please enter either 'S', 'I', 'M', 'P', 'E' or 'Q'");
            string = scanner.next();
        }
        if(string.equals("S")){
            _position.surrounding(this);
        }
        else if(string.equals("I")){
            _position.makeItemList(playground);
            _position.printItems();
            if(_position.get_items().size() > 0){
                System.out.println("Do you want to pick up or eat an Item?");
                System.out.println("If yes, type: 'yes'. If no, type: 'no'");
                string = scanner.next();
                if(string.equals("yes")){
                    _position.pickUpItem(playground, this);
                }
            }
            else{
                System.out.println("No Items here...");
            }
        }
        else if(string.equals("M")){
            System.out.println("\nIn which direction do you want to move?");
            System.out.println("Type 'N' for North, 'E' for East, 'S' for South or 'W' for West.");
            string = scanner.next();
            while (! (string.equals("N") || string.equals("E") || string.equals("S") || string.equals("W"))){
                System.out.println("please enter either: 'N', 'E', 'S' or 'W' ...");
                string = scanner.next();
            }
            move(string);
        }
        else if(string.equals("P")){
            playground.printOtherPlayers(_position);
        }
        else if(string.equals("X")){
            printInventar();
        }
        else if(string.equals("E")){
            System.out.println("Your energy: " + _energy + " %");
        }
        else{
            playground.exit(this);
            _exit = true;
        }
    }

    private void move(String direction){

        if(direction.equals("N")){
            Direction d = Direction.north;
            if(_position.get_north() != null){
                _position.get_north().enter(this, d);
            }
            else{
                System.out.println("Here is nothing. Try another direction.");
            }

        }
        else if(direction.equals("E")){
            Direction d = Direction.east;
            if(_position.get_east() != null){
                _position.get_east().enter(this, d);
            }
            else{
                System.out.println("Here is nothing. Try another direction.");
            }
        }
        else if(direction.equals("S")){
            Direction d = Direction.south;
            if(_position.get_south() != null){
                _position.get_south().enter(this, d);
            }
            else{
                System.out.println("Here is nothing. Try another direction.");
            }
        }
        else if(direction.equals("W")){
            Direction d = Direction.west;
            if(_position.get_west() != null){
                _position.get_west().enter(this, d);
            }
            else{
                System.out.println("Here is nothing. Try another direction.");
            }
        }
    }

    private void printInventar(){
        System.out.println("Your Inventar:");
        for(int i = 0; i < _inventar.size(); i++){
            System.out.println(i + " :" + _inventar.get(i).get_name());
        }
    }

}
