package org.fis.ta.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    public static final String OWNER = "owner";
    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String DESCRIPTION = "description";
    public static final ArrayList<String> IMAGES = ItemService.getFakeList();
    public static final String PRICE = "price";

    @BeforeEach
    void setUp()throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-trading-application";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ItemService.initDatabase();
    }

    @AfterEach
    void tearDown(){
        ItemService.getItemRepository().close();
    }

    @Test
    @DisplayName("Database is initialised and empty")
    void testDatabaseIsInitialised(){

    }

}