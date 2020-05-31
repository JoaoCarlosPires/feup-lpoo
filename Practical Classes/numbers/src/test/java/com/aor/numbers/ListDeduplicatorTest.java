package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListDeduplicatorTest {

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
    public void deduplicate() {

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);
        expected.add(5);

        IListDeduplicator deduplicator = Mockito.mock(IListDeduplicator.class);
        //ListDeduplicator deduplicator = new ListDeduplicator(list);
        List<Integer> distinct = deduplicator.deduplicate(new ListSorter(list));
        Mockito.when(deduplicator.deduplicate(new ListSorter(list))).thenReturn(distinct);

        //assertEquals(expected, distinct);
    }

    @Test
    public void deduplicateBug() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        class SorterStub implements IListSorter {
            @Override
            public List<Integer> sort() {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(4);
                return list;
            }
        }

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);

        IListDeduplicator deduplicator = Mockito.mock(IListDeduplicator.class);
        //ListDeduplicator deduplicator = new ListDeduplicator(list);
        List<Integer> distinct = deduplicator.deduplicate(new SorterStub());
        Mockito.when(deduplicator.deduplicate(new ListSorter(list))).thenReturn(distinct);

        //assertEquals(expected, distinct);
    }
}