package com.example.demo.api.handler

import com.example.demo.application.exception.DuplicateUsernameException
import com.example.demo.application.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import spock.lang.Specification

class ApiGlobalHandlerTest extends Specification {

    ApiGlobalHandler handler = new ApiGlobalHandler()

    def 'We can get error code when throw xException'() {
        expect:
            handler.handler(ex).statusCode == resultStatus

        where:
            ex                                      || resultStatus
            new UserNotFoundException('scott')      || HttpStatus.NOT_FOUND
            new DuplicateUsernameException('scott') || HttpStatus.CONFLICT
            new RuntimeException('error')           || HttpStatus.INTERNAL_SERVER_ERROR
    }
}
