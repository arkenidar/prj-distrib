package com.arkenidar;

import com.arkenidar.products.Product;

import java.util.LinkedList;
import java.util.List;

public class DistributoreDiBevande {

    private final int quantityLimit;
    private final List<Product> products;
    private double balance;

    DistributoreDiBevande(int capacity) {
        products = new LinkedList<>();
        balance = 0;
        quantityLimit = capacity;
    }

    public double saldoAttuale() {
        return balance;
    }

    public void inserisciImporto(double amount) {
        if (amount < 0) {
            System.out.println("errore: quantità negativa come importo da inserire");
            return;
        }
        balance += amount;
    }

    public double getResto() {
        double change = balance;
        balance = 0;
        return change;
    }

    public boolean caricaProdotto(Product product) {
        if (usedCapacity() >= quantityLimit) {
            System.out.println("errore: capacità limite raggiunta!");
            return false;
        }

        products.add(product);

        if (usedCapacity() >= quantityLimit) {
            System.out.println("errore: capacità limite raggiunta!");
        }

        return true;
    }

    public Product scegliProdotto(String id) {
        Product product;

        product = products.stream().filter(curProd -> curProd.getId().equals(id)).findFirst().orElse(null);

        if (product == null) {
            System.out.println("errore: quantità prodotto uguale a zero");
            return null;
        }

        double cost = product.getCost();

        double newBalance = balance - cost;
        if (newBalance < 0) {
            System.out.println("errore: costi non coperti dal denaro immesso");
            return null;
        }
        balance = newBalance;

        products.remove(product);

        return product;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("contenuto: { ");
        for (Product curProd : products) {
            description.append(curProd).append(", ");
        }
        description.append("}");
        return description.toString();
    }

    public int usedCapacity() {
        return products.size();
    }
}
