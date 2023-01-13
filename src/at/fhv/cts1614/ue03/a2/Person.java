//Clara Tschamon, Gruppe 1
package at.fhv.cts1614.ue03.a2;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Objects;

public class Person {
    public enum Married{
        yes,
        no;
    }
    public enum Gender{
        male,
        female;
    }

    private String _first_name;
    private String _last_name;
    private String _unmarried_name;
    private Gender _gender;
    private String _birthday;
    private Married _married;
    private Person _married_with;
    private LocalDate _date_of_marriage;
    private LocalDate _date_of_divorce;
    private int _age;
    //alter generell nie speichern. Erst dann mit einer Methode berechnen lassen, wenn ich
    //es brauche. Sonst braucht es zu viel Rechenleistung
    private static int _highest_personID = 0;
    private int _personID;//zum überprüfen, dass nicht zwei die gleichen Personen als Trauzeuge angegeben werden.
    private LinkedList<Person> marriage_record = new LinkedList<>();
    private LinkedList<String> divorce_record = new LinkedList<>();

    public Person(String first_name, String last_name, Gender gender, String birthday){
        _first_name = first_name;
        _last_name = last_name;
        _gender = gender;
        _married = (Married.no);
        _birthday = birthday;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate _birthday = LocalDate.parse(birthday, formatter);
        LocalDate today = LocalDate.now();
        _age = Period.between(_birthday, today).getYears();
        _highest_personID++;
        _personID = _highest_personID;
    }

    /*
    Die getter braucht es in diesem Fall nur für die Testmethoden...
    Partner ist nämlich von der Klasse Person. Innerhalb der Klasse Person kann also direkt
    zugegriffen werden. Darum braucht es auch keine Setter.
    Wenn noch eine andere Klasse zugreifen müsste, würde man getter und setter brauchen.
     */
    public String get_first_name(){
        return _first_name;
    }

    public String get_last_name(){
        return _last_name;
    }

    public String get_unmarried_name(){
        return _unmarried_name;
    }

    public Gender get_gender(){
        return _gender;
    }

    public String get_birthday(){
        return _birthday;
    }

    public Married get_married() {
        return _married;
    }

    public Person get_married_with(){
        return _married_with;
    }

    public LocalDate get_date_of_marriage(){
        return _date_of_marriage;
    }

    public LocalDate get_date_of_divorce(){
        return _date_of_divorce;
    }

    public int get_age(){
        return _age;
    }

    public int get_personID(){
        return _personID;
    }

    /**
     * marries a man and a woman of age 18 or older.
     * The details are written in a marriage record.
     * @param partner partner to marry.
     */
    public void marry(Person partner, Person bestman1, Person bestman2){

        if(!(partner._married == Married.no && _married == Married.no)){ //war oder ist jemand verheiratet?
            throw new RuntimeException ("Marriage not allowed. Bride AND groom have to be unmarried and never married before.");
        }
        else if(partner._gender == _gender){
            throw new RuntimeException ("Marriage not allowed. Bride and groom can not be from the same gender.");
        }
        else if(_age < 18 || partner._age < 18){ //Mindestalter als Konstante!
            throw new RuntimeException("Marriage not allowd. Bride and groom habe to be 18 or older.");
        }
        else if(!(bestman1._age >= 18 && bestman2._age >= 18)){
            throw new RuntimeException("Marriage not allowed. Both bestmen have to be 18 or older.");
        }
        else if(bestman1._personID == bestman2._personID){
            throw new RuntimeException("Marriage not allowed. Two DIFFERENT bestmen needed.");
        }
        else if(this.equals(partner)){
            throw new RuntimeException("You are not allowed to marry yourself!");
        }

        if(_gender == Gender.female){
            _unmarried_name = _last_name;
            _last_name = partner._last_name;
        }
        else if(partner._gender == Gender.female){
            partner._unmarried_name = partner._last_name;
            partner._last_name = _last_name;
        }
        _married = Married.yes;
        partner._married = Married.yes;
        _married_with = partner;
        partner._married_with = this;
        LocalDate today = LocalDate.now();
        _date_of_marriage = today;
        partner._date_of_marriage = today;
        System.out.println(_first_name + " and " + partner._first_name + " are now married.");
        System.out.println(bestman1._first_name + " and " + bestman2._first_name + " are the bestmen.");
        marriage_record.add(this);
        marriage_record.add(partner);
        marriage_record.add(bestman1);
        marriage_record.add(bestman2);
    }

    /**
     * Divorces two people who are married to each other.
     * The details are written into the divorce record.
     */
    public void divorce(Person partner, String reason){
        if(!partner.equals(_married_with))
        {
            throw new RuntimeException ("These two people are not married.");
        }
        divorce_record.add(this.toString());
        divorce_record.add(partner.toString());
        divorce_record.add(reason);
        if(_gender == Gender.female){
            _last_name = _unmarried_name;
        }
        else if(partner._gender == Gender.female){
            partner._last_name = partner._unmarried_name;
        }
        _married = Married.no;
        partner._married = Married.no;
        System.out.println("Divorce successful. Reason for divorce: \t" + reason);
        _married_with = null;
        partner._married_with = null;
        LocalDate today = LocalDate.now();
        _date_of_divorce = today;
        partner._date_of_divorce = today;
    }

    /**
     * prints the marriage record of a specific person
     */
    public void print_marriage_record(){
        if (_married_with != null) {
            System.out.println("Marriage record of " + _first_name + " " + _last_name);

            for (int i = 0; i < marriage_record.size(); i++) {
                Person p = marriage_record.get(i);
                switch (i) {
                    case 0:
                        System.out.println("partner 1");
                        break;
                    case 1:
                        System.out.println("partner 2");
                        break;
                    case 2:
                        System.out.println("bestman 1");
                        break;
                    case 3:
                        System.out.println("bestman 2");
                        break;
                }
                System.out.print("first name: " + p._first_name + "\t  last name: " + p._last_name + "\t  unmarried name: "
                        + p._unmarried_name + " \t  birth date: " + p._birthday + "\t  married: " + p._married
                        +  "\t  date of marriage: " + p._date_of_marriage + "\n");

                if(p._married_with != null){
                    System.out.println( "married with: " + p._married_with._first_name + "\n");
                }
                else{
                    System.out.println("married with: null\n");
                }

            }
        }
        else{
                System.out.println("not married");
            }
    }

    /**
     * prints the divorce record of a specific person
     */
    public void print_divorce_record(){
        System.out.println("Divorce record of " + _first_name + " " + _last_name);

        for(int i = 0; i < divorce_record.size(); i++){
            switch (i){
                case 0:
                    System.out.print("partner 1: \t");
                    System.out.println(divorce_record.get(i));
                    break;
                case 1:
                    System.out.print("partner 2: \t");
                    System.out.println(divorce_record.get(i));
                    break;
                case 2:
                    System.out.print("reason for divorce: \t");
                    System.out.println(divorce_record.get(i));
                    break;
            }
        }
        System.out.println("date_of_divorce: \t" + _date_of_divorce);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return _age == person._age && _personID == person._personID && Objects.equals(_first_name, person._first_name) && Objects.equals(_last_name, person._last_name) && Objects.equals(_unmarried_name, person._unmarried_name) && _gender == person._gender && Objects.equals(_birthday, person._birthday) && _married == person._married && Objects.equals(_married_with, person._married_with) && Objects.equals(_date_of_marriage, person._date_of_marriage) && Objects.equals(marriage_record, person.marriage_record);
    }

}
