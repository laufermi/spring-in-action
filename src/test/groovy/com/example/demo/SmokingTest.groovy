package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class SmokingTest extends Specification {
    @Autowired
    ApplicationContext applicationContext

    def 'Smoke Testing'() {
        expect:
            applicationContext.id == 'application'
    }
}
