package business;

import java.util.Scanner;

public class Controller {
    private Scanner scanner = new Scanner(System.in);
    private VoteService voteService;
    private Payments payments;
    public Controller(VoteService voteService, Payments payments) {
        this.voteService = voteService;
        this.payments = payments;
    }

    public void launchProgram(){
        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    voteService.addPeople();
                    break;
                case 2:
                    voteService.collectVotes();
                    break;
                case 3:
                    int [] days = voteService.countVotesPerDay();
                    int maxDayIndex = voteService.findMaxVotesDay(days);
                    voteService.printReport(maxDayIndex, days);
                    break;
                case 4:
                    voteService.printMembersWhoVotedWinningDay();
                    break;
                case 5:
                    voteService.printMembersWhoNotVoted();
                    break;
                case 6:
                    payments.addPersonWhoPaid();
                    break;
                case 7:
                    payments.printListOfPeopleWhoPaid();
                    break;
                case 8:
                    return;
                default: VoteService.printMassege("Wybierz opcje z menu.");
            }
        }
    }
    private void printMenu(){
        VoteService.printMassege("Wybierz operację, którą chcesz wykonać: \n" +
                "1. Dodaj nowego uczestnika \n" +
                "2. Zbierz głosy uczestników \n" +
                "3. Wyświetl raport głosowania \n" +
                "4. Wyświetl osoby, które zagłosowały na zwycięski dzień \n" +
                "5. Wyświetl osoby które nie oddały głosu \n" +
                "6. Dodaj osobę, która opłaciła\n" +
                "7. Wyświetl listę osób z infarmacją o płatnościach\n" +
                "8. Exit");
    }
}
