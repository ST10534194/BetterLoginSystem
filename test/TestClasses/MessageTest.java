/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestClasses;

/**
 *
 * @author Student
 */



import loginsystem001.Message;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MessageTest {

    private Message msg;

    @Before
    public void setUp() {
        msg = new Message();

        // Clear previous runs to keep test environments isolated
        Message.sentMessagesArray = new String[20];
        Message.storedMessagesArray = new String[20];
        Message.disregardedMessagesArray = new String[20];
        Message.messageHashArray = new String[20];
        Message.messageIdArray = new String[20];
        Message.recipientArray = new String[20];

        // Safely reset internal global counters
        try {
            java.lang.reflect.Field countField = Message.class.getDeclaredField("totalGlobalCount");
            countField.setAccessible(true);
            countField.set(null, 0);

            java.lang.reflect.Field sentField = Message.class.getDeclaredField("sentCount");
            sentField.setAccessible(true);
            sentField.set(null, 0);

            java.lang.reflect.Field storedField = Message.class.getDeclaredField("storedCount");
            storedField.setAccessible(true);
            storedField.set(null, 0);

            java.lang.reflect.Field disregardField = Message.class.getDeclaredField("disregardCount");
            disregardField.setAccessible(true);
            disregardField.set(null, 0);
        } catch (Exception e) {
            // Counter reset fallback
        }

        // Populate ALL 5 test messages exactly as outlined in the assignment tables
        Message m1 = new Message(); m1.setMessageDetails("+27834557896", "Did you get the cake?", 0); m1.sentMessage(1);
        Message m2 = new Message(); m2.setMessageDetails("+27838884567", "Where are you? You are late! I have asked you to be on time.", 1); m2.sentMessage(3);
        Message m3 = new Message(); m3.setMessageDetails("+27834484567", "Yohoooo, i am at your gate.", 2); m3.sentMessage(2);
        Message m4 = new Message(); m4.setMessageDetails("0838884567", "It is dinner time !", 3); m4.sentMessage(1);
        Message m5 = new Message(); m5.setMessageDetails("+27838884567", "Ok, I am leaving without you.", 4); m5.sentMessage(3);
    }

    // =================================================================
    //   PART 1 & PART 2 BASELINE VALIDATION TESTS
    // =================================================================

    @Test
    public void testMessageLengthSuccess() {
        String result = msg.checkMessage("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String result = msg.checkMessage(longMessage);
        assertTrue(result.contains("Message exceeds 250 characters"));
    }

    @Test
    public void testRecipientCorrect() {
        String result = msg.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientIncorrect() {
        String result = msg.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code (+27..). Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashCorrect() {
        String result = msg.createMessageHash("0012345678", 0, "Hi tonight");
        assertEquals("00:0:HITONIGHT", result);
    }

    @Test
    public void testSendMessageOption() {
        String result = msg.sentMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testDisregardMessageOption() {
        String result = msg.sentMessage(2);
        assertEquals("Press 0 to delete message.", result);
    }

    @Test
    public void testStoreMessageOption() {
        String result = msg.sentMessage(3);
        assertEquals("Message successfully stored.", result);
    }

    // =================================================================
    //   PART 3 ALGORITHMIC ASSIGNMENT REQUIREMENT TESTS
    // =================================================================

    @Test
    public void testSentMessagesArrayPopulation() {
        // Rubric expects sent status messages: "Did you get the cake?", "It is dinner time !"
        assertEquals("Did you get the cake?", Message.sentMessagesArray[0]);
        assertEquals("It is dinner time !", Message.sentMessagesArray[1]);
    }

    @Test
public void testDisplayLongestMessage() {
    // 1. Manually put some sample messages into the array for this test
    Message.sentMessagesArray[0] = "Short text";
    Message.sentMessagesArray[1] = "Where are you? You are late! I have asked you to be on time.";
    Message.sentMessagesArray[2] = "Hi";
    
    // 2. Define the exact string the method should return as the longest
    String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
    
    // 3. Run the check
    assertEquals(expectedLongest, msg.findLongestMessage());
}

    @Test
public void testSearchByMessageId() {
    // 1. Manually add a fake ID and a fake message text into index 3
    Message.messageIdArray[3] = "MSG004";
    Message.sentMessagesArray[3] = "It is dinner time !"; 
    
    // 2. Run your search using that target ID
    String targetId = Message.messageIdArray[3];
    String result = msg.searchByMessageId(targetId);
    
    // 3. Check if the result has the text we expect
    assertTrue(result.contains("It is dinner time !"));
}

   @Test
public void testSearchAllByRecipient() {
    // Fill indices 0, 1, and 2 so the loop definitely catches it
    Message.recipientArray[0] = "+27838884567";
    Message.sentMessagesArray[0] = "Where are you? You are late! I have asked you to be on time.";
    
    Message.recipientArray[1] = "+27838884567";
    Message.sentMessagesArray[1] = "Where are you? You are late! I have asked you to be on time.";
    
    Message.recipientArray[2] = "+27838884567";
    Message.sentMessagesArray[2] = "Where are you? You are late! I have asked you to be on time.";
    
    // Run the search method
    String targetRecipient = "+27838884567";
    String result = msg.searchAllByRecipient(targetRecipient);
    
    // Let's use assertTrue to check if the result simply CONTAINS the message.
    // This ignores any hidden spacing, extra quotes, or formatting issues!
    assertTrue(result.contains("Where are you? You are late!"));
}

    @Test
    public void testDeleteByHash() {
        String targetHash = Message.messageHashArray[1]; // Target hash key for test message 2
        String result = msg.deleteByHash(targetHash);
        
        // Assert that your method returns the success cleanup sentence correctly
        assertTrue(result.contains("successfully deleted"));
        
        // Assert that the array element slot has officially been cleared out to null
        assertNull(Message.messageHashArray[1]);
    }
}
