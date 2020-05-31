package com.aor.refactoring.example3;

public class NullDiscount implements Discount {
    public double applyDiscount(double price) {
        return price;
    }
}
