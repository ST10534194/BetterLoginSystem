/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package TestClasses;

import loginsystem001.Login;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class TestLoginSystem {

    Login login = new Login();

    @Test
    public void testUsernameCorrect() {
        boolean result = login.checkUserName("kyl_1");
        assertTrue(result);
    }

    @Test
    public void testUsernameIncorrect() {
        boolean result = login.checkUserName("kyle!!!!!!");
        assertFalse(result);
    }

    @Test
    public void testPasswordCorrect() {
        boolean result = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void testPasswordIncorrect() {
        boolean result = login.checkPasswordComplexity("password");
        assertFalse(result);
    }

    @Test
    public void testCellCorrect() {
        boolean result = login.checkCellPhoneNumber("+27838968976");
        assertTrue(result);
    }

    @Test
    public void testCellIncorrect() {
        boolean result = login.checkCellPhoneNumber("08966553");
        assertFalse(result);
    }

    @Test
    public void testRegisterSuccessMessage() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Registration successful.", result);
    }

    @Test
    public void testRegisterUsernameFailMessage() {
        String result = login.registerUser("wrongname", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testLoginSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void testLoginFail() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("wrong", "wrong");
        assertFalse(result);
    }
}

