//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue04;

import java.util.LinkedList;
import java.util.Scanner;

public class Room  extends Field {
    private String _description;
    private Field _north;
    private Field _east;
    private Field _south;
    private Field _west;
    private LinkedList <Item> _items = new LinkedList<>();

    public Room(String roomname, String description, Playground playground){
        super(roomname, playground);
        _description = description;
    }

    public String get_description() {
        return _description;
    }

    public LinkedList<Item> get_items() {
        return _items;
    }

    public Field get_north() {
        return _north;
    }

    public Field get_east() {
        return _east;
    }

    public Field get_south() {
        return _south;
    }

    public Field get_west() {
        return _west;
    }

    public void setSide (Direction direction, Field field){
        if(field == this){
            throw new RuntimeException("A field can not be a neighbor of itself");
        }

        switch (direction){
            case north:
                _north = field;
                break;
            case east:
                _east = field;
                break;
            case south:
                _south = field;
                break;
            case west:
                _west = field;
                break;
        }
    }

    @Override
    public void enter(Player player, Direction direction) {
        player.set_position(this);
        player.set_energyDown();
        roomsAround(player);
    }

    public void roomsAround(Player player){
        System.out.println("\nWelcome to \nRoom: " + get_fieldnr() + ", " + this.get_name()
                + "\n" + "Description: " +  _description + "\n");
        surrounding(player);
    }

    public void surrounding(Player player){
        System.out.println("Your surrounding:");
        System.out.println("You are in: " + player.get_position().get_name());
        if(_north != null && ! (_north instanceof Door)){
            System.out.println("North: " + _north.get_name());
        }
        else if(_north != null && _north instanceof Door){
            System.out.print("North: " + _north.get_name() + " to field ");
            isDoor(_north);
        }
        else{
            System.out.println("North: NOTHING");
        }

        if(_east != null && ! (_east instanceof Door)){
            System.out.println("East: " + _east.get_name());
        }
        else if(_east != null && _east instanceof Door) {
            System.out.print("East: " + _east.get_name() + " to field ");
            isDoor(_east);
        }
        else{
            System.out.println("East: NOTHING");
        }

        if(_south != null && ! (_south instanceof Door)){
            System.out.println("South: " + _south.get_name());
        }
        else if(_south != null && _south instanceof Door){
            System.out.print("South: " + _south.get_name() + " to field ");
            isDoor(_south);
        }
        else{
            System.out.println("South: NOTHING");
        }

        if(_west != null && ! (_west instanceof Door)){
            System.out.println("West: " + _west.get_name());
        }
        else if (_west != null && _west instanceof Door){
            System.out.print("West: " + _west.get_name() + " to field ");
            isDoor(_west);
        }
        else{
            System.out.println("West: NOTHING");
        }
    }

    private void isDoor(Field direction){
        if (((Door) direction).get_frontside().get_fieldnr() != this.get_fieldnr()){
            System.out.print(((Door) direction).get_frontside().get_fieldnr() + ", ");
            System.out.println(((Door) direction).get_frontside().get_name());
        }
        else{
            System.out.print(((Door) direction).get_backside().get_fieldnr() + ", ");
            System.out.println(((Door) direction).get_backside().get_name());
        }
    }

    public void makeItemList(Playground playground){
        for(int i = 0; i < playground.get_items().size(); i++){
            if(playground.get_items().get(i).get_position() == this){
                _items.add(playground.get_items().get(i));
                playground.get_items().remove(i);
                i--;
            }
        }
    }

    public void printItems(){
        int number = 0;
        System.out.println("These Items are in this room:");
        for(int i = 0; i < _items.size(); i++){
            System.out.println(number + ": " + _items.get(i).get_name());
            number++;
        }
    }

    public void pickUpItem(Playground playground, Player player){

        if(_items.size() != 0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which Item do you want to pick up or eat? Type in the number.");
            Integer i = scanner.nextInt();
            while(i > _items.size() || i < 0){
                System.out.println("There is no Item with this number. Try again.");
                i = scanner.nextInt();
            }
            Item item = _items.get(i);
            if(item.getClass() == Food.class){
                player.set_energyUp(((Food) item).get_energy());
                System.out.println("Your energylevel: " + player.get_energy() + " %");
            }
            else{
                player.get_inventar().add(item);
                System.out.println("You picked up the item : " + item.get_name());
            }
            _items.remove(item);
        }
        else{
            System.out.println("Here is no item to pick up.");
        }
    }

}
