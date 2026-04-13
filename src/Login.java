/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class Login {

    private String username;
    private String password;
    private String cellPhone;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() >= 8) {
            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) hasUpper = true;
                if (Character.isDigit(c)) hasNumber = true;
                if (!Character.isLetterOrDigit(c)) hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellPhone) {
        return cellPhone.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username, String password, String cellPhone) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        return "Registration successful.";
    }

    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
