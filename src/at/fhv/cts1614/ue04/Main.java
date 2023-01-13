//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue04;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File currdirectory = new File(System.getProperty("src/ue4new"));
        File[] directorylisting = currdirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("txt");
            }
        });
        Playground playground = new Playground("myMiniMUD");
        if(directorylisting.length == 0){
            playground.create();
            Player player = playground.get_players().get(1);
            player.play(playground);
        }
        else{
            playground.reloadOrCreate(playground);
        }

        System.out.println(playground.get_fields().get("Livingroom").get_fieldnr()); //1
        System.out.println(playground.get_fields().get("Kitchen").get_fieldnr()); //3


        //Testen kann man jetzt gut! Z.b. durch assertEquals energylevel usw.
        //wenn spieler keine Energie mehr hat stirbt er... remove von der Liste
        //evtl nur speichern wenn safe aufgerufen wird...
        //bei betreten von spiel fragen, welche Spielsituation wiederhergestellt werden soll...
        //also man kann auch mehrere Dateien haben in denen Spielsituationen gespeichert sind.
        //vielleicht für jeden Spieler die möglichkeit Spielsituation zu speichern?
        //Buch 329
    }
}
