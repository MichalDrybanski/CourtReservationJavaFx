package business;

import java.util.List;

public class Person {
    private String fullName;
    private static int prevId = 0;
    private int id;
    private List<Integer> votedDays;
    private boolean paid = false;
    public Person(String fullName){
        this(fullName, List.of(0));
    }

    public Person(String fullName, List<Integer> votedDays) {
        this.votedDays = votedDays;
        this.fullName = fullName;
        this.id = ++prevId;
    }
    public boolean hasVoted(){
        return votedDays != null && !votedDays.isEmpty();
    }
    public boolean hasVotedForDay(int day){
        return votedDays.contains(day);
    }
    @Override
    public String toString() {
        return "Person{" +
                "Imię i Nazwisko='" + fullName + '\'' +
                ", id=" + id +
                ", Głosowanie=" + votedDays +
                ", Płatności=" + getStringOfBoolean() +
                '}';
    }
    public String getStringOfBoolean(){
        return isPaid() ? "Opłacone":"Nieopłacone";
    }
    public void setVotedDays(List<Integer> votedDays) {
        this.votedDays = votedDays;
    }

    public List<Integer> getVotedDays() {
        return votedDays;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isPaid() {
        return paid;
    }
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
