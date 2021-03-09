package com.example.demo.api

import com.example.demo.WebMvcTestBase
import com.example.demo.api.request.CreateUserRequest
import com.example.demo.application.UserApplicationService
import com.example.demo.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.hasSize
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserApiTest extends WebMvcTestBase {
    @Autowired
    UserApplicationService userApplicationService

    def 'We can find all users'() {
        given:
            userApplicationService.findAll() >> [new User(name: 'scott', email: 'scott@test.com', phone: '+1(430)1234 1234')]
        expect:
            mockMvc.perform(httpGet('/users'))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath('$.[0].name', equalTo('scott')))
                    .andExpect(jsonPath('$.[0].email', equalTo('scott@test.com')))
                    .andExpect(jsonPath('$.[0].phone', equalTo('+1(430)1234 1234')))
    }

    def 'We can get empty list when user not exists'() {
        given:
            userApplicationService.findAll() >> []
        expect:
            mockMvc.perform(httpGet('/users'))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath('$', hasSize(0)))
    }

    def 'We create user when given user details'() {
        given:
            userApplicationService.findByName(_ as String) >> Optional.empty()
            def request = new CreateUserRequest(name: 'scott', email: 'scott@test.com', phone: '+1(430)12341234')
        expect:
            mockMvc.perform(httpPost('/users').content(gson.toJson(request)))
                    .andExpect(status().isCreated())
    }

    def 'We can delete user by name'() {
        expect:
            mockMvc.perform(httpDelete('/users/scott'))
                    .andExpect(status().isAccepted())
    }

    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        UserApplicationService helloWorldService() {
            return detachedMockFactory.Stub(UserApplicationService)
        }
    }
}
