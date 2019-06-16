package com.aldo.drawing.elements;

import com.aldo.drawing.render.Printer;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode
@Getter
public class Canvas implements Element {
    private static final int border = 2;
    private final int width;
    private final int height;
    private final char[][] screen;
    private List<Element> elements = new LinkedList<>();

    public Canvas(int w, int h) {
        if (!argumentsValid(w, h)) {
            throw new IllegalArgumentException("Canvas invalid width*height");
        }

        this.width = w + border;
        this.height = h + border;
        this.screen = new char[height][width];
    }

    private boolean argumentsValid(int width, int height) {
        return width > 0 && height > 0;
    }

    public void addElement(Element e) {
        elements.add(e);
    }

    public void draw(Printer printer) {
        draw(this);

        elements.forEach(e -> e.draw(this));

        render(printer);
    }

    @Override
    public void draw(Canvas canvas) {
        reset();
        canvas.drawHorizontal(0);
        canvas.drawHorizontal(height - 1);
        canvas.drawVertical(0);
        canvas.drawVertical(width - 1);
    }

    private void drawHorizontal(int y) {
        for (int i = 0; i < width; i++) {
            screen[y][i] = '-';
        }
    }

    private void drawVertical(int x) {
        for (int i = 1; i < height - 1; i++) {
            screen[i][x] = '|';
        }
    }

    private void reset() {
        Arrays.stream(screen)
                .forEach(chars -> {
                    for (int i = 0; i < chars.length; i++) {
                        chars[i] = ' ';
                    }
                });
    }

    public void setPixel(int x, int y, char value) {
        if (inBound(x, y)) {
            screen[y][x] = value;
        }
    }

    private boolean inBound(int x, int y) {
        return (x > 0 && x < screen[0].length - 1)
                && (y > 0 && y < screen.length - 1);
    }

    private void render(Printer printer) {
        Arrays.stream(screen)
                .map(String::copyValueOf)
                .forEach(printer::println);
    }
}
