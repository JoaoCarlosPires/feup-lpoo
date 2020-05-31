package com.aor.refactoring.example3;

public class DiscountFixed implements Discount {
    private double percentage;

    public DiscountFixed(double percentage) {
        this.percentage = percentage;
    }

    public double applyDiscount(double price) {
        if (percentage > 0) return price - price * percentage;
       return price;
    }
}
