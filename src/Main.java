import java.util.*;

class Train {
    private String trainNumber;
    private String engineType;
    private List<String> bogies;

    public Train(String trainNumber, String engineType) {
        this.trainNumber = trainNumber;
        this.engineType = engineType;
        this.bogies = new ArrayList<>();
    }

    public void displaySummary() {
        System.out.println("===== TRAIN CONSIST SUMMARY =====");
        System.out.println("Train Number : " + trainNumber);
        System.out.println("Engine Type  : " + engineType);
        System.out.println("Total Bogies : " + bogies.size());
        System.out.println("Bogies List  : " + bogies);
        System.out.println("=================================");
    }
}

public class Main {
    public static void main(String[] args) {
        Train train = new Train("TCM101", "WAP-7 Engine");
        train.displaySummary();
    }
}