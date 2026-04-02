import java.util.*;

// Bogie class
class Bogie {
    String id;
    String type;

    Bogie(String id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return id + " (" + type + ")";
    }

    // Ensures uniqueness
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Bogie)) return false;
        Bogie b = (Bogie) obj;
        return this.id.equals(b.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}


public class TrainConsistApp {

    static LinkedHashSet<Bogie> consist = new LinkedHashSet<>();
    static Stack<Bogie> stack = new Stack<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== Train Consist Management =====");
            System.out.println("1. Add Bogie");
            System.out.println("2. Remove Last Bogie (LIFO)");
            System.out.println("3. Display Consist");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    addBogie();
                    break;

                case 2:
                    removeLastBogie();
                    break;

                case 3:
                    displayConsist();
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);
    }

    // Add bogie
    static void addBogie() {
        System.out.print("Enter Bogie ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Bogie Type: ");
        String type = sc.nextLine();

        Bogie b = new Bogie(id, type);

        if (consist.add(b)) {
            stack.push(b);
            System.out.println("Bogie added successfully.");
        } else {
            System.out.println("Duplicate Bogie! Not allowed.");
        }
    }

    // Remove last added bogie (LIFO)
    static void removeLastBogie() {
        if (stack.isEmpty()) {
            System.out.println("No bogies to remove.");
            return;
        }

        Bogie last = stack.pop();
        consist.remove(last);

        System.out.println("Removed Bogie: " + last);
    }

    // Display consist
    static void displayConsist() {
        if (consist.isEmpty()) {
            System.out.println("Train consist is empty.");
            return;
        }

        System.out.println("\nTrain Consist (Order Preserved):");
        for (Bogie b : consist) {
            System.out.println(b);
        }
    }
}