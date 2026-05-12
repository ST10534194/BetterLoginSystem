package loginsystem001;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
import loginsystem001.Login;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Registration ===");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username (Must include _ and be max 5 chars.): ");
        String username = input.nextLine();

        System.out.print("Enter password (Min 8 chars with capital, number & special char.): ");
        String password = input.nextLine();

        System.out.print("Enter SA cell number (Must Begin With +27...): ");
        String cell = input.nextLine();

        String result = login.registerUser(username, password, cell);
        System.out.println(result);

        if (result.equals("Your Registration was successful.")) {

            System.out.println("\n=== Login ===");

            System.out.print("Enter username: ");
            String user = input.nextLine();

            System.out.print("Enter password: ");
            String pass = input.nextLine();

            boolean success = login.loginUser(user, pass);

            String message = login.returnLoginStatus(success, firstName, lastName);
            System.out.println(message);

            // PART 2 STARTS HERE

            if (success) {

                System.out.println("\nWelcome to QuickChat.");

                System.out.print("How many messages would you like to send? ");
                int numMessages = input.nextInt();
                input.nextLine();

                int choice = 0;
                int totalSent = 0;

                while (choice != 3) {

                    System.out.println("\n===== MENU =====");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");

                    System.out.print("Choose option: ");
                    choice = input.nextInt();
                    input.nextLine();

                    switch (choice) {

                        case 1:

                            for (int i = 0; i < numMessages; i++) {

                                Message msg = new Message();

                                System.out.print("Enter recipient number: ");
                                String recipient = input.nextLine();

                                System.out.println(msg.checkRecipientCell(recipient));

                                System.out.print("Enter your message: ");
                                String text = input.nextLine();

                                System.out.println(msg.checkMessage(text));

                                msg.setMessageDetails(recipient, text, i);

                                System.out.println("\n1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message");

                                System.out.print("Choose option: ");
                                int sendOption = input.nextInt();
                                input.nextLine();

                                String sendResult = msg.sentMessage(sendOption);
                                System.out.println(sendResult);

                                if (sendOption == 1) {
                                    totalSent++;
                                }

                                System.out.println("\n===== MESSAGE DETAILS =====");
                                System.out.println(msg.printMessages());

                                System.out.println("Message Hash: " + msg.getMessageHash());
                            }

                            System.out.println("\nTotal messages sent: " + totalSent);

                            break;

                        case 2:
                            System.out.println("Coming Soon.");
                            break;

                        case 3:
                            System.out.println("Goodbye.");
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }
        }

        input.close();
    }
}