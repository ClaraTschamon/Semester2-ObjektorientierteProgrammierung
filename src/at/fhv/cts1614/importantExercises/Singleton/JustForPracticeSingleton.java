package at.fhv.cts1614.importantExercises.Singleton;

/*
The primary purpose of a Singleton class is to restrict the limit of
the number of object creation to only one. This often ensures that there is
access control to resources, for example, socket or database connection.
 */
public class JustForPracticeSingleton {

    private static JustForPracticeSingleton single_instance = null;

    public String s;

    private JustForPracticeSingleton(){
        s = "Hello I am a string part of Singleton class";
    }
    public static JustForPracticeSingleton getInstance(){
        if (single_instance == null){
            single_instance = new JustForPracticeSingleton();
        }
        return single_instance;
    }
}

class Connection{
    public static void main(String[] args) {
        JustForPracticeSingleton singletonX = JustForPracticeSingleton.getInstance();
        JustForPracticeSingleton singletonY = JustForPracticeSingleton.getInstance();
        JustForPracticeSingleton singletonZ = JustForPracticeSingleton.getInstance();

        System.out.println("Hashcode of x is " + singletonX.hashCode());
        System.out.println("Hashcode of y is " + singletonY.hashCode());
        System.out.println("Hashcode of z is " + singletonZ.hashCode());

        if(singletonX == singletonY && singletonY == singletonZ){
            System.out.println("Three objects point to the same memory location on the heap i.e, to the same object");
        }
        else{
            System.out.println("Three objects DO NOT point to the same memory location on the heap");
        }
    }
}
