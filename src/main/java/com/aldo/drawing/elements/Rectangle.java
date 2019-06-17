package com.aldo.drawing.elements;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rectangle implements Element {
    private final Line l1;
    private final Line l2;
    private final Line l3;
    private final Line l4;

    public Rectangle(int x1, int y1, int x2, int y2) {
        if (!argumentsValid(x1, y1, x2, y2)) {
            throw new IllegalArgumentException("Rectangle invalid coordinates");
        }

        this.l1 = new Line(x1, y1, x1, y2);
        this.l2 = new Line(x1, y1, x2, y1);
        this.l3 = new Line(x2, y1, x2, y2);
        this.l4 = new Line(x1, y2, x2, y2);

    }

    private boolean argumentsValid(int x1, int y1, int x2, int y2) {
        return x1 <= x2 && y1 <= y2;
    }

    @Override
    public void draw(Screen screen) {
        l1.draw(screen);
        l2.draw(screen);
        l3.draw(screen);
        l4.draw(screen);
    }
}
