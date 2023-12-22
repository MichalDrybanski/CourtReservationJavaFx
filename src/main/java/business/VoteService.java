package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class VoteService {
    private Scanner scanner = new Scanner(System.in);
    private List<Person> people;
    private  List<Integer> onePersonChosenDaysIndexes;
    public VoteService(List<Person> people) {
        this.people = people;
    }

    public void collectVotes() {
        for(Person person : people) {
            onePersonChosenDaysIndexes = new ArrayList<>();

            printMassege("Osoba głosująca: " + person.getFullName());

            while (true) {
                printMassege("""
                        Wybierz odpowiedni dla Ciebie dzień w tygodniu od poniedziałku do piątku.
                        1 - Poniedziałek
                        2 - Wtorek
                        3 - Środa
                        4 - Czwartek
                        5 - Piątek
                        6 - Zakończ wybieranie""");
                int choice = scanner.nextInt();
                if (onePersonChosenDaysIndexes.contains(choice)) {
                    printMassege("Ten dzień został juz wybrany.");
                    continue;
                }
                if (choice >= 1 && choice <= 5) {
                    onePersonChosenDaysIndexes.add(choice-1);
                } else if (choice == 6) {
                    break;
                }
            }
            person.setVotedDays(new ArrayList<>(onePersonChosenDaysIndexes));
        }
    }
    public Person findPerson(String fullName){
        for(Person person: people){
            if(person.getFullName().equals(fullName)){
                return person;
            }
        }
        return null;
    }
    public void addPeople(){
        printMassege("Podaj imię osoby, którą chcesz dodać do listy członków: ");
        String name = scanner.next();

        if(findPerson(name) != null){
            printMassege("Taka osoba już istnieje");
            return;
        }
        people.add(new Person(name, List.of()));

        printMassege("Dodano nową osobę do listy");
    }
    private List<String> listOfPeopleNotVoted() {
        List<String> names = new ArrayList<>();

        for (Person person : people) {
            if (!person.hasVoted()) {
                names.add(person.getFullName());
            }
        }
        sortList(names);
        return names;
    }
    private List<String> whoVotedForWinningDay(){
        int [] votesPerDay = countVotesPerDay();
        int winningDay = findMaxVotesDay(votesPerDay);

        List<String> listOfPeopleVotingWinnerDay = new ArrayList<>();

        for(Person person : people){
            if(person.hasVotedForDay(winningDay)){
                listOfPeopleVotingWinnerDay.add(person.getFullName());
            }
        }
        sortList(listOfPeopleVotingWinnerDay);
        return listOfPeopleVotingWinnerDay;
    }
    private void sortList (List<String> listToSort){
        Collections.sort(listToSort);
    }
    public int findMaxVotesDay(int[] days) {
        int mostVotes = days[0];
        int day = 0;
        for (int i = 0; i < days.length; i++) {
            if (mostVotes < days[i]) {
                mostVotes = days[i];
                day = i;
            }
        }
        return day;
    }
    public int[] countVotesPerDay() {
        int[] votesPerDay = new int[5];
        for (int day = 0; day < votesPerDay.length; day++) {
            int counterOfVotes = 0;
            for (Integer compareNumber : countChosenDays()) {
                if (compareNumber.equals(day)) {
                    counterOfVotes++;
                }
            }
            votesPerDay[day] = counterOfVotes;
        }
        return votesPerDay;
    }
    private List<Integer> countChosenDays(){
        List<Integer> chosenDays = new ArrayList<>();
        for(Person person : people){
            chosenDays.addAll(person.getVotedDays());
        }
        return chosenDays;
    }

    public void printReport(int day, int[] tab) {
        String dayName = convertToDayName(day);
        System.out.println("Najwiecęj glosów ma dzień: " + dayName);
        System.out.println("Głosów: " + tab[day]);
        if (tab[day] < 8) {
            System.out.println("Nie ma wystarczająco osób na ten dzień");
        } else System.out.println("Wybrany dzień to " + dayName);
    }
    public void printMembersWhoNotVoted(){
        List<String> notVoted = listOfPeopleNotVoted();
        for(String notVot : notVoted){
            printMassege(notVot);
        }
    }
    public void printMembersWhoVotedWinningDay(){
        List<String> winners = whoVotedForWinningDay();
        for(String win : winners){
            printMassege(win);
        }
    }
    public static void printMassege(String message) {
        System.out.println(message);
    }

    public void printMembersVotes() {
        for (Person person : people) {
            List<Integer> dayIndexes = person.getVotedDays();
            StringBuilder sb = new StringBuilder(person.getFullName() + " zaglosował/a na: ");
            int i = 0;
            for(Integer day : dayIndexes){
                sb.append(convertToDayName(day));//todo: DONE zmienic na wyswietlanie w jednej lini
                if(++i < dayIndexes.size()){
                    sb.append(", ");
                }
            }
            printMassege(sb.toString());
        }
    }
    private String convertToDayName(int day) {
        switch (day) {
            case 0:
                return "Poniedziałek";
            case 1:
                return "Wtorek";
            case 2:
                return "Środa";
            case 3:
                return "Czwartek";
            case 4:
                return "Piątek";
            default:
                return "Nieprawidłowy dzień";
        }
    }
    @Override
    public String toString() {
        return "VoteService{" +
                "people=" + people +
                '}';
    }
    public List<Person> getPeople() {
        return people;
    }
}
