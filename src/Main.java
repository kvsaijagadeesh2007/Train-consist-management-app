import java.util.ArrayList;
import java.util.Scanner;

class PassengerBogie {
    String bogieId;
    String type;
    int seatCapacity;

    PassengerBogie(String bogieId, String type, int seatCapacity) {
        this.bogieId = bogieId;
        this.type = type;
        this.seatCapacity = seatCapacity;
    }

    void display() {
        System.out.println("Bogie ID: " + bogieId +
                ", Type: " + type +
                ", Seat Capacity: " + seatCapacity);
    }
}

class Train {
    ArrayList<PassengerBogie> passengerBogies = new ArrayList<>();

    void addPassengerBogie(PassengerBogie bogie) {
        passengerBogies.add(bogie);
        System.out.println("Passenger bogie added successfully.");
    }

    void removePassengerBogie(String bogieId) {
        for (PassengerBogie b : passengerBogies) {
            if (b.bogieId.equals(bogieId)) {
                passengerBogies.remove(b);
                System.out.println("Bogie removed successfully.");
                return;
            }
        }
        System.out.println("Bogie not found.");
    }

    void searchBogie(String bogieId) {
        for (PassengerBogie b : passengerBogies) {
            if (b.bogieId.equals(bogieId)) {
                System.out.println("Bogie Found:");
                b.display();
                return;
            }
        }
        System.out.println("Bogie not found.");
    }

    void displayAllBogies() {
        if (passengerBogies.isEmpty()) {
            System.out.println("No passenger bogies attached.");
            return;
        }
        for (PassengerBogie b : passengerBogies) {
            b.display();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Train train = new Train();

        while (true) {
            System.out.println("\n--- Train Consist Management ---");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Remove Passenger Bogie");
            System.out.println("3. Search Bogie");
            System.out.println("4. Display All Bogies");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Bogie ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Bogie Type (Sleeper/AC/FirstClass): ");
                    String type = sc.nextLine();
                    System.out.print("Enter Seat Capacity: ");
                    int cap = sc.nextInt();
                    train.addPassengerBogie(new PassengerBogie(id, type, cap));
                    break;

                case 2:
                    System.out.print("Enter Bogie ID to remove: ");
                    String removeId = sc.nextLine();
                    train.removePassengerBogie(removeId);
                    break;

                case 3:
                    System.out.print("Enter Bogie ID to search: ");
                    String searchId = sc.nextLine();
                    train.searchBogie(searchId);
                    break;

                case 4:
                    train.displayAllBogies();
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}