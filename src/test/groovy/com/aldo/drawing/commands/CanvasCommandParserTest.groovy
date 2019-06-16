package com.aldo.drawing.commands

import com.aldo.drawing.elements.Canvas
import spock.lang.Specification
import spock.lang.Unroll

class CanvasCommandParserTest extends Specification {
    CanvasCommandParser parser = new CanvasCommandParser()

    @Unroll
    def 'CanvasCommandParser can parse a valid canvas command #command'() {
        when: 'parsing command'
        Optional<Canvas> element = parser.parse(command)

        then: 'the element exists'
        assert element.isPresent()

        and: 'the element matches the expectation'
        Canvas expected = new Canvas(width, height)
        assert element.get() == expected

        where:
        command   | width | height
        'C 5 7'   | 5     | 7
        'C 11 19' | 11    | 19
    }

    @Unroll
    def 'CanvasCommandParser rejects non valid canvas commands #command'() {
        when: 'parsing command'
        Optional<Canvas> element = parser.parse(command)

        then: 'no element is returned'
        assert !element.isPresent()

        where:
        command            | _
        'X 5 7'            | _
        'C 11'             | _
        'C 11 7 something' | _
    }
}
