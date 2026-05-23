/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author baloy
 */


import java.util.Random;
import java.util.Scanner;

public class Message {

    private String messageID;

    private String messageHash;

    private String recipient;

    private String message;

    // Constructor
    public Message(String recipient,
                   String message,
                   int messageNumber) {

        this.messageID = generateMessageID();

        this.recipient = recipient;

        this.message = message;

        this.messageHash =
                createMessageHash(
                        this.messageID,
                        this.message,
                        messageNumber);
    }

    // Generate Message ID
    public String generateMessageID() {

        Random random = new Random();

        long number =
                1000000000L
                + (long) (random.nextDouble()
                * 9000000000L);

        return String.valueOf(number);
    }

    // Check Message ID
    public boolean checkMessageID(String id) {

        return id.length() <= 10;
    }

    // Check recipient number
    public boolean checkRecipientCell(String recipient) {

        return recipient.startsWith("+")
                && recipient.length() <= 13;
    }

    // Create message hash
    public String createMessageHash(String id,
                                    String message,
                                    int num) {

        String[] words =
                message.trim().split(" ");

        String firstWord =
                words[0].toUpperCase();

        String lastWord =
                words[words.length - 1].toUpperCase();

        String firstTwo =
                id.substring(0, 2);

        return firstTwo
                + ":"
                + num
                + ":"
                + firstWord
                + lastWord;
    }

    // Display message details
    public void displayMessage() {

        System.out.println(
                "\n=== MESSAGE DETAILS ===");

        System.out.println(
                "Message ID: "
                        + messageID);

        System.out.println(
                "Message Hash: "
                        + messageHash);

        System.out.println(
                "Recipient: "
                        + recipient);

        System.out.println(
                "Message: "
                        + message);
    }

    // Message options menu
    public void messageMenu(Scanner sc) {

        System.out.println("\nChoose option:");

        System.out.println("1) Send Message");

        System.out.println("2) Show recent message");

        System.out.println("3) Exit");

        System.out.print("Choose: ");

        int option = sc.nextInt();

        sc.nextLine();

        switch (option) {

            case 1:

                System.out.println(
                        "\nMessage successfully sent.");

                displayMessage();

                break;

            case 2:

                System.out.println(
                        "\nRecent message:");

                displayMessage();

                break;

            case 3:

                System.out.println(
                        "\nExiting menu.");

                break;

            default:

                System.out.println(
                        "\nInvalid option.");
        }
    }

    // Store message in JSON format
    public String storeMessage() {

        return "{\n" +
               "  \"messageID\": \"" + messageID + "\",\n" +
               "  \"recipient\": \"" + recipient + "\",\n" +
               "  \"message\": \"" + message + "\",\n" +
               "  \"messageHash\": \"" + messageHash + "\"\n" +
               "}";
    }

    // Getters
    public String getMessageID() {

        return messageID;
    }

    public String getRecipient() {

        return recipient;
    }

    public String getMessage() {

        return message;
    }
}