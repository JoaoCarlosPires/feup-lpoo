package com.aor.numbers;

public class DivisibleByFilter implements IListFilter{
    private Integer myDivisor;

    public DivisibleByFilter(Integer divisor) {
        this.myDivisor = divisor;
    }

    @Override
    public boolean accept(Integer number) {
        return number % this.myDivisor == 0;
    }
}
