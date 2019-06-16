package com.aldo.drawing.input;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    Scanner scanner = new Scanner(System.in);

    public String next() {
        return scanner.nextLine();
    }
}
