package com.aldo.drawing.commands


import com.aldo.drawing.elements.Line
import spock.lang.Specification
import spock.lang.Unroll

class LineCommandParserTest extends Specification {
    LineCommandParser parser = new LineCommandParser()

    @Unroll
    def 'LineCommandParser can parse a valid line command #command'() {
        when: 'parsing command'
        Optional<Line> element = parser.parse(command)

        then: 'the element exists'
        assert element.isPresent()

        and: 'the element matches the expectation'
        Line expected = new Line(x1, y1, x2, y2)
        assert element.get() == expected

        where:
        command      | x1 | y1 | x2 | y2
        'L 1 2 1 5'  | 1  | 2  | 1  | 5
        'L 2 5 17 5' | 2  | 5  | 17 | 5
    }

    @Unroll
    def 'LineCommandParser rejects non valid line commands #command'() {
        when: 'parsing command'
        Optional<Line> element = parser.parse(command)

        then: 'no element is returned'
        assert !element.isPresent()

        where:
        command            | _
        'X 5 7'            | _
        'L 11'             | _
        'L 11 7 something' | _
    }

    def 'Line with invalid coordinates throws exception'() {
        when: 'parsing command'
        parser.parse(command)

        then: 'an exception is thrown'
        thrown(IllegalArgumentException)


        where:
        command        | _
        'L 11 7 12 12' | _
    }
}
