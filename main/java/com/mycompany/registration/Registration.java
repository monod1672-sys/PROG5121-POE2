/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author baloy
 */
public class Registration {

    static int totalSentMessages = 0;

    public static void runTests() {

        Login login = new Login();

        System.out.println("\n=== RUNNING TESTS ===");

        System.out.println("Test Username Valid: "
                + (login.checkUserName("ky1_") == true));

        System.out.println("Test Username Invalid: "
                + (login.checkUserName("kyle!!!!!") == false));

        System.out.println("Test Password Valid: "
                + (login.checkPassword("Ch@8sec@ke99!") == true));

        System.out.println("Test Password Invalid: "
                + (login.checkPassword("password") == false));

        System.out.println("Test Phone Valid: "
                + (login.checkPhone("+27838968976") == true));

        System.out.println("Test Phone Invalid: "
                + (login.checkPhone("0838968976") == false));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Login user = new Login();

        int choice;

        do {

            System.out.println("\n=== MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Send Message");
            System.out.println("4. Exit");

            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    user.register(sc);
                    break;

                case 2:
                    user.login(sc);
                    break;

                case 3:
                    user.sendMessage(sc);

                    totalSentMessages++;

                    String jsonContent =
                            "{\"message\":\"Sample Message\"}";

                    saveMessageToJson(jsonContent);

                    break;

                case 4:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        System.out.println("\nTotal messages sent in this session: "
                + totalSentMessages);

        sc.close();
    }

    private static void saveMessageToJson(String jsonContent) {

        try (FileWriter writer =
                     new FileWriter("messages.json", true)) {

            writer.write(jsonContent + "\n");

            System.out.println("Message successfully stored.");

        } catch (IOException e) {

            System.out.println("Error storing JSON: "
                    + e.getMessage());
        }
    }
}