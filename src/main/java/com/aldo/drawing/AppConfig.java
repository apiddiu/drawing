package com.aldo.drawing;

import com.aldo.drawing.commands.CommandParser;
import com.aldo.drawing.commands.CompositeCommandParser;
import com.aldo.drawing.input.ConsoleInputReader;
import com.aldo.drawing.input.InputReader;
import com.aldo.drawing.render.ConsolePrinter;
import com.aldo.drawing.render.Printer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public InputReader reader() {
        return new ConsoleInputReader();
    }

    @Bean
    CommandParser parser() {
        return new CompositeCommandParser();
    }

    @Bean
    Printer printer() {
        return new ConsolePrinter();
    }
}
