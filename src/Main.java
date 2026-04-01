
import java.util.*;

// Comparator for Train Formation Order
class BogieComparator implements Comparator<String> {
    public int compare(String b1, String b2) {
        return getPriority(b1) - getPriority(b2);
    }

    private int getPriority(String id) {
        if (id.startsWith("E")) return 1; // Engine
        if (id.startsWith("P")) return 2; // Passenger
        if (id.startsWith("C")) return 3; // Cargo
        if (id.startsWith("G")) return 4; // Guard
        return 5;
    }
}

// Passenger Bogie Class
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

// Train Class
class Train {
    ArrayList<PassengerBogie> passengerBogies = new ArrayList<>();
    HashSet<String> bogieIds = new HashSet<>();
    SortedSet<String> formation = new TreeSet<>(new BogieComparator());

    void addPassengerBogie(PassengerBogie bogie) {
        if (bogieIds.contains(bogie.bogieId)) {
            System.out.println("Duplicate Bogie ID not allowed!");
            return;
        }

        passengerBogies.add(bogie);
        bogieIds.add(bogie.bogieId);
        formation.add(bogie.bogieId);

        System.out.println("Passenger bogie added successfully.");
    }

    void removePassengerBogie(String bogieId) {
        Iterator<PassengerBogie> iterator = passengerBogies.iterator();

        while (iterator.hasNext()) {
            PassengerBogie b = iterator.next();
            if (b.bogieId.equals(bogieId)) {
                iterator.remove();
                bogieIds.remove(bogieId);
                formation.remove(bogieId);
                System.out.println("Bogie removed successfully.");
                return;
            }
        }
        System.out.println("Bogie not found.");
    }

    void displayAllBogies() {
        for (PassengerBogie b : passengerBogies) {
            b.display();
        }
    }

    void displayFormation() {
        System.out.println("Train Formation Order:");
        for (String id : formation) {
            System.out.println(id);
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Train train = new Train();

        while (true) {
            System.out.println("\n--- Train Consist Management ---");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Remove Passenger Bogie");
            System.out.println("3. Display All Bogies");
            System.out.println("4. Display Train Formation Order");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Bogie ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Bogie Type: ");
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
                    train.displayAllBogies();
                    break;

                case 4:
                    train.displayFormation();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}