package org.fis.ta.exceptions;

public class PriceNotValidException extends Exception {
    public PriceNotValidException() {
        super("Please enter a valid price!");
    }
}