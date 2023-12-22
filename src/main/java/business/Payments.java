package business;

import java.util.List;
import java.util.Scanner;

public class Payments {
    private Scanner scanner = new Scanner(System.in);
    private List<Person> people;

    public Payments(List<Person> people) {
        this.people = people;
    }

    public void printEveryone() {
        for (Person person : people) {
            VoteService.printMassege(person.toString());
        }
    }

    private Person findPerson(String name) {
        for (Person person : people) {
            if (person.getFullName().contains(name)) {
                return person;
            }
        }
        return null;
    }

    public void addPersonWhoPaid(int numberOfPeople) {
        VoteService.printMassege("Podaj imiona ktore chcesz dodac do listy osob oplaconych: ");
        for (int i = 0; i < numberOfPeople; i++) {
            String name = scanner.next();
            Person person = findPerson(name);
            if (person != null) {
                person.setPaid(true);
            } else {
                VoteService.printMassege("Taka osoba nie istnieje na liscie czlonkow.");
            }
        }
    }
    public void addPersonWhoPaid(){
        VoteService.printMassege("Podaj imię osoby która chcesz dodać do listy osób opłaconych");
        String name = scanner.next();

        Person person = findPerson(name);
        if(person != null){
            person.setPaid(true);
        }else{
            VoteService.printMassege("Taka osoba nie istnieje na liście");
        }

    }
    public void printListOfPeopleWhoPaid() {
        for (Person person : people){
            VoteService.printMassege(person.toString());
        }
    }
}
