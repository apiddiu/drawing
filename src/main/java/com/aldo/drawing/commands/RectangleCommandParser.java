package com.aldo.drawing.commands;

import com.aldo.drawing.elements.Rectangle;

import java.util.regex.Matcher;

public class RectangleCommandParser extends PatternCommandParser<Rectangle> {
    RectangleCommandParser() {
        super("^R (\\d+) (\\d+) (\\d+) (\\d+)$");
    }

    @Override
    protected Rectangle buildElement(Matcher matcher) {
        return new Rectangle(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4))
        );
    }
}
