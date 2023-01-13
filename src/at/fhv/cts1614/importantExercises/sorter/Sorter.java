package at.fhv.cts1614.importantExercises.sorter;

import java.util.LinkedList;
import java.util.List;

public class Sorter {
    private int [] values = {3, 2, 10, 5, 1, 0};




    //vorgefertigter Comparator verwenden:
    //ich kann die Liste so eifach in ArrayList umändern...
    //List<String> strs = new LinkedList<String>();
    //List<String> strs = new ArrayList<String>();
    //strs.add("Hallo"); --> Funktioniert nicht weil das Verhalten innerhalb von einer Methode sein muss

    public static void main(String[] args) {
        List<String> strs = new LinkedList<String>();
        strs.add("Hallo");
        strs.add("Test");

        //java.util brauche ich davor weil ich den Namen sorter bereits selbst verwendet habe
        strs.sort(new java.util.Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                return o1.compareTo(o2);
            }
        });

        for (String str:strs){
            System.out.println(str);
        }

        class Foo implements Comparable<Foo>{
            @Override
            public int compareTo(Foo o){
                return 0;
            }
            List <Foo> foos = null;
            //Collections.sort(foos);
        }
    }




}
