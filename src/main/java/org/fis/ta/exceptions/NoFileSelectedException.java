package org.fis.ta.exceptions;

public class NoFileSelectedException extends Exception{
    public NoFileSelectedException() {
        super("The item must have at least 1 image!");

    }
}
