package at.fhv.cts1614.importantExercises.dataStreams.a2;

import java.io.Serializable;

public class Address implements Serializable {
    private String _street;
    private String _city;

    public Address(String street, String city){
        _street = street;
    }
}
