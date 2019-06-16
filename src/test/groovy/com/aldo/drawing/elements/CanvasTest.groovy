package com.aldo.drawing.elements

import com.aldo.drawing.render.Printer
import spock.lang.Specification

class CanvasTest extends Specification {
    def 'Canvas can render itself'() {
        given:
        Printer printer = Mock()
        StringBuilder sb = new StringBuilder()
        Canvas canvas = new Canvas(10, 5)

        when:
        canvas.draw(printer)

        then:
        printer.println(_) >> { args ->
            sb.append(args[0])
            sb.append(System.lineSeparator())
        }

        println sb.toString()

        assert sb.toString().trim() == '''
------------
|          |
|          |
|          |
|          |
|          |
------------'''.trim()
    }

    def 'Canvas can render lines'() {
        given:
        Printer printer = Mock()
        StringBuilder sb = new StringBuilder()
        Canvas canvas = new Canvas(10, 5)
        canvas.addElement(new Line(2, 1, 5, 1))
        canvas.addElement(new Line(8, 2, 8, 5))

        when:
        canvas.draw(printer)

        then:
        printer.println(_) >> { args ->
            sb.append(args[0])
            sb.append(System.lineSeparator())
        }

        println sb.toString()

        assert sb.toString().trim() == '''
------------
| XXXX     |
|       X  |
|       X  |
|       X  |
|       X  |
------------'''.trim()
    }

    def 'Canvas can render lines on the border'() {
        given:
        Printer printer = Mock()
        StringBuilder sb = new StringBuilder()
        Canvas canvas = new Canvas(10, 5)
        canvas.addElement(new Line(1, 1, 2, 1))
        canvas.addElement(new Line(1, 5, 2, 5))
        canvas.addElement(new Line(9, 1, 10, 1))
        canvas.addElement(new Line(9, 5, 10, 5))


        when:
        canvas.draw(printer)

        then:
        printer.println(_) >> { args ->
            sb.append(args[0])
            sb.append(System.lineSeparator())
        }

        println sb.toString()

        assert sb.toString().trim() == '''
------------
|XX      XX|
|          |
|          |
|          |
|XX      XX|
------------'''.trim()
    }

    def 'Canvas renders only inbound portion of lines'() {
        given:
        Printer printer = Mock()
        StringBuilder sb = new StringBuilder()
        Canvas canvas = new Canvas(10, 5)
        canvas.addElement(new Line(3, 1, 12, 1))
        canvas.addElement(new Line(7, 3, 7, 8))

        when:
        canvas.draw(printer)

        then:
        printer.println(_) >> { args ->
            sb.append(args[0])
            sb.append(System.lineSeparator())
        }

        println sb.toString()

        assert sb.toString().trim() == '''
------------
|  XXXXXXXX|
|          |
|      X   |
|      X   |
|      X   |
------------'''.trim()
    }

    def 'Canvas can render a rectangle'() {
        given:
        Printer printer = Mock()
        StringBuilder sb = new StringBuilder()
        Canvas canvas = new Canvas(10, 5)
        canvas.addElement(new Rectangle(2, 1, 8, 4))

        when:
        canvas.draw(printer)

        then:
        printer.println(_) >> { args ->
            sb.append(args[0])
            sb.append(System.lineSeparator())
        }

        println sb.toString()

        assert sb.toString().trim() == '''
------------
| XXXXXXX  |
| X     X  |
| X     X  |
| XXXXXXX  |
|          |
------------'''.trim()
    }

    def 'Canvas renders only inbound portion of rectangle'() {
        given:
        Printer printer = Mock()
        StringBuilder sb = new StringBuilder()
        Canvas canvas = new Canvas(10, 5)
        canvas.addElement(new Rectangle(2, 1, 11, 4))

        when:
        canvas.draw(printer)

        then:
        printer.println(_) >> { args ->
            sb.append(args[0])
            sb.append(System.lineSeparator())
        }

        println sb.toString()

        assert sb.toString().trim() == '''
------------
| XXXXXXXXX|
| X        |
| X        |
| XXXXXXXXX|
|          |
------------'''.trim()
    }

}
