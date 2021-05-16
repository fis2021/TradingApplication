package org.fis.ta.exceptions;

public class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException(){
        super("Username or password do not exist!");
    }
}
