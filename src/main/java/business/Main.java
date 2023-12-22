package business;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
//        List<Person> list = new ArrayList<>(List.of(
//                new Person("Michal", List.of(1,2,3,4)),
//                new Person("Adam", List.of(0,1)),
//                new Person("Michal D", List.of(0,1)),
//                new Person("Andrzej", List.of(0,1)),
//                new Person("Dominika", List.of(0,1,3)),
//                new Person("Karolina", List.of(0,1,4)),
//                new Person("Pawel", List.of(0,1,3)),
//                new Person("Pawel D", List.of(0,1,3)),
//                new Person("Kamil K", List.of(0,1)),
//                new Person("Kamil P", List.of())
//        ));
        VoteService voteService = new VoteService(people);

        Payments payments = new Payments(voteService.getPeople());
        Controller controller = new Controller(voteService, payments);
        controller.launchProgram();
    }
}
