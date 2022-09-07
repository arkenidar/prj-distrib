package com.arkenidar;

import com.arkenidar.products.Product;

import java.util.HashMap;
import java.util.Map;

public class DistributoreDiBevande {

    private final int limit; // quantity limit
    private Map<String, Integer> quantities; // quantities by id
    private double balance;

    private Map<String, Double> costs; // costs by id

    DistributoreDiBevande(int capacity) {
        quantities = new HashMap<String, Integer>();
        costs = new HashMap<String, Double>();
        limit = capacity;
        balance = 0;
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

    public void caricaProdotto(Product product) {
        if (usedCapacity() >= limit) {
            System.out.println("errore: capacità limite raggiunta!");
            return;
        }

        Integer quantity = quantities.get(product.getId());
        if (quantity == null) quantity = 0;
        quantities.put(product.getId(), quantity + 1);

        costs.put(product.getId(), product.getCost());

        if (usedCapacity() >= limit) {
            System.out.println("errore: capacità limite raggiunta!");
            return;
        }
    }

    public Product scegliProdotto(String id) {
        Integer quantity = quantities.get(id);
        if (quantity <= 0) {
            System.out.println("errore: quantità prodotto uguale a zero");
            return null;
        }
        Double cost = costs.get(id);
        if (cost == null) {
            System.out.println("errore: costo inesistente nell'elenco costi");
            return null;
        }
        double newBalance = balance - cost;
        if (newBalance < 0) {
            System.out.println("errore: costi non coperti dal denaro immesso");
            return null;
        }
        balance = newBalance;
        quantities.put(id, quantity - 1);

        return new Product(id, costs.get(id));
    }

    @Override
    public String toString() {
        String description = "contenuto: { ";
        for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
            String part = entry.getKey() + ":" + entry.getValue();
            description += part + ", ";
        }
        description += "}";
        return description;
    }

    public int usedCapacity() {
        int sum = quantities.values().stream().mapToInt(i -> i).sum();
        return sum;
    }
}
