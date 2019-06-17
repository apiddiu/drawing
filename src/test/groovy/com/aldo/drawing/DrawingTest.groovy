package com.aldo.drawing

import com.aldo.drawing.commands.CommandParser
import com.aldo.drawing.commands.CompositeCommandParser
import com.aldo.drawing.elements.Canvas
import com.aldo.drawing.elements.Line
import com.aldo.drawing.elements.Rectangle
import com.aldo.drawing.input.InputReader
import com.aldo.drawing.render.Printer
import spock.lang.Specification
import spock.lang.Unroll

class DrawingTest extends Specification {
    Drawing drawing
    InputReader reader
    CommandParser parser
    Printer printer

    def setup() {
        reader = Mock()
        parser = Spy(CompositeCommandParser)
        printer = Mock()
        drawing = new Drawing(reader, parser, printer)
    }

    def 'Q command stops the process'() {
        given:
        reader.next() >> 'Q'

        when:
        drawing.run()

        then:
        1 * printer.println('Quit program')
    }

    def 'Elements can be drawn on a canvas'() {
        def canvasCommand = 'C 10 10'
        def lineCommand = 'L 1 1 5 1'
        def rectangleCommand = 'R 1 1 5 5'
        Canvas canvas = Mock()
        parser.parse(canvasCommand) >> Optional.of(canvas)

        given:
        reader.next() >> canvasCommand >> lineCommand >> rectangleCommand >> 'Q'

        when:
        drawing.run()

        then:
        1 * canvas.draw(_ as Line)
        1 * canvas.draw(_ as Rectangle)
        3 * canvas.render(printer)
        1 * printer.println('Quit program')
    }

    def 'when command is not valid a message is printed'() {
        def unknownCommand = 'X 12 14 Unknown'
        given:
        reader.next() >> unknownCommand >> 'Q'

        when:
        drawing.run()

        then:
        1 * printer.println('Unknown command')
        1 * printer.println('Quit program')
    }

    @Unroll
    def 'when a Canvas does not exist yet no #type can be created'() {
        given:
        reader.next() >> command >> 'Q'

        when:
        drawing.run()

        then:
        1 * printer.println('Please create a canvas first')
        1 * printer.println('Quit program')

        where:
        command     | type
        'L 1 1 2 1' | 'Line'
        'R 1 1 2 1' | 'Rectangle'
    }

    @Unroll
    def 'when an error occurs the message #error is printed'() {
        given:
        reader.next() >> command >> 'Q'

        when:
        drawing.run()

        then:
        1 * printer.println("An error occurred: ${error}")
        1 * printer.println('Quit program')

        where:
        command       | error
        'R 1 10 10 1' | 'Rectangle invalid coordinates'
        'L 1 10 11 7' | 'Line invalid coordinates'
    }
}
