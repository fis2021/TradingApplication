package org.fis.ta.services;


import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.exceptions.PriceNotValidException;
import org.fis.ta.model.Item;

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

    public static void addItem(String owner, String name,  String price, String category) throws PriceNotValidException{
        checkPrice(price);
        itemRepository.insert(new Item(owner, name, price, category));
    }

    private static void checkPrice(String price)throws PriceNotValidException {
        String priceRegex ="^\\+(?:[0-9] ?){6,14}[0-9]$";

        Pattern pat = Pattern.compile(priceRegex);

        if (!pat.matcher(price).matches())
            throw new PriceNotValidException();
    }

}
