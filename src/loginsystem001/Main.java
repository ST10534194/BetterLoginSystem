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
import loginsystem001.Message;
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

            // PART 2 & PART 3 RUNTIME SCOPE STARTS HERE
            if (success) {

                System.out.println("\nWelcome to QuickChat.");

                System.out.print("How many messages would you like to send? ");
                int numMessages = input.nextInt();
                input.nextLine();

                int choice = 0;
                int totalSent = 0;

                // PART 3 UPDATE: Changes loop termination boundary to 5 to handle the expanded report suite
                while (choice != 5) {

                    System.out.println("\n===== MAIN MENU =====");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Run Reports & Management");
                    System.out.println("4) Populate Test Data Instantly");
                    System.out.println("5) Quit");

                    System.out.print("Choose option: ");
                    choice = input.nextInt();
                    input.nextLine();

                    // Instantiating a processing object to call our reporting and analytical methods
                    Message reportEngine = new Message();

                    switch (choice) {

                        case 1:
                            // Rule 5 Check: Instantly blocks the user if their limit has been met
                            if (totalSent >= numMessages) {
                                System.out.println("\nMessage limit reached.");
                                break; 
                            }

                            for (int i = totalSent; i < numMessages; i++) {

                                Message msg = new Message();

                                System.out.print("Enter recipient number (Should Start With +27): ");
                                String recipient = input.nextLine();

                                System.out.println(msg.checkRecipientCell(recipient));

                                System.out.print("Enter your message (Should Not Exceed 250 Character): ");
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

                                // Counts the completed action toward your limit boundary
                                if (sendOption == 1 || sendOption == 2 || sendOption == 3) {
                                    totalSent++;
                                }

                                System.out.println("\n===== MESSAGE DETAILS =====");
                                System.out.println(msg.printMessages());
                            }

                            System.out.println("\nTotal messages handled: " + totalSent);
                            break;

                        case 2:
                            System.out.println("Coming Soon.");
                            break;

                        case 3:
                            // --- PART 3 ASSIGNMENT INTERACTIVE REPORT SUB-MENU ---
                            System.out.println("\n===== REPORT & MANAGEMENT ENGINE =====");
                            System.out.println("a) Display all saved senders and recipients");
                            System.out.println("b) Display the longest message saved");
                            System.out.println("c) Search via Message ID");
                            System.out.println("d) Filter all logs by unique recipient");
                            System.out.println("e) Delete message using unique Hash Key");
                            System.out.println("f) Print complete system report");
                            System.out.print("Select sub-option letter (a-f): ");
                            String subChoice = input.nextLine().toLowerCase().trim();

                            switch (subChoice) {
                                case "a":
                                    System.out.println("\n--- Senders and Recipients Logs ---");
                                    for (int i = 0; i < Message.recipientArray.length; i++) {
                                        if (Message.recipientArray[i] != null) {
                                            System.out.println("Index [" + i + "] Target Recipient -> " + Message.recipientArray[i]);
                                        }
                                    }
                                    break;
                                case "b":
                                    System.out.println("\nLongest Saved Message:\n" + reportEngine.findLongestMessage());
                                    break;
                                case "c":
                                    System.out.print("Enter Message ID to search: ");
                                    String searchId = input.nextLine();
                                    System.out.println("\nSearch Result:\n" + reportEngine.searchByMessageId(searchId));
                                    break;
                                case "d":
                                    System.out.print("Enter Recipient Cell Number (+27...): ");
                                    String searchRecip = input.nextLine();
                                    System.out.println("\nMessages Linked to Recipient:\n" + reportEngine.searchAllByRecipient(searchRecip));
                                    break;
                                case "e":
                                    System.out.print("Enter custom message hash to wipe: ");
                                    String targetHash = input.nextLine();
                                    System.out.println("\nAction Output: " + reportEngine.deleteByHash(targetHash));
                                    break;
                                case "f":
                                    System.out.println(reportEngine.generateReport());
                                    break;
                                default:
                                    System.out.println("Sub-option selection invalid.");
                            }
                            break;

                        case 4:
                            // CONVENIENCE DATA-LOADER FOR TESTING ASSIGNMENT SCENARIOS
                            System.out.println("\nInjecting Mock Evaluation Datasets into Memory Structures...");
                            
                            Message m1 = new Message(); m1.setMessageDetails("+27834557896", "Did you get the cake?", 0); m1.sentMessage(1);
                            Message m2 = new Message(); m2.setMessageDetails("+27838884567", "Where are you? You are late! I have asked you to be on time.", 1); m2.sentMessage(3);
                            Message m3 = new Message(); m3.setMessageDetails("+27834484567", "Yohoooo, i am at your gate.", 2); m3.sentMessage(2);
                            Message m4 = new Message(); m4.setMessageDetails("+27831114567", "It is dinner time !", 3); m4.sentMessage(1);
                            Message m5 = new Message(); m5.setMessageDetails("+27838884567", "Ok, I am leaving without you.", 4); m5.sentMessage(3);
                            
                            System.out.println("All 5 Target Assignment Simulation Messages successfully injected!");
                            break;

                        case 5:
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
