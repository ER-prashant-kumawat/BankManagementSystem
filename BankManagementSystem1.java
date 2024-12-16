import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankManagementSystem1 {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Map<String, Account>> banks = new HashMap<>(); // Bank-wise account storage
    static String selectedBank = null;

    public static void main(String[] args) {
        // Initialize banks
        banks.put("SBI", new HashMap<>());
        banks.put("PNB", new HashMap<>());
        banks.put("Kotak", new HashMap<>());

        System.out.println("Welcome to the Multi-Bank Management System!");

        while (true) {
            try {
                System.out.println("\nSelect a bank:");
                System.out.println("1. SBI\n2. PNB\n3. Kotak\n4. Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        selectedBank = "SBI";
                        bankMenu();
                        break;
                    case 2:
                        selectedBank = "PNB";
                        bankMenu();
                        break;
                    case 3:
                        selectedBank = "Kotak";
                        bankMenu();
                        break;
                    case 4:
                        System.out.println("Thank you for using the system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (1, 2, 3, or 4).");
            }
        }
    }

    static void bankMenu() {
        while (true) {
            try {
                System.out.println("\nBank: " + selectedBank);
                System.out.println("1. Register\n2. Login\n3. Back to Bank Selection");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        register();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        return; // Go back to bank selection
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (1, 2, or 3).");
            }
        }
    }

    static void register() {
        Map<String, Account> accounts = banks.get(selectedBank);

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (accounts.containsKey(username)) {
            System.out.println("Username already exists in " + selectedBank + ". Please try a different username.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        accounts.put(username, new Account(username, password));
        System.out.println("Registration successful in " + selectedBank + "! You can now log in.");
    }

    static void login() {
        Map<String, Account> accounts = banks.get(selectedBank);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        if (!accounts.containsKey(username)) {
            System.out.println("Account not found in " + selectedBank + ". Please register first.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Account account = accounts.get(username);
        if (!account.password.equals(password)) {
            System.out.println("Incorrect password. Please try again.");
            return;
        }

        System.out.println("Login successful! Welcome, " + username + " to " + selectedBank + ".");
        userMenu(account);
    }

    static void userMenu(Account account) {
        while (true) {
            try {
                System.out.println("\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Logout");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        deposit(account);
                        break;
                    case 2:
                        withdraw(account);
                        break;
                    case 3:
                        checkBalance(account);
                        break;
                    case 4:
                        System.out.println("Logged out successfully.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (1, 2, 3, or 4).");
            }
        }
    }

    static void deposit(Account account) {
        try {
            System.out.print("Enter amount to deposit: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount > 0) {
                account.balance += amount;
                System.out.println("Deposit successful! New balance: " + account.balance);
            } else {
                System.out.println("Invalid amount. Please enter a positive number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    static void withdraw(Account account) {
        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount > 0 && amount <= account.balance) {
                account.balance -= amount;
                System.out.println("Withdrawal successful! New balance: " + account.balance);
            } else {
                System.out.println("Invalid amount or insufficient balance. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    static void checkBalance(Account account) {
        System.out.println("Your current balance is: " + account.balance);
    }
}

class Account {
    String username;
    String password;
    double balance;

    Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }
}
