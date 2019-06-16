package com.aldo.drawing

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

@SpringBootTest
class DrawingApplicationTest extends Specification {

    def setupSpec() {
        System.setIn(new ByteArrayInputStream("Q".bytes))
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    def 'the application quits when the quit command is received'() {
        expect:
        true
    }
}
