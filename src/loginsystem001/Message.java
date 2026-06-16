/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem001;

/**
 *
 * @author Student
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    // --- PART 3 ADDITIONS: Global parallel tracking structures ---
    private static final int MAX_LIMIT = 20;
    
    public static String[] sentMessagesArray = new String[MAX_LIMIT];
    public static String[] disregardedMessagesArray = new String[MAX_LIMIT];
    public static String[] storedMessagesArray = new String[MAX_LIMIT];
    public static String[] messageHashArray = new String[MAX_LIMIT];
    public static String[] messageIdArray = new String[MAX_LIMIT];
    public static String[] recipientArray = new String[MAX_LIMIT]; 
    
    private static int sentCount = 0;
    private static int disregardCount = 0;
    private static int storedCount = 0;
    private static int totalGlobalCount = 0; // Tracks global parallel layout indexes

    private static int totalMessages = 0;

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;

    public String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    public String checkMessage(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please enter a message less than 250 characters. ";
        }
    }

    public String checkRecipientCell(String recipient) {
        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code (+27..). Please correct the number and try again.";
        }
    }

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

    // Research assignment requirement: Stores message contents into a JSON file structure
    public void storeMessage() {
        String jsonFormat = "{\n" +
                            "  \"messageId\": \"" + this.messageID + "\",\n" +
                            "  \"messageHash\": \"" + this.messageHash + "\",\n" +
                            "  \"recipient\": \"" + this.recipient + "\",\n" +
                            "  \"text\": \"" + this.messageText + "\"\n" +
                            "}\n";

        try (FileWriter file = new FileWriter("stored_messages.json", true)) {
            file.write(jsonFormat);
        } catch (IOException e) {
            System.out.println("Error saving message to JSON: " + e.getMessage());
        }
    }

    // PART 3 ADJUSTMENT: Automatically logs details to parallel arrays upon choosing options
    public String sentMessage(int option) {
        if (totalGlobalCount < MAX_LIMIT) {
            messageIdArray[totalGlobalCount] = this.messageID;
            messageHashArray[totalGlobalCount] = this.messageHash;
            recipientArray[totalGlobalCount] = this.recipient;
        }

        if (option == 1) {
            if (sentCount < MAX_LIMIT) {
                sentMessagesArray[sentCount++] = this.messageText;
            }
            totalGlobalCount++;
            totalMessages++;
            return "Message successfully sent.";
        } else if (option == 2) {
            if (disregardCount < MAX_LIMIT) {
                disregardedMessagesArray[disregardCount++] = this.messageText;
            }
            totalGlobalCount++;
            return "Press 0 to delete message.";
        } else if (option == 3) {
            if (storedCount < MAX_LIMIT) {
                storedMessagesArray[storedCount++] = this.messageText;
            }
            storeMessage(); // <--- Automatically triggers the JSON file generation
            totalGlobalCount++;
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    public int returnTotalMessages() {
        return totalMessages;
    }

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

    // =================================================================
    //   PART 3 ASSIGNMENT ALGORITHMIC REQUIREMENTS
    // =================================================================

    // 2b. Display the longest stored/handled message text
    public String findLongestMessage() {
        String longest = "";
        for (int i = 0; i < totalGlobalCount; i++) {
            String currentText = null;
            if (sentMessagesArray[i] != null) currentText = sentMessagesArray[i];
            else if (storedMessagesArray[i] != null) currentText = storedMessagesArray[i];
            else if (disregardedMessagesArray[i] != null) currentText = disregardedMessagesArray[i];
            
            if (currentText != null && currentText.length() > longest.length()) {
                longest = currentText;
            }
        }
        return longest.isEmpty() ? "No messages logged in system." : longest;
    }

    // 2c. Match Message ID and print structural recipient + content pairs
    public String searchByMessageId(String id) {
        for (int i = 0; i < totalGlobalCount; i++) {
            if (messageIdArray[i] != null && messageIdArray[i].equals(id)) {
                String msgText = "Disregarded / Logged out";
                if (sentMessagesArray[i] != null) msgText = sentMessagesArray[i];
                else if (storedMessagesArray[i] != null) msgText = storedMessagesArray[i];
                else if (disregardedMessagesArray[i] != null) msgText = disregardedMessagesArray[i];
                
                return "Recipient: " + recipientArray[i] + "\nMessage: \"" + msgText + "\"";
            }
        }
        return "Message ID not found.";
    }

    // 2d. Filter and match multiple message texts bound to a single recipient cell
    public String searchAllByRecipient(String targetRecipient) {
        StringBuilder results = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < totalGlobalCount; i++) {
            if (recipientArray[i] != null && recipientArray[i].equals(targetRecipient)) {
                String msgText = null;
                if (sentMessagesArray[i] != null) msgText = sentMessagesArray[i];
                else if (storedMessagesArray[i] != null) msgText = storedMessagesArray[i];
                else if (disregardedMessagesArray[i] != null) msgText = disregardedMessagesArray[i];
                
                if (msgText != null) {
                    if (found) results.append(" ");
                    results.append("\"").append(msgText).append("\"");
                    found = true;
                }
            }
        }
        return found ? results.toString() : "No history found for recipient.";
    }

    // 2e. Wipe structural fields by matching its cryptographic text layout key
    public String deleteByHash(String hash) {
        for (int i = 0; i < totalGlobalCount; i++) {
            if (messageHashArray[i] != null && messageHashArray[i].equals(hash)) {
                String targetsText = "Unknown/Empty";
                if (sentMessagesArray[i] != null) targetsText = sentMessagesArray[i];
                else if (storedMessagesArray[i] != null) targetsText = storedMessagesArray[i];
                else if (disregardedMessagesArray[i] != null) targetsText = disregardedMessagesArray[i];
                
                // Clear index links
                messageIdArray[i] = null;
                messageHashArray[i] = null;
                recipientArray[i] = null;
                sentMessagesArray[i] = null;
                storedMessagesArray[i] = null;
                disregardedMessagesArray[i] = null;
                
                return "Message: \"" + targetsText + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // 2f. Format and print a clean full report dump
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n====================== SYSTEM REPORT ======================\n");
        boolean recordsExist = false;
        for (int i = 0; i < totalGlobalCount; i++) {
            if (messageHashArray[i] != null) {
                String msgText = "Disregarded";
                if (sentMessagesArray[i] != null) msgText = sentMessagesArray[i];
                else if (storedMessagesArray[i] != null) msgText = storedMessagesArray[i];
                
                report.append("HASH: ").append(messageHashArray[i]).append("\n")
                      .append("RECIPIENT: ").append(recipientArray[i]).append("\n")
                      .append("MESSAGE: ").append(msgText).append("\n")
                      .append("-----------------------------------------------------------\n");
                recordsExist = true;
            }
        }
        return recordsExist ? report.toString() : "No active message structures saved in report layout logs.";
    }
}
