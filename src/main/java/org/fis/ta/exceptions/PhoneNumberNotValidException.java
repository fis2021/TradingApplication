package org.fis.ta.exceptions;

public class PhoneNumberNotValidException extends Exception{
    public PhoneNumberNotValidException() {
        super("Please enter a valid phone number!");
    }
}
