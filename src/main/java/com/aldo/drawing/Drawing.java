package com.aldo.drawing;

import com.aldo.drawing.commands.CommandParser;
import com.aldo.drawing.elements.Canvas;
import com.aldo.drawing.elements.Element;
import com.aldo.drawing.input.InputReader;
import com.aldo.drawing.render.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Drawing implements CommandLineRunner {
    private final InputReader reader;
    private final CommandParser<Element> parser;
    private final Printer printer;

    @Autowired
    public Drawing(InputReader reader, CommandParser<Element> parser, Printer printer) {
        this.reader = reader;
        this.parser = parser;
        this.printer = printer;
    }

    @Override
    public void run(String... args) {
        printer.println("RUN");

        String command;
        Canvas canvas = null;

        while (true) {
            printer.print("Enter command: ");
            command = reader.next();

            if ("Q".equals(command)) {
                printer.println("Quit program");
                break;
            }

            try {
                Optional<Element> parsedElement = parser.parse(command);
                if (parsedElement.isPresent()) {
                    Element element = parsedElement.get();

                    if (element instanceof Canvas) {
                        canvas = (Canvas) element;
                        canvas.render(printer);
                    } else if (canvas != null) {
                        canvas.draw(element);
                        canvas.render(printer);
                    } else {
                        printer.println("Please create a canvas first");
                    }

                } else {
                    printer.println("Unknown command");
                }
            } catch (Exception e) {
                printer.println("An error occurred: " + e.getMessage());

            }
        }
    }
}
