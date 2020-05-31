package com.aor.numbers;

import java.util.ArrayList;
import java.util.List;

public class ListFilterer {
    private List<Integer> myList;
    public ListFilterer(List<Integer> list) {
        this.myList = list;
    }

    public List<Integer> filter(IListFilter filter) {
        List<Integer> newList = new ArrayList<>();
        for (Integer i : myList) {
            if (filter.accept(i)) {
                newList.add(i);
            }
        }
        return newList;
    }
}
