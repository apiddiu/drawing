package com.aldo.drawing.commands;

import com.aldo.drawing.elements.Element;

import java.util.Optional;

public interface CommandParser<T extends Element> {
    Optional<T> parse(String command);
}
