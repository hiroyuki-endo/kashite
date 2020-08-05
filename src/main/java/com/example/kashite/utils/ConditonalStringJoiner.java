package com.example.kashite.utils;

import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConditonalStringJoiner {
    private StringJoiner joiner;

    private ConditonalStringJoiner(CharSequence delimiter) {
        this.joiner = new StringJoiner(delimiter);
    }

    public static ConditonalStringJoiner init(CharSequence delimiter) {
        return new ConditonalStringJoiner(delimiter);
    }

    public ConditonalStringJoiner append(String value) {
        joiner.add(value);
        return this;
    }

    public ConditonalStringJoiner optionalAppend(String value, Predicate<String> required) {
        return optionalAppend(value, required, v -> v);
    }

    public ConditonalStringJoiner optionalAppend(String value, Predicate<String> required, Function<String, String> converter) {
        if(required.test(value)) {
            joiner.add(converter.apply(value));
        }
        return this;
    }

    @Override
    public String toString() {
        return joiner.toString();
    }
}
