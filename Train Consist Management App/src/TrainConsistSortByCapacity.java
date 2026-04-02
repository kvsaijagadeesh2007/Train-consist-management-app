import java.util.*;

class Bogie {
    String id;
    String type;
    int capacity;

    Bogie(String id, String type, int capacity) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return id + " (" + type + ") - Seats: " + capacity;
    }
}

public class TrainConsistSortByCapacity {

    static List<Bogie> bogies = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Train Consist Management - UC7 ===");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Display Bogies");
            System.out.println("3. Display Bogies Sorted by Capacity");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addBogie();
                    break;
                case 2:
                    displayBogies();
                    break;
                case 3:
                    displaySortedBogies();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    static void addBogie() {
        System.out.print("Enter Bogie ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Bogie Type (Sleeper/AC Chair/First Class): ");
        String type = sc.nextLine();
        System.out.print("Enter Seating Capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();
        bogies.add(new Bogie(id, type, capacity));
        System.out.println("Bogie added successfully.");
    }

    static void displayBogies() {
        if (bogies.isEmpty()) {
            System.out.println("No bogies available.");
            return;
        }
        System.out.println("\nCurrent Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }

    static void displaySortedBogies() {
        if (bogies.isEmpty()) {
            System.out.println("No bogies to display.");
            return;
        }
        List<Bogie> sortedList = new ArrayList<>(bogies);
        sortedList.sort(Comparator.comparingInt(b -> b.capacity));
        System.out.println("\nBogies Sorted by Capacity (Ascending):");
        for (Bogie b : sortedList) {
            System.out.println(b);
        }
    }
}