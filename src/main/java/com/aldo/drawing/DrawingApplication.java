package com.aldo.drawing;

import com.aldo.drawing.commands.CommandParser;
import com.aldo.drawing.elements.Canvas;
import com.aldo.drawing.elements.Element;
import com.aldo.drawing.input.InputReader;
import com.aldo.drawing.render.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class DrawingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DrawingApplication.class, args);
    }

    @Autowired
    private InputReader reader;

    @Autowired
    private CommandParser<Element> parser;

    @Autowired
    private Printer printer;

    @Override
    public void run(String... args) throws Exception {
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
                        canvas.draw(printer);
                    } else if (canvas != null) {
                        canvas.addElement(element);
                        canvas.draw(printer);
                    } else {
                        printer.println("please create a canvas first");
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
