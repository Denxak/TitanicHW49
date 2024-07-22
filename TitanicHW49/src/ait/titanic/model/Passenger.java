package ait.titanic.model;

public class Passenger {
    private int passengerId;
    private int survived;
    private String name;
    private String sex;
    private double age;
    private Ticket ticket;

    public Passenger(int passengerId, int survived, String name, String sex, double age, Ticket ticket) {
        this.passengerId = passengerId;
        this.survived = survived;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.ticket = ticket;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getSurvived() {
        return survived;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public double getAge() {
        return age;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
