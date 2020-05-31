package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListAggregatorTest {

    private List<Integer> list = new ArrayList<>();

    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);
    }


    @Test
    public void sum() {

        ListAggregator aggregator = new ListAggregator(list);

        int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public void max() {

        ListAggregator aggregator = new ListAggregator(list);

        int max = aggregator.max();

        assertEquals(5, max);
    }

    @Test
    public void max2() {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(-4);
        list.add(-5);

        ListAggregator aggregator = new ListAggregator(list);

        int max = aggregator.max();

        assertEquals(-1, max);

    }

    @Test
    public void min() {

        ListAggregator aggregator = new ListAggregator(list);

        int min = aggregator.min();

        assertEquals(1, min);
    }


    @Test
    public void distinct() {
        ListAggregator aggregator = new ListAggregator(list);

        class DeduplicateStub implements IListDeduplicator {
            @Override
            public List<Integer> deduplicate(IListSorter sorter) {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(4);
                list.add(5);
                return list;
            }
        }

        class SorterStub implements IListSorter{
            @Override
            public List<Integer> sort() {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(2);
                list.add(4);
                list.add(5);
                return list;
            }
        }

        int distinct = aggregator.distinct(new DeduplicateStub(), new SorterStub());

        assertEquals(4, distinct);
    }

    @Test
    public void distinct2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        ListAggregator aggregator = new ListAggregator(list);

        class DeduplicateStub implements IListDeduplicator {
            @Override
            public List<Integer> deduplicate(IListSorter sorter) {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(4);
                return list;
            }
        }

        class SorterStub implements IListSorter {
            @Override
            public List<Integer> sort() {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(2);
                list.add(4);
                return list;
            }
        }

        int distinct = aggregator.distinct(new DeduplicateStub(), new SorterStub());

        assertEquals(3, distinct);

    }
}