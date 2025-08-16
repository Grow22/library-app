package com.group.libraryapp.controller;

public class Fruit {

    private String name;
    private long price;
    public Fruit(String name, long price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public long getPrice() {
        return price;
    }

}
