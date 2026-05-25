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

public class MessageTest {

    Message msg = new Message();

    @Test
    public void testMessageLengthSuccess() {

        String result = msg.checkMessage(
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message ready to send.",
                result
        );
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        String result = msg.checkMessage(longMessage);

        assertTrue(
                result.contains("Message exceeds 250 characters")
        );
    }

    @Test
    public void testRecipientCorrect() {

        String result =
                msg.checkRecipientCell("+27718693002");

        assertEquals(
                "Cell phone number successfully captured.",
                result
        );
    }

    @Test
    public void testRecipientIncorrect() {

        String result =
                msg.checkRecipientCell("08575975889");

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                result
        );
    }

    @Test
    public void testMessageHashCorrect() {

        String result = msg.createMessageHash(
                "0012345678",
                0,
                "Hi tonight"
        );

        assertEquals(
                "00:0:HITONIGHT",
                result
        );
    }

    @Test
    public void testSendMessageOption() {

        String result = msg.sentMessage(1);

        assertEquals(
                "Message successfully sent.",
                result
        );
    }

    @Test
    public void testDisregardMessageOption() {

        String result = msg.sentMessage(2);

        assertEquals(
                "Press 0 to delete message.",
                result
        );
    }

    @Test
    public void testStoreMessageOption() {

        String result = msg.sentMessage(3);

        assertEquals(
                "Message successfully stored.",
                result
        );
    }
}
