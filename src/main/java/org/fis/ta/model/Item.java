package org.fis.ta.model;


import javafx.scene.image.Image;
import org.dizitart.no2.objects.Id;

import java.util.ArrayList;


public class Item {
    @Id
    private int ID;
    private static int count=0;
    private String name;
    private String description;
    private String category;
    private ArrayList<Image> images;
    private String owner;
    private String price;
    private int counter =0;



    public Item(String owner, String name, String category, String description, ArrayList<Image> images, String price)
    {
        this.ID = count;
        count++;
        this.name = name;
        this.description = description;
        this.images = images;
        this.owner = owner;
        this.price = price;
        this.category = category;
    }
    public Item()
    { }

    public int getID() { return ID; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public ArrayList<Image> getImages() { return images; }
    public String getOwner() { return owner; }
    public String getPrice() { return price; }
    public String getCategory() { return category;}
    public int getCounter(){
        return counter;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setCategory(String category) { this.category = category;}
    public int setCounter(int counter){
        this.counter=counter;
    }



}