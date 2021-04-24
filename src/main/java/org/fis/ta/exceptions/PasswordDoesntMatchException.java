package org.fis.ta.exceptions;

public class PasswordDoesntMatchException extends Exception{

    public PasswordDoesntMatchException() {
        super("Password and Confirm Password do not match!");
    }

}
