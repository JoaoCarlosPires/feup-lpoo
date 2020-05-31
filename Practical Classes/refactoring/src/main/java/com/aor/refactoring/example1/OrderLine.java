package com.aor.refactoring.example1;

public class OrderLine {
    public Product product;
    public int quantity;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getName() {
        return product.name;
    }

    public double getPrice() {
        return product.price;
    }

    public String lineToString() {
        return getName() + "(x" + quantity + "): " + (getPrice() * quantity) + "\n";
    }
}
