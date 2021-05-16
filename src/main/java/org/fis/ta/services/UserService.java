package org.fis.ta.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.exceptions.*;
import org.fis.ta.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.fis.ta.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static Nitrite database;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("trading-application-users.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
        
    }

    public static Nitrite getDataBase(){
        return database;
    }


    public static void addUser(String username, String password, String confirmPassword, String firstName, String lastName, String email, String phoneNumber) throws UsernameAlreadyExistsException, PasswordDoesntMatchException, EmailNotValidException, EmptyFieldException, PhoneNumberNotValidException {
        checkNotEmptyFields(username, password, confirmPassword, firstName, lastName, email, phoneNumber);
        checkUserDoesNotAlreadyExist(username);
        checkPasswordMatch(password, confirmPassword);
        checkEmailIsValid(email);
        checkPhoneNumber(phoneNumber);
        userRepository.insert(new User(username, encodePassword(username, password), firstName, lastName, email, phoneNumber));
    }

    public static List<User> getAllUsers() {
        return userRepository.find().toList();
    }

    public static boolean checkLoginCredentials(String username, String password){
        for(User user : userRepository.find()){
            if(Objects.equals(username,user.getUsername())){
                if(Objects.equals(user.getPassword(),encodePassword(user.getUsername(),password))){
                    return true;
                }
            }
        }
        return false;
    }
    public static void checkNotEmptyFields(String username, String password, String confirmPassword, String firstName, String lastName, String email, String phoneNumber) throws EmptyFieldException{
        if(username.isEmpty() | password.isEmpty() | confirmPassword.isEmpty() | firstName.isEmpty() | lastName.isEmpty() | email.isEmpty() | phoneNumber.isEmpty())
            throw new EmptyFieldException();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkPasswordMatch(String password, String confirmPassword) throws PasswordDoesntMatchException {
        if(!password.equals(confirmPassword))
            throw new PasswordDoesntMatchException();
    }

    private static void checkEmailIsValid(String email) throws EmailNotValidException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
            if (!pat.matcher(email).matches())
                throw new EmailNotValidException();
    }

    private static void checkPhoneNumber(String phoneNumber)throws PhoneNumberNotValidException {
        String phoneNumberRegex ="^\\+(?:[0-9] ?){6,14}[0-9]$";

        Pattern pat = Pattern.compile(phoneNumberRegex);

        if (!pat.matcher(phoneNumber).matches())
            throw new PhoneNumberNotValidException();
    }



    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static User getCurrentUser(String username){
        User aux = new User();
        for(User user: userRepository.find()){
            if (Objects.equals(username, user.getUsername())){
                aux=user;
            }
        }
        return aux;
    }

    public static ObjectRepository<User> getUserRepository(){
        return userRepository;
    }

}
