package com.aldo.drawing.render

import spock.lang.Specification


class ConsolePrinterTest extends Specification {
    ConsolePrinter printer = new ConsolePrinter()
    PrintStream console
    PrintStream mockedConsole

    def setup() {
        console = System.out
        mockedConsole = Mock()
        System.setOut(mockedConsole)
    }

    def 'println prints a new line on console'() {
        given:
        def message = "Hello World!"

        when:
        printer.println(message)

        then:
        1 * mockedConsole.println(_) >> { args -> console.println(args[0]) }
    }

    def 'print prints on console'() {
        given:
        def message = "Hello World!"

        when:
        printer.print(message)

        then:
        1 * mockedConsole.print(_) >> { args -> console.print(args[0]) }
    }
}
