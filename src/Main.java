import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Customer {
    private String name;
    private String mobileNumber;
    private String email;
    private String city;
    private int age;

    public Customer(String name, String mobileNumber, String email, String city, int age) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    // Getters and setters here
}

class Bus {
    private String busNumber;
    private int totalSeats;
    private String startingPoint;
    private String endingPoint;
    private String startingTime;
    private double fare;

    public Bus(String busNumber, int totalSeats, String startingPoint, String endingPoint, String startingTime, double fare) {
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingTime = startingTime;
        this.fare = fare;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public double getFare() {
        return fare;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    // Getters and setters here
}

class Reservation {
    private Customer customer;
    private Bus bus;
    private int seatNumber;

    public Reservation(Customer customer, Bus bus, int seatNumber) {
        this.customer = customer;
        this.bus = bus;
        this.seatNumber = seatNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Bus getBus() {
        return bus;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}

public class Main {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Bus> buses = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private Queue<Customer> waitingQueue = new LinkedList<>();

    public static void main(String[] args) {
        Main system = new Main();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register Customer");
            System.out.println("2. Register Bus");
            System.out.println("3. Search for Buses");
            System.out.println("4. Reserve Seat");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Request New Seat");
            System.out.println("7. Display Reservations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    system.registerCustomer(scanner);
                    break;
                case 2:
                    system.registerBus(scanner);
                    break;
                case 3:
                    system.searchBuses(scanner);
                    break;
                case 4:
                    system.reserveSeat(scanner);
                    break;
                case 5:
                    system.cancelReservation(scanner);
                    break;
                case 6:
                    system.requestNewSeat(scanner);
                    break;
                case 7:
                    system.displayReservations();
                    break;
                case 0:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void registerCustomer(Scanner scanner) {
        System.out.println("Customer Registration:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Mobile Number: ");
        String mobileNumber = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Customer newCustomer = new Customer(name, mobileNumber, email, city, age);
        customers.add(newCustomer);
        System.out.println("Customer registered successfully!");
    }

    public void registerBus(Scanner scanner) {
        System.out.println("Bus Registration:");
        System.out.print("Bus Number: ");
        String busNumber = scanner.nextLine();
        System.out.print("Total Seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Starting Point: ");
        String startingPoint = scanner.nextLine();
        System.out.print("Ending Point: ");
        String endingPoint = scanner.nextLine();
        System.out.print("Starting Time: ");
        String startingTime = scanner.nextLine();
        System.out.print("Fare: ");
        double fare = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        Bus newBus = new Bus(busNumber, totalSeats, startingPoint, endingPoint, startingTime, fare);
        buses.add(newBus);
        System.out.println("Bus registered successfully!");
    }

    public void searchBuses(Scanner scanner) {
        System.out.println("Bus Search:");
        System.out.print("Enter starting point: ");
        String startingPoint = scanner.nextLine();
        System.out.print("Enter ending point: ");
        String endingPoint = scanner.nextLine();

        System.out.println("Available Buses:");
        for (Bus bus : buses) {
            if (bus.getStartingPoint().equalsIgnoreCase(startingPoint) && bus.getEndingPoint().equalsIgnoreCase(endingPoint)) {
                System.out.println("Bus Number: " + bus.getBusNumber());
                System.out.println("Starting Time: " + bus.getStartingTime());
                System.out.println("Fare: " + bus.getFare());
                System.out.println();
            }
        }
    }

    public void reserveSeat(Scanner scanner) {
        System.out.println("Reserve Seat:");
        // Display available buses
        System.out.println("Available Buses:");
        for (int i = 0; i < buses.size(); i++) {
            System.out.println((i + 1) + ". " + buses.get(i).getBusNumber());
        }

        System.out.print("Select a bus (enter the bus number): ");
        int busIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline character

        if (busIndex >= 0 && busIndex < buses.size()) {
            Bus selectedBus = buses.get(busIndex);

            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();

            Customer customer = null;
            for (Customer c : customers) {
                if (c.getName().equalsIgnoreCase(customerName)) {
                    customer = c;
                    break;
                }
            }

            if (customer != null) {
                if (selectedBus.getTotalSeats() > reservations.size()) {
                    int seatNumber = reservations.size() + 1;
                    Reservation reservation = new Reservation(customer, selectedBus, seatNumber);
                    reservations.add(reservation);
                    System.out.println("Seat reserved successfully!");
                } else {
                    waitingQueue.add(customer);
                    System.out.println("All seats are occupied. Added to the waiting queue.");
                }
            } else {
                System.out.println("Customer not found. Please register the customer first.");
            }
        } else {
            System.out.println("Invalid bus selection.");
        }
    }

    public void cancelReservation(Scanner scanner) {
        System.out.println("Cancel Reservation:");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        Customer customer = null;
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(customerName)) {
                customer = c;
                break;
            }
        }

        if (customer != null) {
            // Find and cancel the reservation for this customer
            Reservation reservationToRemove = null;
            for (Reservation reservation : reservations) {
                if (reservation.getCustomer() == customer) {
                    reservationToRemove = reservation;
                    break;
                }
            }

            if (reservationToRemove != null) {
                reservations.remove(reservationToRemove);
                System.out.println("Reservation canceled successfully!");
            } else {
                System.out.println("No reservation found for this customer.");
            }
        } else {
            System.out.println("Customer not found. Please register the customer first.");
        }
    }

    public void requestNewSeat(Scanner scanner) {
        System.out.println("Request New Seat:");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        Customer customer = null;
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(customerName)) {
                customer = c;
                break;
            }
        }

        if (customer != null) {
            // Check if the customer has an existing reservation
            Reservation existingReservation = null;
            for (Reservation reservation : reservations) {
                if (reservation.getCustomer() == customer) {
                    existingReservation = reservation;
                    break;
                }
            }

            if (existingReservation != null) {
                Bus bus = existingReservation.getBus();
                int newSeatNumber = findAvailableSeat(bus);

                if (newSeatNumber != -1) {
                    Reservation newReservation = new Reservation(customer, bus, newSeatNumber);
                    reservations.add(newReservation);
                    System.out.println("New seat assigned successfully!");
                } else {
                    System.out.println("No available seats on the bus.");
                }
            } else {
                System.out.println("No existing reservation found for this customer.");
            }
        } else {
            System.out.println("Customer not found. Please register the customer first.");
        }
    }

    private int findAvailableSeat(Bus bus) {
        // Implement logic to find an available seat on the specified bus
        // You may need to keep track of occupied seats and return an available seat number
        int totalSeats = bus.getTotalSeats();
        if (reservations.size() < totalSeats) {
            // Find the first available seat number
            for (int seatNumber = 1; seatNumber <= totalSeats; seatNumber++) {
                boolean seatOccupied = false;
                for (Reservation reservation : reservations) {
                    if (reservation.getBus() == bus && reservation.getSeatNumber() == seatNumber) {
                        seatOccupied = true;
                        break;
                    }
                }
                if (!seatOccupied) {
                    return seatNumber;
                }
            }
        }
        return -1; // No available seats
    }

    public void displayReservations() {
        System.out.println("Current Reservations:");
        for (Reservation reservation : reservations) {
            Customer customer = reservation.getCustomer();
            Bus bus = reservation.getBus();
            int seatNumber = reservation.getSeatNumber();

            System.out.println("Customer: " + customer.getName());
            System.out.println("Bus Number: " + bus.getBusNumber());
            System.out.println("Seat Number: " + seatNumber);
            System.out.println();
        }
    }
}
