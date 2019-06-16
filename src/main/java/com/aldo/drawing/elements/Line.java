package com.aldo.drawing.elements;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Line implements Element {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public Line(int x1, int y1, int x2, int y2) {
        if (!argumentsValid(x1, y1, x2, y2)) {
            throw new IllegalArgumentException("Line invalid coordinates");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    private boolean argumentsValid(int x1, int y1, int x2, int y2) {
        return x1 == x2 || y1 == y2;
    }

    @Override
    public void draw(Canvas canvas) {
        for (int ix = x1; ix <= x2; ix++) {
            for (int iy = y1; iy <= y2; iy++) {
                canvas.setPixel(ix, iy, 'X');
            }
        }
    }
}
