package com.aldo.drawing.commands;

import com.aldo.drawing.elements.Canvas;

import java.util.regex.Matcher;

public class CanvasCommandParser extends PatternCommandParser<Canvas> {
    CanvasCommandParser() {
        super("^C (\\d+) (\\d+)$");
    }

    @Override
    protected Canvas buildElement(Matcher matcher) {
        return new Canvas(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2))
        );
    }
}
