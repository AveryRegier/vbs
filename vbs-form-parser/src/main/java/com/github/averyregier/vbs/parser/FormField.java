package com.github.averyregier.vbs.parser;

/**
 * Created by avery on 7/14/15.
 */
public class FormField {
    private final String key;
    private final String value;

    public FormField(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return key+"="+value;
    }
}
