/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
import loginsystem001.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    Message msg = new Message();

    @Test
    void testMessageLengthSuccess() {

        String result = msg.checkMessage(
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message ready to send.",
                result
        );
    }

    @Test
    void testMessageLengthFailure() {

        String longMessage =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        String result = msg.checkMessage(longMessage);

        assertTrue(
                result.contains("Message exceeds 250 characters")
        );
    }

    @Test
    void testRecipientSuccess() {

        String result =
                msg.checkRecipientCell("+27718693002");

        assertEquals(
                "Cell phone number successfully captured.",
                result
        );
    }

    @Test
    void testRecipientFailure() {

        String result =
                msg.checkRecipientCell("08575975889");

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                result
        );
    }

    @Test
    void testMessageHash() {

        String hash = msg.createMessageHash(
                "0012345678",
                0,
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "00:0:HITONIGHT?",
                hash
        );
    }

    @Test
    void testSendMessageOption() {

        String result = msg.sentMessage(1);

        assertEquals(
                "Message successfully sent.",
                result
        );
    }

    @Test
    void testDiscardMessageOption() {

        String result = msg.sentMessage(2);

        assertEquals(
                "Press 0 to delete message.",
                result
        );
    }

    @Test
    void testStoreMessageOption() {

        String result = msg.sentMessage(3);

        assertEquals(
                "Message successfully stored.",
                result
        );
    }
}