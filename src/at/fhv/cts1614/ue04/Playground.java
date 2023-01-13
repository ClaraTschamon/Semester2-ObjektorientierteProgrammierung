//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue04;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Playground implements Serializable{

    private static final long serialVersionUID = 5L;
    private static int MAXID;
    private final int ID;
    private String _name;

    Map <String, Field> _fields = new HashMap<>(); //<Key, Value> = <name, field>
    LinkedList <Player> _players = new LinkedList<>();
    LinkedList <Item> _items = new LinkedList<>();

    public Playground(String name){
        MAXID++;
        ID = MAXID;
        _name = name;
    }

    public Map<String, Field> get_fields() {
        return _fields;
    }

    public LinkedList<Item> get_items(){
        return _items;
    }
    public LinkedList<Player> get_players(){
        return _players;
    }

    public void create(){
        Room livingroom = new Room("Livingroom", "Just a normal livingroom.", this);
        Room bathroom = new Room("Bathroom", "The bathroom. Not really interesting.", this);
        Room kitchen = new Room("Kitchen", "the kitchen", this);
        Room hallway = new Room("Hallway", "The one and only hallway.", this);
        Room bedroom = new Room("Bedroom", "This is your bedroom", this);

        Door door1 = new Door(false, this);
        Door door2 = new Door(true, this);
        Door door3 = new Door(false, this);
        Door door4 = new Door(true, this);

        Wall wall1 = new Wall(this);
        Wall wall2 = new Wall(this);
        Wall wall3 = new Wall(this);
        Wall wall4 = new Wall(this);
        Wall wall5 = new Wall(this);
        Wall wall6 = new Wall(this);
        Wall wall7 = new Wall(this);

        livingroom.setSide(Direction.north, door2);
        livingroom.setSide(Direction.east, door1);
        livingroom.setSide(Direction.south, wall1);
        livingroom.setSide(Direction.west, wall2);

        bathroom.setSide(Direction.north, door4);
        bathroom.setSide(Direction.east, null);
        bathroom.setSide(Direction.south, null);
        bathroom.setSide(Direction.west, door1);

        bedroom.setSide(Direction.north, wall4);
        bedroom.setSide(Direction.east, door3);
        bedroom.setSide(Direction.south, wall3);
        bedroom.setSide(Direction.west, null);

        kitchen.setSide(Direction.north, wall5);
        kitchen.setSide(Direction.east, hallway);
        kitchen.setSide(Direction.south, door2);
        kitchen.setSide(Direction.west, door3);

        hallway.setSide(Direction.north, wall6);
        hallway.setSide(Direction.east, null);
        hallway.setSide(Direction.south, door4);
        hallway.setSide(Direction.west, kitchen);

        door1.setSide(Direction.frontside, bathroom);
        door1.setSide(Direction.backside, livingroom);

        door2.setSide(Direction.frontside, kitchen);
        door2.setSide(Direction.backside, livingroom);

        door3.setSide(Direction.frontside, kitchen);
        door3.setSide(Direction.backside, bedroom);

        door4.setSide(Direction.frontside, hallway);
        door4.setSide(Direction.backside, bathroom);

        Player lukas = new Player("Lukas", livingroom, this);
        Player clara = new Player("Clara", bathroom, this);
        Player mama = new Player("Mama", hallway, this);

        Item sofa = new Item("Sofa", livingroom, this);
        Item book = new Item("Book", livingroom, this);
        Item soap = new Item ("Soap", bathroom, this);
        Item towel = new Item("Towel", bathroom, this);
        Item fork = new Item("Fork", kitchen, this);
        Food apple = new Food("Apple", kitchen, 10, this);
        Food bread = new Food("Bread", kitchen, 20, this);
        Food chips = new Food("Chips", bedroom, 15, this);

        safe();
    }

    public void printOtherPlayers(Room room){
        System.out.println("Players in this room:");
        for (int i = 0; i < _players.size(); i++){
            if(_players.get(i).get_position() == room){
                System.out.println(_players.get(i).get_name());
            }
        }
    }

    public void reloadOrCreate(Playground playground){
        System.out.println("Do you want to create a new game or load an old state?");
        System.out.println("Type 'N' for new or 'O' for old...");
        Scanner scanner = new Scanner(System.in);
        String in = scanner.next();
        if(in.equals("N")){
            playground.create();
        }
        else{
            File currdirectory = new File(System.getProperty("src/ue4new"));
            File[] directorylisting = currdirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith("txt");
                }
            });
            System.out.println("MiniMUD states: ");
            int counter = 0;
            for(File file : directorylisting){
                counter++;
                System.out.println(counter + " " + file.getName());
            }
            System.out.println("Which state do you want to load? Type in the number");
            int input = scanner.nextInt();
            while(input > directorylisting.length){
                System.out.println("Invalid entry");
                input = scanner.nextInt();
            }
            reload(directorylisting[input].getAbsolutePath());
        }
    }

    public void exit(Player player){
        System.out.println("Player " + player.get_name() + " just left the game.");
        safe();
    }

    public void safe(){
        System.out.println("Enter a gamename...");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        try(
                FileOutputStream fileOutputStream = new FileOutputStream(new File("src/at/fhv/cts1614/ue04/" + input + ".txt"));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Playground reload(String inputfile){
        try(
                FileInputStream fileInputStream = new FileInputStream(inputfile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);)
        {
            Playground playground2 = (Playground) objectInputStream.readObject();
            return playground2;
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
