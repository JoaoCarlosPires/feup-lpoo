package com.aor.refactoring.example3;

public class DiscountPercentage implements Discount {
    private int fixed;

    public DiscountPercentage(int fixed) {
        this.fixed = fixed;
    }

    public double applyDiscount(double price) {
        if (fixed > 0) return fixed > price ? 0 : price - fixed;
        return price;
    }
}
