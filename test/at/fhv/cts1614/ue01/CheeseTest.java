package at.fhv.cts1614.ue01;

class CheeseTest {

    static Character[][] Cheese = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                                   {' ', '*', '*', '*', ' ', ' ', '*', ' '},
                                   {' ', '*', ' ', '*', ' ', ' ', ' ', ' '},
                                   {' ', '*', ' ', '*', ' ', ' ', '*', ' '},
                                   {' ', ' ', '*', ' ', ' ', ' ', '*', ' '},
                                   {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    /*static Character[][] Cheese = { {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                                    {' ', ' ', ' ', ' ', ' ', ' ', '*', ' '},
                                    {' ', '*', '*', ' ', ' ', ' ', ' ', ' '},
                                    {' ', '*', '*', ' ', ' ', ' ', '*', ' '},
                                    {' ', ' ', ' ', ' ', ' ', ' ', '*', ' '},
                                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};*/

    static int rows = 6;
    static int colums = 8;

    public static void main(String[] args) {

        int size_counter = 0;
        int biggest_hole = 0;
        int hole_counter = 0;
        int counter_i = 0; //Zeile
        int counter_j = 0; //Spalte

        while (counter_i < rows) {
            while (counter_j < colums) {
                if (Cheese[counter_i][counter_j] == '*') {
                    if (isLittleHole(counter_i, counter_j)) { //kleines Loch?
                        hole_counter++;
                        Cheese[counter_i][counter_j] =' ';
                        counter_i = 0;
                        counter_j = 0;
                        if (size_counter > biggest_hole) {
                            biggest_hole = size_counter;
                            size_counter = 0;
                        }
                    }
                    else {                                  //kein kleines Loch
                        while (Cheese[counter_i][counter_j] =='*'){
                            size_counter++;
                            Cheese[counter_i][counter_j] =' ';
                            if(Cheese[counter_i][counter_j + 1] == '*'){
                                counter_j++;
                            }
                            else if(Cheese[counter_i][counter_j-1] == '*'){
                                counter_j--;
                            }
                        }
                        size_counter = CheckHole(counter_i, counter_j, size_counter);
                        hole_counter++;
                        if (size_counter > biggest_hole){
                            biggest_hole = size_counter;
                        }
                        size_counter = 0;
                        counter_i = 0;
                        counter_j = 0;
                    }
                }
                counter_j++;
            }
            counter_j = 0;
            counter_i ++;
        }
        System.out.println("Ich habe " + hole_counter + " Löcher. Mein größtes Loch ist " + biggest_hole + " * groß.");
    }
    public static int CheckHole(int counter_i, int counter_j, int size_counter){
        //statisch weil die Funktion nur statische Variablen kennt
        while (counter_i < rows-1 && counter_j < colums-1) {
            counter_i++;
            if (Cheese[counter_i][counter_j] == '*') {
                size_counter++;
                Cheese[counter_i][counter_j] = ' ';
                size_counter = CheckLeftAndUp(counter_i, counter_j, size_counter);
            } else if (Cheese[counter_i][counter_j - 1] == '*') {
                size_counter++;
                counter_j--;
                Cheese[counter_i][counter_j] = ' ';
                size_counter = CheckLeftAndUp(counter_i, counter_j, size_counter);
            } else if (Cheese[counter_i][counter_j + 1] == '*') {
                size_counter++;
                counter_j++;
                Cheese[counter_i][counter_j] = ' ';
                size_counter = CheckLeftAndUp(counter_i, counter_j, size_counter);
            } else if (Cheese[counter_i - 1][counter_j] == '*'){
                size_counter++;
                counter_i--;
                Cheese[counter_i][counter_j] = ' ';
                size_counter = CheckLeftAndUp(counter_i, counter_j, size_counter);
            }
        }
        return size_counter;
    }

    public static int CheckLeftAndUp(int counter_i, int counter_j, int size_counter){
        while (Cheese[counter_i][counter_j+1] == '*'){
            size_counter++;
            counter_j++;
            Cheese[counter_i][counter_j] = ' ';
        }
        while(Cheese[counter_i][counter_j-1] == '*'){
            size_counter++;
            counter_j--;
            Cheese[counter_i][counter_j] = ' ';
        }

        while (rows-1 > counter_i && counter_i > 0  && counter_j < colums){
            counter_i --;
            if (Cheese[counter_i][counter_j] == '*') {
                size_counter++;
                Cheese[counter_i][counter_j] = ' ';
            }
            else if (Cheese[counter_i][counter_j+1] == '*'){
                size_counter++;
                counter_j++;
                Cheese[counter_i][counter_j] = ' ';
            }
            else if (Cheese[counter_i][counter_j-1] == '*'){
                size_counter++;
                counter_j--;
                Cheese[counter_i][counter_j] = ' ';
            }
        }
        return size_counter;
    }

    static Boolean isLittleHole(int counter_i, int counter_j) {

        if(counter_i < rows - 2 && counter_j < colums -1){
            if (Cheese[counter_i][counter_j+1] == ' '){
                counter_i++;
                if(checkRow(counter_i, counter_j) == true){
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    static Boolean checkRow(int counter_i, int counter_j){
        if (Cheese[counter_i][counter_j-1] == ' '
                && Cheese[counter_i][counter_j] == ' '
                && Cheese[counter_i][counter_j+1] == ' '){
            return true;
        }
        return false;
    }
}