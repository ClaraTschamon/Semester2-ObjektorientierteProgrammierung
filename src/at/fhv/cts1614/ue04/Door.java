//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue04;

import java.util.Scanner;

public class Door extends Field {

    private Boolean _open;
    private Room _frontside;
    private Room _backside;

    public Door (Boolean open, Playground playground) {
        super("Door", playground);
        _open = open;
    }

    public Boolean get_open(){
        return _open = true;
    }

    public Room get_frontside(){
        return _frontside;
    }

    public Room get_backside(){
        return _backside;
    }

    public void setSide (Direction direction, Room room){

        switch (direction){
            case frontside:
                _frontside = room;
                break;
            case backside:
                _backside = room;
                break;
        }
    }

    public void enter(Player player, Direction direction){
        player.set_energyDown();
        if(!_open){
            System.out.println("The door is closed. \nDo you want to open the door?");
            System.out.println("If yes, type: 'yes'. If no, type: 'no'.");
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            while (!(string.equals("yes") || string.equals("no"))){
                System.out.println("Please enter either: 'yes' or: 'no'...");
            }
            if (string.equals("yes")){
                _open = true;
            }
            else{
                System.out.println("This door stayed closed.");
            }
        }
        if(_open){
            if(player.get_position() != _frontside){
                player.set_position(_frontside);
            }
            else{
                player.set_position(_backside);
            }
            player.get_position().roomsAround(player);
        }

    }
}
