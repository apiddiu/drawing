package com.aldo.drawing.commands;

import com.aldo.drawing.elements.Element;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositeCommandParser implements CommandParser {

    private final Set<PatternCommandParser<? extends Element>> parsers;

    public CompositeCommandParser() {
        this.parsers = Stream.of(
                new CanvasCommandParser(),
                new LineCommandParser(),
                new RectangleCommandParser()
        ).collect(Collectors.toSet());
    }

    @Override
    public Optional<Element> parse(String command) {
        return parsers.stream()
                .map(p -> p.parse(command))
                .filter(Optional::isPresent)
                .map(o -> (Element) o.get())
                .findFirst();
    }
}
