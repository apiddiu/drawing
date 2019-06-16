package com.aldo.drawing.render;

public class ConsolePrinter implements Printer {
    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public void print(String s) {
        System.out.print(s);
    }
}
