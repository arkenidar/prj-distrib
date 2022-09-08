package com.arkenidar.products;

public abstract class Product {
    private final String id;
    private final double cost;

    public Product(String id, double cost) {
        this.id = id;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return id + ":" + cost;
    }
}
