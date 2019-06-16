package com.aldo.drawing.commands

import com.aldo.drawing.elements.Canvas
import com.aldo.drawing.elements.Line
import com.aldo.drawing.elements.Rectangle
import spock.lang.Specification
import spock.lang.Unroll

class CompositeCommandParserTest extends Specification {
    CompositeCommandParser parser = new CompositeCommandParser()

    @Unroll
    def 'CompositeCommandParser can parse a valid command #command'() {
        when: 'parsing command'
        Optional element = parser.parse(command)

        then: 'the element exists'
        assert element.isPresent()

        and: 'the element matches the expectation'
        assert element.get() == expectedElement

        where:
        command      | expectedElement
        'C 5 7'      | new Canvas(5, 7)
        'C 11 19'    | new Canvas(11, 19)
        'L 1 2 1 5'  | new Line(1, 2, 1, 5)
        'L 2 5 17 5' | new Line(2, 5, 17, 5)
        'R 1 1 2 2'  | new Rectangle(1, 1, 2, 2)
        'R 1 2 17 5' | new Rectangle(1, 2, 17, 5)
    }

    @Unroll
    def 'CompositeCommandParser rejects non valid commands #command'() {
        when: 'parsing command'
        Optional<Rectangle> element = parser.parse(command)

        then: 'no element is returned'
        assert !element.isPresent()

        where:
        command                  | _
        'X 5 7'                  | _
        'R 11'                   | _
        'R 11 7 12 17 something' | _
        'C 11'                   | _
        'C 11 7 something'       | _
        'L 11'                   | _
        'L 11 7 something'       | _
    }
}
