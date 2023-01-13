package at.fhv.cts1614.importantExercises.dataStreams.a2;

import java.io.*;
import java.util.Objects;

public class Person implements Serializable{

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return _salary == person._salary && Objects.equals(_name, person._name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name, _salary);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Address address1 = new Address("Hummelweg", "Nenzing");
        Person johannes = new Person("Johannes", 1999, address1);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("src/at/fhv/cts1614/seminar07/a2/myObject.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("src/at/fhv/cts1614/seminar07/a2/myObject.txt")));
        objectOutputStream.writeObject(johannes);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("src/at/fhv/cts1614/seminar07/a2/myObject.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person p2 = (Person) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(johannes.equals(p2));
    }
}
