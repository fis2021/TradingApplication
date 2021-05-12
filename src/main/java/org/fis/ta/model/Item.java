package org.fis.ta.model;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.dizitart.no2.objects.Id;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Item {
    @Id
    private int ID;
    private static int count;
    private String name;
    private String description;
    private String category;
    private ArrayList<String> images = new ArrayList<>();
    private String owner;
    private String price;
    private int counter =0;
    private String dateAdded;


    public Item(String owner, String name, String category, String description, ArrayList<String> images, String price,String dateAdded)
    {
        this.ID = count;
        count++;
        this.name = name;
        this.description = description;
        this.images = images;
        this.owner = owner;
        this.price = price;
        this.category = category;
        this.dateAdded=dateAdded;
    }
    public Item()
    { }

    public int getID() { return ID; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public ArrayList<String> getImages() { return images; }
    public String getOwner() { return owner; }
    public String getPrice() { return price; }
    public String getCategory() { return category;}
    public String getDateAdded(){return dateAdded;}
    public static int getCount() { return count; }

    public int getCounter(){return counter;}



    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setCategory(String category) { this.category = category;}
    public void setDateAdded(String date){this.dateAdded=date;}

    public static void setCount(int count){ Item.count = count;}
    public void setCounter(int counter)
    {this.counter=counter;}

    public Image getImage() throws FileNotFoundException {
        Image image = null;
        try {
            FileInputStream input = new FileInputStream(images.get(0));
            image = new Image(input);
            return image;
        } catch (FileNotFoundException e) {
            
        }
        return image;

    }

    public String toString(){
        return this.name;
    }




}