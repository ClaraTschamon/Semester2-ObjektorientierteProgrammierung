package at.fhv.cts1614.importantExercises.dataStreams.a3;

import java.io.*;
import java.util.Objects;

public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	private String _name;
    private int _salary;
    private Address _address;

    public Person(String name, int salary, Address address) {
        _name = name;
        _salary = salary;
        _address = address;
    }

    public String get_name() {
        return _name;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        //ich definiere die gesamte funktionalität neu... deshalb muss ich alles selbst implementieren
        //und nicht einfach nur die addresse
        //Serializable von Person kann nicht gelöscht werden wegen ObjectOutput
        out.writeUTF(_name);
        out.writeInt(_salary);
        out.writeUTF(_address.get_city());
        out.writeUTF(_address.get_street());
    }

    private Person readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        _name = in.readUTF();
        _salary = in.readInt();
        String street = in.readUTF();
        String city = in.readUTF();
        Address addr = new Address(street, city);
        
        
        return new Person(_name, _salary, addr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return _salary == person._salary && Objects.equals(_name, person._name);
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Address address = new Address("Saegerstrasse", "Dornbirn");
        Person lukas = new Person("Lukas", 2000, address);
       
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/at/fhv/cts1614/seminar07/a3/myPerson.txt"));
        lukas.writeObject(objectOutputStream);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/at/fhv/cts1614/seminar07/a3/myPerson.txt"));
        Person newLukas = lukas.readObject(objectInputStream);

        objectInputStream.close();

        System.out.println(lukas.equals(newLukas));
    }
}
