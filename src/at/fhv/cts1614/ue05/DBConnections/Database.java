//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.DBConnections;

import java.util.LinkedList;
import java.util.List;

public class Database {
    private String _name;
    private static final int MAXCONNECTIONS = 5;
    private List<DBConnection> _active_connections = new LinkedList<>();

    public Database(String name){
        _name = name;
    }

    public List<DBConnection> get_active_connections() {
        return _active_connections;
    }

    public void connect(DBConnection connection){
        try{
            if(_active_connections.contains(connection)) {
                throw new RuntimeException(connection.get_username() + " is already connected.");
            }
            else if(_active_connections.size() < MAXCONNECTIONS) {
                connection.set_active(true);
                _active_connections.add(connection);
            }else{
                throw new RuntimeException("Already 5 active connections. " + connection.get_username() + " can not connect.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void disconnect(DBConnection connection){
        try{
            if(_active_connections.contains(connection)){
                if(!connection.is_active()){
                    throw new RuntimeException("The connection was already deconnected");
                }
                else{
                    connection.set_active(false);
                    _active_connections.remove(connection);
                }
            }
            else{
                throw new RuntimeException("There is no connection for " + connection.get_username());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //löschen eines Objekts durch referenz auf null setzen...
    /*
    public void deleteConnection(DBConnection connection){
        connection = null;
    }
    //null setzen funktioniert nicht... aber wieso nicht???

     */
}
