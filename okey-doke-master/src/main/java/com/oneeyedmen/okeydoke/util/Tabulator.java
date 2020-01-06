package com.oneeyedmen.okeydoke.util;

import com.oneeyedmen.okeydoke.internal.LyingWrappingIterator;

import java.lang.reflect.Array;
import java.util.*;

public class Tabulator {

    public String tableOf(Iterable<?> data) {
        return tableOf(data, columnSizes(data));
    }

    public String headedTableOf(Iterable<?> data, String... headers) {
        Iterable<?> dataWithHeader = withHeader(data, headers);
        int[] columnSizes = columnSizes(dataWithHeader);
        return tableOf(withDivider(dataWithHeader, columnSizes), columnSizes);
    }

    private int[] columnSizes(Iterable<?> data) {
        int[] result = {};
        for (Object row : data) {
            Collection<?> rowCollection = collectionOf(row);
            if (result.length == 0)
                result = new int[rowCollection.size()];
            int c = 0;
            for (Object cell : rowCollection) {
                int cellLength = String.valueOf(cell).length();
                result[c] = Math.max(result[c], cellLength);
                ++c;
            }
        }
        return result;
    }

    private Collection<?> collectionOf(Object row) {
        if (row instanceof Collection)
            return (Collection<?>) row;
        if (row.getClass().isArray())
            return asList(row);
        return Collections.singleton(row);
    }

    private String tableOf(Iterable<?> data, int[] columnSizes) {
        if (columnSizes.length == 0)
            return "";
        List<List<String>> formatted = formattedCells(data, columnSizes);
        StringBuffer result = new StringBuffer();
        for (List<String> row: formatted) {
            formatRowInto(result, row);
        }
        return result.toString();
    }

    private void formatRowInto(StringBuffer result, List<String> row) {
        result.append('|');
        for (String cell : row) {
            result.append(cell).append('|');
        }
        result.append('\n');
    }

    private List<List<String>> formattedCells(Iterable<?> data, int[] columnSizes) {
        List<List<String>> result = new ArrayList<List<String>>();
        for (Object row : data) {
            result.add(formattedRow(collectionOf(row), columnSizes));
        }
        return result;
    }

    private List<String> formattedRow(Iterable<?> rowCollection, int[] columnSizes) {
        List<String> resultRow = new ArrayList<String>();
        int c = 0;
        for (Object cell : rowCollection) {
            resultRow.add(formatCell(String.valueOf(cell), columnSizes[c]));
            ++c;
        }
        return resultRow;
    }

    private String formatCell(String s, int columnSize) {
        if (s.length() == columnSize)
            return s;
        StringBuilder result = new StringBuilder(columnSize);
        result.append(s);
        while (result.length() < columnSize) {
            result.append(' ');
        }
        return result.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> asList(final Object array) {
        if (!array.getClass().isArray())
            throw new IllegalArgumentException("Not an array");
        return new AbstractList<T>() {
            @Override
            public T get(int index) {
                return (T) Array.get(array, index);
            }

            @Override
            public int size() {
                return Array.getLength(array);
            }
        };
    }

    @SuppressWarnings("unchecked")
    private Iterable<?> withHeader(final Iterable<?> data, final String... headers) {
        return new Iterable() {
            @Override public Iterator iterator() {
                return new LyingWrappingIterator(data.iterator()) {
                    @Override protected boolean hasNext(int i) {
                        return wrapped.hasNext() || i < 1;
                    }

                    @Override protected Object next(int i) {
                        return i == 0 ? headers : wrapped.next();
                    }
                };
            }
        };

    }

    @SuppressWarnings("unchecked")
    private Iterable<?> withDivider(final Iterable<?> data, final int[] columnSizes) {
        return new Iterable<Object>() {
            @Override public Iterator<Object> iterator() {
                return new LyingWrappingIterator(data.iterator()) {
                    @Override protected boolean hasNext(int i) {
                        return wrapped.hasNext() || i < 2;
                    }

                    @Override protected Object next(int i) {
                        return i == 1 ? dividerData(columnSizes) : wrapped.next();
                    }
                };
            }
        };
    }

    private Collection<?> dividerData(int[] columnSizes) {
        List<String> result = new ArrayList<String>(columnSizes.length);
        for (int i = 0; i < columnSizes.length; i++) {
            result.add(times('-', columnSizes[i]));
        }
        return result;
    }

    private String times(char ch, int repeat) {
        StringBuffer result = new StringBuffer(repeat);
        for (int i = 0; i < repeat; i++) {
            result.append(ch);
        }
        return result.toString();
    }
}
