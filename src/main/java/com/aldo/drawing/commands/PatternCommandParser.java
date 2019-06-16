package com.aldo.drawing.commands;

import com.aldo.drawing.elements.Element;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PatternCommandParser<T extends Element> implements CommandParser {
    private Pattern pattern;

    PatternCommandParser(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public Optional<T> parse(String command) {
        return Optional.of(pattern.matcher(command))
                .filter(Matcher::find)
                .map(this::buildElement);
    }

    protected abstract T buildElement(Matcher matcher);
}
