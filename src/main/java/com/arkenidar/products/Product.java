package com.arkenidar.products;

public abstract class Product {
    private String id;
    private double cost;

    Product(String id, double cost) {
        this.id = id;
        this.cost = cost;
    }
}
