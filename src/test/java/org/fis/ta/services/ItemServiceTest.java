package org.fis.ta.services;

import org.apache.commons.io.FileUtils;
import org.fis.ta.exceptions.EmptyFieldException;
import org.fis.ta.exceptions.NoFileSelectedException;
import org.fis.ta.exceptions.PriceNotValidException;
import org.fis.ta.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

class ItemServiceTest {

    public static final String OWNER = "owner";
    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String DESCRIPTION = "description";
    public static final ArrayList<String> IMAGES = ItemService.getFakeList();
    public static final String PRICE = "120.00";

    @BeforeEach
    void setUp()throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".testItemService";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ItemService.initDatabase();
    }

    @AfterEach
    void tearDown(){
        ItemService.getDataBase().close();
    }



    @Test
    @DisplayName("Database is initialised and empty")
    void testDatabaseIsInitialised(){
        assertThat(ItemService.getAllItems()).isNotNull();
        assertThat(ItemService.getAllItems()).isEmpty();
    }

    @Test
    @DisplayName("Item is successfully added in the database")
    void testItemIsAddedInDatabase() throws EmptyFieldException, NoFileSelectedException, PriceNotValidException {
        ItemService.addItem(OWNER,NAME,CATEGORY,DESCRIPTION,IMAGES,PRICE);
        assertThat(ItemService.getAllItems()).isNotEmpty();
        assertThat(ItemService.getAllItems()).size().isEqualTo(1);
        Item item = ItemService.getAllItems().get(0);
        assertThat(item).isNotNull();
        assertThat(item.getOwner()).isEqualTo(OWNER);
        assertThat(item.getName()).isEqualTo(NAME);
        assertThat(item.getCategory()).isEqualTo(CATEGORY);
        assertThat(item.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(item.getImages()).isEqualTo(IMAGES);
        assertThat(item.getPrice()).isEqualTo(PRICE);
    }

}