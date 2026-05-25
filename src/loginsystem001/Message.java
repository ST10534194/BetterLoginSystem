/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem001;

/**
 *
 * @author Student
 */
import java.util.Random;

public class Message {

    private static int totalMessages = 0;

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Generate random 10 digit ID
    public String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // Check message length
    public String checkMessage(String message) {

        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please enter a message less than 250 characters. ";
        }
    }

    // Check recipient number
    public String checkRecipientCell(String recipient) {

        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Create message hash
    public String createMessageHash(String messageID, int messageNumber, String message) {

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String hash = messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord.toUpperCase()
                + lastWord.toUpperCase();

        return hash;
    }

    // Send/store/disregard
    public String sentMessage(int option) {

        if (option == 1) {
            totalMessages++;
            return "Message successfully sent.";
        } else if (option == 2) {
            return "Press 0 to delete message.";
        } else if (option == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // Print message details
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    // Return total messages
    public int returnTotalMessages() {
        return totalMessages;
    }

    // Set message details
    public void setMessageDetails(String recipient, String messageText, int messageNumber) {

        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash(messageID, messageNumber, messageText);
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }
}