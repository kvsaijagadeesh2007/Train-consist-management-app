import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return guestName + " (" + roomType + ")";
    }
}

public class UseCase6RoomAllocationService {

    private Queue<BookingRequest> requestQueue = new LinkedList<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private int roomCounter = 100;

    public UseCase6RoomAllocationService() {
        inventory.put("Single", 3);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    public void addBookingRequest(String guestName, String roomType) {
        BookingRequest request = new BookingRequest(guestName, roomType);
        requestQueue.add(request);
        System.out.println("Booking request added: " + request);
    }

    public void processNextBooking() {
        if (requestQueue.isEmpty()) {
            System.out.println("No pending booking requests.");
            return;
        }

        BookingRequest request = requestQueue.poll();
        String type = request.roomType;
        Integer available = inventory.getOrDefault(type, 0);

        if (available <= 0) {
            System.out.println("No rooms available for type " + type + " for guest " + request.guestName);
            return;
        }

        String roomId = type.substring(0, 1).toUpperCase() + roomCounter++;
        Set<String> allocatedSet = allocatedRooms.get(type);

        if (allocatedSet.contains(roomId)) {
            System.out.println("Room ID collision! This should never happen.");
            return;
        }

        allocatedSet.add(roomId);
        inventory.put(type, available - 1);

        System.out.println("Booking confirmed for " + request.guestName +
                ". Room ID: " + roomId + " (" + type + ")");
    }

    public void displayStatus() {
        System.out.println("\n=== Current Inventory & Allocations ===");
        for (String type : inventory.keySet()) {
            System.out.println(type + " - Available: " + inventory.get(type) +
                    ", Allocated Rooms: " + allocatedRooms.get(type));
        }
        System.out.println("======================================\n");
    }

    public static void main(String[] args) {
        UseCase6RoomAllocationService service = new UseCase6RoomAllocationService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Hotel Booking Management System ===");
            System.out.println("1. Add Booking Request");
            System.out.println("2. Process Next Booking");
            System.out.println("3. Display Status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Guest Name: ");
                    String guest = sc.nextLine();
                    System.out.print("Room Type (Single/Double/Suite): ");
                    String type = sc.nextLine();
                    service.addBookingRequest(guest, type);
                    break;
                case 2:
                    service.processNextBooking();
                    break;
                case 3:
                    service.displayStatus();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}