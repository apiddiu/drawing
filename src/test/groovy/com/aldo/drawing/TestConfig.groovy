package com.aldo.drawing

import com.aldo.drawing.render.Printer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@TestConfiguration
class TestConfig {
    @Bean
    @Primary
    Printer testPrinter() {
        return new TestPrinter()
    }

    static class TestPrinter implements Printer {
        StringBuilder output = new StringBuilder()

        @Override
        void println(String s) {
            output.append(s)
            output.append(System.lineSeparator())
        }

        @Override
        void print(String s) {
            output.append(s)
            output.append(System.lineSeparator())
        }

        def output() {
            output.toString().trim()
        }
    }
}
