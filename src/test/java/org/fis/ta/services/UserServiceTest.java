package org.fis.ta.services;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.fis.ta.exceptions.*;
import org.fis.ta.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email@mail.com";
    public static final String PHONE_NUMBER = "+403232321";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-trading-application";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.getDataBase().close();
    }

    @Test
    @DisplayName("Database is initialized and empty")
    void testDatabaseIsInitialized() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException, PhoneNumberNotValidException, EmailNotValidException, EmptyFieldException, PasswordDoesntMatchException {
        UserService.addUser(USERNAME, PASSWORD, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo(USERNAME);
        Assertions.assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(USERNAME, PASSWORD));
        Assertions.assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
        Assertions.assertThat(user.getLastName()).isEqualTo(LAST_NAME);
        Assertions.assertThat(user.getEmail()).isEqualTo(EMAIL);
        Assertions.assertThat(user.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
    }

    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(USERNAME, PASSWORD, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER);
            UserService.addUser(USERNAME, PASSWORD, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER);
        });
    }

    @Test
    @DisplayName("Testing Login credentials")
    void testLoginCredentials() throws PhoneNumberNotValidException, UsernameAlreadyExistsException, EmailNotValidException, EmptyFieldException, PasswordDoesntMatchException {
        UserService.addUser(USERNAME, PASSWORD, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER);
        assertThat(UserService.checkLoginCredentials(USERNAME,PASSWORD)).isTrue();
    }

    @Test
    @DisplayName("All fields must be completed")
    void testNotEmptyFields() {
        assertThrows(EmptyFieldException.class, () -> {
            UserService.addUser(USERNAME, PASSWORD, PASSWORD, "", LAST_NAME, EMAIL, PHONE_NUMBER);
        });
    }

    @Test
    @DisplayName("Password must be the same with Confirmed Password")
    void testPasswordMatchWithConfirmedPassword() throws EmptyFieldException {
        assertThrows(PasswordDoesntMatchException.class, () -> {
            UserService.addUser(USERNAME, PASSWORD, PASSWORD + "1", FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER);
        });
    }

    @Test
    @DisplayName("Email must look like a real email address(e.g. example@mail.com)")
    void testEmailIsValid() {
        assertThrows(EmailNotValidException.class, () -> {
            UserService.addUser(USERNAME, PASSWORD, PASSWORD, FIRST_NAME, LAST_NAME, "email.com", PHONE_NUMBER);
        });
    }

    @Test
    @DisplayName("Phone number must look like a real phone number with + and must have between 6-14 digits(e.g +407238131)")
    void testPhoneNumberIsValid() {
        assertThrows(PhoneNumberNotValidException.class, () -> {
            UserService.addUser(USERNAME, PASSWORD, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, "0737261");
        });
    }
}