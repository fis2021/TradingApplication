package org.fis.ta.services;


import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.controllers.LoginController;
import org.fis.ta.exceptions.EmptyFieldException;
import org.fis.ta.exceptions.NoFileSelectedException;
import org.fis.ta.exceptions.PriceNotValidException;
import org.fis.ta.model.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import static org.fis.ta.services.FileSystemService.getPathToFile;

public class ItemService {

    private static ObjectRepository<Item> itemRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("trading-application-items.db").toFile())
                .openOrCreate("test", "test");
        itemRepository = database.getRepository(Item.class);
        int count = 0;
        for (Item item : itemRepository.find()) {
            count++;
        }
        Item.setCount(count);
    }

    public static ArrayList<Item> loadItemList(){
        ArrayList<Item> list = new ArrayList<>();
        for(Item item:itemRepository.find()){
            if(UserService.getCurrentUser(LoginController.getUsername()).getUsername().equals(item.getOwner()) & !item.isSold()){
                list.add(item);
            }
        }
        return list;
    }

    public static void addItem(String owner, String name, String category, String description, ArrayList<String> images, String price) throws PriceNotValidException, EmptyFieldException, NoFileSelectedException{
        checkNotEmptyFields(name);
        checkNotEmptyFields(description);
        checkNotEmptyFields(price);
        checkPrice(price);
        checkIfImageInserted(images);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(calendar.getTime());
        Item item = new Item(owner,name,category,description,images,price,date);
        itemRepository.insert(item);
    }
    public static void deleteItem(Item item){
        itemRepository.remove(item);
    }

    public static String buyItem(Item item, String delivery, String country, String city, String street, String houseNumber, Boolean fastDelivery, String newOwner)throws  EmptyFieldException{
        checkNotEmptyFields(delivery);
        checkNotEmptyFields(country);
        checkNotEmptyFields(city);
        checkNotEmptyFields(street);
        checkNotEmptyFields(houseNumber);

        item.setSold(true);
        item.setNewOwner(newOwner);
        itemRepository.update(item);
        if(item.getCategory().equals("Real estates")){
            return "Item bought!";
        }
        if(item.getCategory().equals("Cars, motorcycles and boats")){
            return "Item bought!";
        }
        int days = 7;
        if(delivery.equals("Courier delivery")){
            if(fastDelivery){
                days = 1;
            } else{
                days = 2;
            }
        }
        if(days == 1){
            return "Item will arrive to you in 1 day after it's current owner send it";
        }else {
            return "Item will arrive to you in " + days + "days after it's current owner send it";
        }
    }

    private static void checkPrice(String price)throws PriceNotValidException {
        String priceRegex ="\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";

        Pattern pat = Pattern.compile(priceRegex);

        if (!pat.matcher(price).matches())
            throw new PriceNotValidException();
    }

    public static void checkNotEmptyFields(String field) throws EmptyFieldException {
        if(field.isEmpty()  )
            throw new EmptyFieldException();
    }

    public static void checkIfImageInserted(ArrayList<String> images) throws NoFileSelectedException {
        if(images.isEmpty()){
            throw new NoFileSelectedException();
        }
    }

    public static Item getCurrentItem(int ID){
        Item aux = new Item();
        for(Item item:itemRepository.find()){
            if(ID == item.getID())
            {
                aux=item;
            }
        }
        return aux;
    }

    public static ObjectRepository<Item> getItemRepository(){
        return itemRepository;
    }


}
