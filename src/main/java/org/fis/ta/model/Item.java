package org.fis.ta.model;

import javafx.scene.image.ImageView;
import org.dizitart.no2.objects.Id;

import java.util.ArrayList;

public class Item {
    @Id
    private String name;
    private String description;
    private ArrayList<ImageView> images;
    private String ownerUsername;
    private int price;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ImageView> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageView> images) {
        this.images = images;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public Item(String name, String description, ArrayList<ImageView> images,String ownerUsername,int price)
    {
        this.name=name;
        this.description=description;
        this.images=images;
        this.ownerUsername=ownerUsername;
        this.price=price;
    }
    public Item()
    { }


}
