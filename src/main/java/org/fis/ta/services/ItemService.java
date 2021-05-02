package org.fis.ta.services;


import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.exceptions.*;
import org.fis.ta.model.Item;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.fis.ta.services.FileSystemService.getPathToFile;

public class ItemService {

    private static ObjectRepository<Item> itemRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("trading-application-items.db").toFile())
                .openOrCreate("test", "test");

        itemRepository = database.getRepository(Item.class);
    }

    public static void addItem(String owner, String name, String category, String description, ArrayList<String> images, String price) throws PriceNotValidException, EmptyFieldException, NoFileSelectedException{
        checkNotEmptyFields(name, category, description, price);
        checkPrice(price);
        checkIfImageInserted(images);
        itemRepository.insert(new Item(owner, name, category, description, images, price));
    }

    private static void checkPrice(String price)throws PriceNotValidException {
        String priceRegex ="\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";

        Pattern pat = Pattern.compile(priceRegex);

        if (!pat.matcher(price).matches())
            throw new PriceNotValidException();
    }

    public static void checkNotEmptyFields(String name, String category, String description, String price) throws EmptyFieldException {
        if(name.isEmpty() | category.isEmpty() | description.isEmpty() | price.isEmpty() )
            throw new EmptyFieldException();
    }

    public static void checkIfImageInserted(ArrayList<String> images) throws NoFileSelectedException {
        if(images.isEmpty()){
            throw new NoFileSelectedException();
        }
    }

}
