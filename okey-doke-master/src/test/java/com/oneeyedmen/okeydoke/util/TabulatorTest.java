package com.oneeyedmen.okeydoke.util;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TabulatorTest {

    private final Tabulator tabulator = new Tabulator();

    @Test public void empty_when_empty_collection() {
        Iterable<?> empty = Collections.emptyList();
        assertEquals("", tabulator.tableOf(empty));
    }

    @Test public void empty_when_first_row_empty() {
        Iterable<?> emptyRow = Collections.emptyList();
        assertEquals("", tabulator.tableOf(asList(emptyRow)));
    }

    @Test public void no_header_row() {
        Iterable<?> data = asList(
                asList("one", "two", "three"),
                asList("four", "five", "siiiiiiix"));

        assertEquals(
"|one |two |three    |\n" +
"|four|five|siiiiiiix|\n",
            tabulator.tableOf(data));
    }

    @Test public void iterable_of_arrays() {
        Iterable<?> data = asList(
                new String[] {"one", "two", "three"},
                new String[] {"four", "five", "siiiiiiix"});

        assertEquals(
"|one |two |three    |\n" +
"|four|five|siiiiiiix|\n",
                tabulator.tableOf(data));
    }

    @Test public void iterable_of_arrays_of_primitives() {
        Iterable<?> data = asList(
                new int[] {1, 2, 3},
                new boolean[] {true, false, true});

        assertEquals(
"|1   |2    |3   |\n" +
"|true|false|true|\n",
                tabulator.tableOf(data));
    }

    @Test public void header_row_2() {
        Iterable<?> data = asList(
                asList("one", "two", "three"),
                asList("four", "five", "siiiiiiix"));

        assertEquals(
"|Header 1|Header 2|Header 3 |\n" +
"|--------|--------|---------|\n" +
"|one     |two     |three    |\n" +
"|four    |five    |siiiiiiix|\n",
                tabulator.headedTableOf(data, "Header 1", "Header 2", "Header 3"));
    }

    @Test public void header_row_for_no_data() {
        Iterable<?> data = Collections.emptyList();

        assertEquals(
"|Header 1|Header 2|Header 3|\n" +
"|--------|--------|--------|\n",
                tabulator.headedTableOf(data, "Header 1", "Header 2", "Header 3"));
    }

    @Test public void one_dimension() {
        Collection<?> data = asList(
                "one",
                "two",
                "three");

        assertEquals(
"|one  |\n" +
"|two  |\n" +
"|three|\n",
                tabulator.tableOf(data));
    }

    @Test public void one_dimension_with_header_row() {
        Collection<?> data = asList(
                "one",
                "two",
                "three");

        assertEquals(
"|Header|\n" +
"|------|\n" +
"|one   |\n" +
"|two   |\n" +
"|three |\n",
                tabulator.headedTableOf(data, "Header"));
    }

}
