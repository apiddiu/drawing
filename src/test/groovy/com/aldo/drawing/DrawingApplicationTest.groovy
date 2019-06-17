package com.aldo.drawing


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import spock.lang.Specification
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

@SpringBootTest
@Import(TestConfig)
class DrawingApplicationTest extends Specification {

    @Autowired
    TestConfig.TestPrinter printer

    def setupSpec() {
        System.setIn(new ByteArrayInputStream(
                """C 20 5
L 1 3 7 3
L 7 1 7 3
R 15 2 20 5
Q
""".bytes))


    }

    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    def 'the application quits when the quit command is received'() {
        given:
        def expectedOutput = getClass().getResource('/drawAppOut.txt').text

        expect:
        println printer.output()
        assert printer.output() == expectedOutput.trim()
    }
}
