package ait.titanic;

import ait.titanic.model.Passenger;
import ait.titanic.model.Ticket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TitanicAppl {
    public static void main(String[] args) {
        List<Passenger> passengers = csvFileProcessing("train.csv");
        if (passengers != null && !passengers.isEmpty()) {
            printTask1(passengers);
            printTask2(passengers);
            printTask3(passengers);
            printTask4(passengers);
        } else {
            System.out.println("No data available.");
        }
    }

    private static List<Passenger> csvFileProcessing(String nameFile) {
        List<Passenger> passengers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
            String str = br.readLine();
            String[] cells = str.split(",");
            while ((str = br.readLine()) != null) {
                cells = str.split(",", -1);
                Ticket ticket = new Ticket(
                        parseInteger(cells[2]),
                        parseInteger(cells[7]),
                        parseInteger(cells[8]),
                        cells[9],
                        parseDouble(cells[10]),
                        cells[11].split(" "),
                        !cells[12].isEmpty() ? cells[12].charAt(0) : null);
                Passenger passenger = new Passenger(
                        parseInteger(cells[0]),
                        parseInteger(cells[1]),
                        cells[3] + " " + cells[4],
                        cells[5],
                        !cells[6].isEmpty() ? parseDouble(cells[6]) : -1,
                        ticket);
                passengers.add(passenger);
            }
            return passengers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer value: " + value);
            return 0;
        }
    }

    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.err.println("Invalid double value: " + value);
            return 0.0;
        }
    }

    private static void printTask1(List<Passenger> passengers) {
        Double totalFares = passengers.stream()
                .mapToDouble(passenger -> passenger.getTicket().getFare())
                .sum();
        System.out.println("Total fares = " + totalFares);
    }

    private static void printTask2(List<Passenger> passengers) {
        for (int i = 1; i <= 3; i++) {
            int numClasses = i;
            Long totalClasses = passengers.stream()
                    .filter(passenger -> passenger.getTicket().getPclass() == numClasses)
                    .count();
            Double totalFaresClasses = passengers.stream()
                    .filter(passenger -> passenger.getTicket().getPclass() == numClasses)
                    .mapToDouble(passenger -> passenger.getTicket().getFare())
                    .sum();
            double averageFare = totalClasses > 0 ? .1 * totalFaresClasses / totalClasses : 0;
            System.out.println("Average fare for " + numClasses + " classes = " + averageFare);
        }
    }

    private static void printTask3(List<Passenger> passengers) {
        printSurvivalStats(passengers, 1);
        printSurvivalStats(passengers, 0);
    }

    private static void printTask4(List<Passenger> passengers) {
        printSurvivalStats(passengers, "male", 1);
        printSurvivalStats(passengers, "female", 1);
        printSurvivalStats(passengers, "children", 1);

        printSurvivalStats(passengers, "male", 0);
        printSurvivalStats(passengers, "female", 0);
        printSurvivalStats(passengers, "children", 0);
    }

    private static void printSurvivalStats(List<Passenger> passengers, int survivalStatus) {
        long count = passengers.stream()
                .filter(passenger -> passenger.getSurvived() == survivalStatus)
                .count();
        String status = (survivalStatus == 1) ? "survived" : "non survived";
        System.out.println("Total quantity of " + status + " = " + count);
    }

    private static void printSurvivalStats(List<Passenger> passengers, String category, int survivalStatus) {
        long count;
        if ("children".equals(category)) {
            count = passengers.stream()
                    .filter(passenger -> passenger.getAge() < 18)
                    .filter(passenger -> passenger.getSurvived() == survivalStatus)
                    .count();
        } else {
            count = passengers.stream()
                    .filter(passenger -> category.equals(passenger.getSex()))
                    .filter(passenger -> passenger.getSurvived() == survivalStatus)
                    .count();
        }
        String status = (survivalStatus == 1) ? "survived" : "non survived";
        System.out.println("Total quantity of " + status + " " + category + " = " + count);
    }
}
