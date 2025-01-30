import java.util.*;

class User {
    String username;
    String password;
    String email;

    User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

class Train {
    String trainName;
    String destination;
    int seatsAvailable;

    Train(String trainName, String destination, int seatsAvailable) {
        this.trainName = trainName;
        this.destination = destination;
        this.seatsAvailable = seatsAvailable;
    }
}

public class TrainBookingSystem {
    private static Map<String, User> users = new HashMap<>();
    private static List<Train> trains = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String loggedInUser = null;

    public static void main(String[] args) {
        initializeTrains();
        while (true) {
            System.out.println("\nWelcome to Train Booking System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> forgotPassword();
                case 4 -> {
                    System.out.println("Thank you for using the Train Booking System!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeTrains() {
        trains.add(new Train("Express 1", "City A", 100));
        trains.add(new Train("Express 2", "City A", 100));
        trains.add(new Train("Express 3", "City A", 100));
        trains.add(new Train("Express 4", "City A", 100));
        trains.add(new Train("Express 5", "City A", 100));
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please try a different one.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        users.put(username, new User(username, password, email));
        System.out.println("Registration successful. You can now log in.");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            loggedInUser = username;
            System.out.println("Login successful! Welcome, " + username);
            bookTrain();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void forgotPassword() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = users.get(username);
        if (user != null) {
            System.out.println("Your registered email is: " + user.email);
            System.out.println("Please contact support to reset your password.");
        } else {
            System.out.println("Username not found.");
        }
    }

    private static void bookTrain() {
        System.out.println("\nAvailable trains:");
        for (int i = 0; i < trains.size(); i++) {
            Train train = trains.get(i);
            System.out.printf("%d. %s to %s (%d seats available)%n", i + 1, train.trainName, train.destination, train.seatsAvailable);
        }

        System.out.print("Enter the train number you want to book: ");
        int trainChoice = scanner.nextInt();
        scanner.nextLine();

        if (trainChoice < 1 || trainChoice > trains.size()) {
            System.out.println("Invalid train number.");
            return;
        }

        Train selectedTrain = trains.get(trainChoice - 1);
        if (selectedTrain.seatsAvailable > 0) {
            selectedTrain.seatsAvailable--;
            System.out.println("Booking successful! You have booked a seat on " + selectedTrain.trainName);
        } else {
            System.out.println("Seats not available on this train. Here are the alternative options:");
            for (Train train : trains) {
                if (!train.trainName.equals(selectedTrain.trainName) && train.seatsAvailable > 0) {
                    System.out.printf("%s to %s (%d seats available)%n", train.trainName, train.destination, train.seatsAvailable);
                }
            }
        }
    }
}