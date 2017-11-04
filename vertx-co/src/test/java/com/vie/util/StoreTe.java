package com.vie.util;

import com.vie.hors.ke.EmptyStreamException;
import com.vie.hors.ke.JsonFormatException;
import org.junit.Test;
import top.StoreBase;

public class StoreTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testNone() {
        execJObject("zero.json", config -> {

        });
    }

    @Test(expected = JsonFormatException.class)
    public void testEmpty() {
        execJObject("format.json", config -> {

        });
    }
}
