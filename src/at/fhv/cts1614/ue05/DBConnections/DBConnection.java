//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.DBConnections;

public class DBConnection {

    private String _username;
    private boolean _active;

    public DBConnection(String username){
        _username = username;
    }

    public boolean is_active() {
        return _active;
    }

    public void set_active(boolean active) {
        _active = active;
    }

    public String get_username() {
        return _username;
    }
}
