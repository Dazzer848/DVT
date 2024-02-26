/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author darrenl
 */
public class Checks {

    public boolean emailValdity(String email) {
        // The checks required for the email
        boolean hasASpecial = false;
        boolean hasADot = false;

        //The location of the @ Symbol to prevent issues involving emails such as darren.larkens@reddam.house where the email could still be invalid as there could still be
        //an inadaquate refferance email. Eg darren.larkens@gmail
        int locationOfSpecial = -1;

        //Checks if the email is present in the field
        if (email.length() <= 0) {
            return false;
        }

        //Checking email for presence and location of @ symbol
        for (int i = 0; i < email.length(); i++) {
            char checkingCharacter = email.charAt(i);

            //Sets the check to true and stores location of @ symbol
            if (checkingCharacter == '@') {
                hasASpecial = true;
                locationOfSpecial = i;
                break;
            }

        }

        //Final check to completly erradicate any possiblities of infinite loops or indexing errors
        if (locationOfSpecial > 0) {

            //Loops through the string PAST the point of the @ symbol to ensure a valid email
            for (int f = locationOfSpecial; f < email.length(); f++) {
                char CheckingCharacter = email.charAt(f);

                //Checks for the dot
                if (CheckingCharacter == '.') {
                    hasADot = true;
                    break;
                }
            }
        }

        //Combines both validity fields to ensure the email is valid
        //Returns true if both are met and false if they are not
        if (hasASpecial = true && hasADot == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cardDateValidity(LocalDateTime cardExperationDate) {
        boolean isCorrectCardDate = true;

        if (cardExperationDate.isAfter(cardExperationDate.plusYears(3))) {
            isCorrectCardDate = false;
        }

        return isCorrectCardDate;

    }

    public boolean cardNumberCheck(String cardNumber) {
        boolean correctLenght = false;
        boolean noIllegalCharactersPresent = true;
        boolean cardNumberValid = false;

        if (cardNumber.length() == 19) {
            correctLenght = true;
        } else {
            return false;
        }

        for (int i = 0; i <= cardNumber.length(); i++) {

            char checkDigit = cardNumber.charAt(i);
            if (!(Character.isDigit(checkDigit))) {
                noIllegalCharactersPresent = false;

            }
        }

        if (correctLenght == true && noIllegalCharactersPresent == true) {
            cardNumberValid = true;
            return cardNumberValid;
        }
        return cardNumberValid;
    }

    public void printReciept(int priceOfItem, int ISBNofItem) {
        System.out.println("-------------- Recpiept ------------- \nPrice Of Item: R" + priceOfItem + "\nISBN of Item: " + ISBNofItem + "\n\n---------------------- THANK YOU FOR YOUR PURCHASE! --------------------");
    }

    public boolean lengthCheck(int length, String input) {
        boolean isCorrectLength = true;

        if (input.length() > length || input.length() < length) {
            isCorrectLength = false;
        }

        return isCorrectLength;
    }

    public boolean priceRangeCheck(int maxPrice, String input) {
        if (input.length() > maxPrice) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidISBN(int inputISBN) {
        String isbnString = String.valueOf(inputISBN);

        // Remove any hyphens
        isbnString = isbnString.replace("-", "");

        // Check if the length is 10
        if (isbnString.length() != 10) {
            return false;
        }

        // Check if the first 9 characters are digits
        for (int i = 0; i < 9; i++) {
            if (!Character.isDigit(isbnString.charAt(i))) {
                return false;
            }
        }

        // Calculate the weighted sum
        int weightedSum = 0;
        for (int i = 0; i < 9; i++) {
            weightedSum += (i + 1) * Character.getNumericValue(isbnString.charAt(i));
        }

        // Check if the last character is a digit or 'X'
        char lastChar = isbnString.charAt(9);
        if (!Character.isDigit(lastChar) && lastChar != 'X') {
            return false;
        }

        // For 'X', its value is 10
        int lastDigit = (lastChar == 'X') ? 10 : Character.getNumericValue(lastChar);

        // Check if the sum modulo 11 equals the last digit
        return (weightedSum + lastDigit) % 11 == 0;
    }
}
