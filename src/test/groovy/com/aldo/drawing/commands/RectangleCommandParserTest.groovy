package com.aldo.drawing.commands


import com.aldo.drawing.elements.Rectangle
import spock.lang.Specification
import spock.lang.Unroll

class RectangleCommandParserTest extends Specification {
    RectangleCommandParser parser = new RectangleCommandParser()

    @Unroll
    def 'RectangleCommandParser can parse a valid rectangle command #command'() {
        when: 'parsing command'
        Optional<Rectangle> element = parser.parse(command)

        then: 'the element exists'
        assert element.isPresent()

        and: 'the element matches the expectation'
        Rectangle expected = new Rectangle(x1, y1, x2, y2)
        assert element.get() == expected

        where:
        command      | x1 | y1 | x2 | y2
        'R 1 1 2 2'  | 1  | 1  | 2  | 2
        'R 1 2 17 5' | 1  | 2  | 17 | 5
    }

    @Unroll
    def 'RectangleCommandParser rejects non valid rectangle commands #command'() {
        when: 'parsing command'
        Optional<Rectangle> element = parser.parse(command)

        then: 'no element is returned'
        assert !element.isPresent()

        where:
        command                  | _
        'X 5 7'                  | _
        'R 11'                   | _
        'R 11 7 12 17 something' | _
    }

    def 'Rectangle with invalid coordinates throws exception'() {
        when: 'parsing command'
        parser.parse(command)

        then: 'an exception is thrown'
        thrown(IllegalArgumentException)


        where:
        command     | _
        'R 1 1 0 2' | _
        'R 1 1 2 0' | _
    }
}
