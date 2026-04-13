/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
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

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter SA cell number (+27...): ");
        String cell = input.nextLine();

        String result = login.registerUser(username, password, cell);
        System.out.println(result);

        if (result.equals("Registration successful.")) {

            System.out.println("\n=== Login ===");

            System.out.print("Enter username: ");
            String user = input.nextLine();

            System.out.print("Enter password: ");
            String pass = input.nextLine();

            boolean success = login.loginUser(user, pass);

            String message = login.returnLoginStatus(success, "John", "Doe");
            System.out.println(message);
        }

        input.close();
    }
}
