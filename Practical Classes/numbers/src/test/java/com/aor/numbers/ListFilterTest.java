package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListFilterTest {
    @Test
    public void acceptZero() {
        DivisibleByFilter filter = new DivisibleByFilter(4);
        assertTrue(filter.accept(0));
    }

    @Test
    public void acceptNotDivisible() {
        DivisibleByFilter filter = new DivisibleByFilter(4);
        assertFalse(filter.accept(2));
    }

    @Test
    public void acceptDivisible() {
        DivisibleByFilter filter = new DivisibleByFilter(4);
        assertTrue(filter.accept(8));
    }

    List<Integer> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add(-1);
        list.add(2);
        list.add(-3);
        list.add(4);
    }

    @Test
    public void filter() {
        IListFilter filter = Mockito.mock(IListFilter.class);
        Mockito.when(filter.accept(-1)).thenReturn(true);
        Mockito.when(filter.accept(2)).thenReturn(false);
        Mockito.when(filter.accept(-3)).thenReturn(false);
        Mockito.when(filter.accept(4)).thenReturn(true);

        List<Integer> expected = new ArrayList<>();
        expected.add(-1);
        expected.add(4);

        ListFilterer filterer = new ListFilterer(list);
        List<Integer> result = filterer.filter(filter);

        assertEquals(expected, result);
    }

    @Test
    public void acceptPositive() {
        PositiveFilter filter = new PositiveFilter();

        assertTrue(filter.accept(1));
    }

    @Test
    public void acceptNegative() {
        PositiveFilter filter = new PositiveFilter();

        assertFalse(filter.accept(-1));
    }

    @Test
    public void acceptZero2() {
        PositiveFilter filter = new PositiveFilter();

        assertFalse(filter.accept(0));
    }
}
