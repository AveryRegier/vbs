package com.github.averyregier.vbs.parser;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by avery on 7/14/15.
 */
public class LineParserTest {
    private LineParser parser = new LineParser();

    @Test
    public void parseOne() {
        String toParse = "key: value";
        List<FormField> result = parser.parse(toParse);
        assertEquals(1, result.size());
        assertFieldEquals(result, "key", "value", 0);
    }

    private void assertFieldEquals(List<FormField> result, String key, String value, int index) {
        FormField formField = result.get(index);
        assertEquals(key, formField.getKey());
        assertEquals(value, formField.getValue());
    }

    @Test
    public void parseNothing() {
        String toParse = "This is just random text";
        List<FormField> result = parser.parse(toParse);
        assertEquals(0, result.size());
    }

    @Test
    public void parse2Lines() {
        String toParse = "This is just random text\nkey: value";
        List<FormField> result = parser.parse(toParse);
        assertEquals(1, result.size());
        assertFieldEquals(result, "key", "value", 0);
    }

    @Test
    public void parse2Values() {
        String toParse = "key 1: value 1\nkey 2: value 2";
        List<FormField> result = parser.parse(toParse);
        assertEquals(2, result.size());
        assertFieldEquals(result, "key 1", "value 1", 0);
        assertFieldEquals(result, "key 2", "value 2", 1);
    }

    @Test
    public void sameKeyCorrectOrder() {
        String toParse = "key: value 1\nkey: value 2";
        List<FormField> result = parser.parse(toParse);
        assertEquals(2, result.size());
        assertFieldEquals(result, "key", "value 1", 0);
        assertFieldEquals(result, "key", "value 2", 1);
    }
}