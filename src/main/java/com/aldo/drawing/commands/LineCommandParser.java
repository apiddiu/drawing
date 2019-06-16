package com.aldo.drawing.commands;

import com.aldo.drawing.elements.Line;

import java.util.regex.Matcher;

public class LineCommandParser extends PatternCommandParser<Line> {
    LineCommandParser() {
        super("^L (\\d+) (\\d+) (\\d+) (\\d+)$");
    }

    @Override
    protected Line buildElement(Matcher matcher) {
        return new Line(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4))
        );
    }
}
