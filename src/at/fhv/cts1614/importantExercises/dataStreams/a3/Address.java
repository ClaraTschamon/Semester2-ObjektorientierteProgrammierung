package at.fhv.cts1614.importantExercises.dataStreams.a3;

public class Address {
    private String _street;
    private String _city;

    public Address(String street, String city){
        _street = street;
        _city = city;
    }

    public String get_street() {
        return _street;
    }

    public void set_street(String _street) {
        this._street = _street;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }


}
