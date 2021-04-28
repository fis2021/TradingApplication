package org.fis.ta.model;

public class Item {

    private String name;
    private User owner;
    private String price;
    private String category;

    public Item(){}

    public Item(String name, User owner, String price, String category){
        this.name = name;
        this.owner = owner;
        this.price = price;
        this.category = category;
    }

    public void setName(String name){ this.name = name; }
    public void setPrice(String price){ this.price = price; }
    public void setCategory(String category){ this.category = category; }
    public void setOwner(User owner){ this.owner = owner; }

    public String getName(){ return name; }
    public String getPrice(){ return price; }
    public String getCategory(){ return category; }
    public User getOwner() { return owner; }

}
