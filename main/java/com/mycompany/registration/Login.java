/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author baloy
 */
import java.util.Scanner;

class Login {

    private String storedUsername;

    private String storedPassword;

    private String storedPhone;

    private boolean loggedIn = false;

    // Username validation
    public boolean checkUserName(String username) {

        return username.contains("_")
                && username.length() <= 5;
    }

    // Password validation
    public boolean checkPassword(String password) {

        if (password.length() < 8) {

            return false;
        }

        boolean hasUpper = false;

        boolean hasNumber = false;

        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {

                hasUpper = true;

            } else if (Character.isDigit(ch)) {

                hasNumber = true;

            } else if (!Character.isLetterOrDigit(ch)) {

                hasSpecial = true;
            }
        }

        return hasUpper
                && hasNumber
                && hasSpecial;
    }

    // Phone validation
    public boolean checkPhone(String phone) {

        if (!phone.startsWith("+27")) {

            return false;
        }

        if (phone.length() != 12) {

            return false;
        }

        for (int i = 3; i < phone.length(); i++) {

            if (!Character.isDigit(phone.charAt(i))) {

                return false;
            }
        }

        return true;
    }

    // Register user
    public void register(Scanner sc) {

        System.out.println("\n=== REGISTER ===");

        System.out.print("Enter username: ");

        String username = sc.nextLine();

        if (checkUserName(username)) {

            storedUsername = username;

            System.out.println(
                    "Username successfully captured.");

        } else {

            System.out.println(
                    "Username is not correctly formatted.");

            return;
        }

        System.out.print("Enter password: ");

        String password = sc.nextLine();

        if (checkPassword(password)) {

            storedPassword = password;

            System.out.println(
                    "Password successfully captured.");

        } else {

            System.out.println(
                    "Password is not correctly formatted.");

            return;
        }

        System.out.print("Enter phone number (+27...): ");

        String phone = sc.nextLine();

        if (checkPhone(phone)) {

            storedPhone = phone;

            System.out.println(
                    "Cell phone number successfully added.");

        } else {

            System.out.println(
                    "Cell phone number incorrectly formatted.");

            return;
        }

        System.out.println(
                "Registration successful!");
    }

    // Login user
    public void login(Scanner sc) {

        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter username: ");

        String username = sc.nextLine();

        System.out.print("Enter password: ");

        String password = sc.nextLine();

        if (username.equals(storedUsername)
                && password.equals(storedPassword)) {

            loggedIn = true;

            System.out.println(
                    "Welcome back! Login successful.");

        } else {

            loggedIn = false;

            System.out.println(
                    "Username or password incorrect.");
        }
    }

    // Send Message
    public void sendMessage(Scanner sc) {

        if (!loggedIn) {

            System.out.println(
                    "You must login first.");

            return;
        }

        System.out.println(
                "\n=== SEND MESSAGE ===");

        System.out.print(
                "Enter recipient number: ");

        String recipient = sc.nextLine();

        System.out.print(
                "Enter your message: ");

        String text = sc.nextLine();

        if (text.length() > 250) {

            int extra = text.length() - 250;

            System.out.println(
                    "Message exceeds 250 characters by "
                            + extra);

            return;
        }

        Message msg =
                new Message(
                        recipient,
                        text,
                        1);

        if (!msg.checkRecipientCell(recipient)) {

            System.out.println(
                    "Cell phone number incorrectly formatted.");

            return;
        }

        msg.messageMenu(sc);
    }
}