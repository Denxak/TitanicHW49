package ait.titanic.model;

public class Ticket{
    private int pclass;
    private int sibSp;
    private int parch;
    private String ticket;
    private double fare;
    private String[] cabin;
    private Character embarked;

    public Ticket(int pclass, int sibSp, int parch, String ticket, double fare, String[] cabin, Character embarked) {
        this.pclass = pclass;
        this.sibSp = sibSp;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
    }

    public int getPclass() {
        return pclass;
    }

    public int getSibSp() {
        return sibSp;
    }

    public int getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public double getFare() {
        return fare;
    }

    public String[] getCabin() {
        return cabin;
    }

    public Character getEmbarked() {
        return embarked;
    }
}
