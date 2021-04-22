package org.fis.ta.exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("All fields must be completed!");

    }
}
