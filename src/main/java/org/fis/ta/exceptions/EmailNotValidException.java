package org.fis.ta.exceptions;

public class EmailNotValidException extends Exception{
    public EmailNotValidException() {
        super("This is not a valid email address!");

    }
}
