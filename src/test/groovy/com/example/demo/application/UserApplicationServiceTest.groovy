package com.example.demo.application

import com.example.demo.application.command.CreateUserCommand
import com.example.demo.application.command.DeleteUserCommand
import com.example.demo.application.exception.DuplicateUsernameException
import com.example.demo.application.exception.UserNotFoundException
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import spock.lang.Specification

class UserApplicationServiceTest extends Specification {
    UserApplicationService service

    UserRepository userRepository = Mock()

    void setup() {
        service = new UserApplicationService(userRepository)
    }

    def 'We can create user when given create command'() {
        given:
            def command = new CreateUserCommand(name: 'scott', email: 'scott@demo.com', phone: '+1(430)1234 1234')
            userRepository.findByName(_ as String) >> Optional.empty()
        when:
            service.save(command)
        then:
            1 * userRepository.save({
                verifyAll(it, User) {
                    it.name == command.name
                    it.email == command.email
                    it.phone == command.phone
                }
            })
    }

    def 'We can not create user when given create command and user exists'() {
        given:
            def command = new CreateUserCommand(name: 'scott', email: 'scott@demo.com', phone: '+1(430)1234 1234')
            userRepository.findByName(_ as String) >> Optional.of(new User())
        when:
            service.save(command)
        then:
            thrown(DuplicateUsernameException)
    }

    def 'We delete user when given delete command'() {
        given:
            def command = new DeleteUserCommand(name: 'scott')
        when:
            service.delete(command)
        then:
            1 * userRepository.delete({
                verifyAll(it, User) {
                    it.name == command.getName()
                }
            })
    }

    def 'We can find all users'() {
        given:
            userRepository.findAll() >> [new User(name: 'scott', email: 'scott@demo.com', phone: '+1(430)1234 1234')]
        when:
            def users = service.findAll()
        then:
            users.size() == 1
            users.get(0).name == 'scott'
    }

    def 'We can find any user when user not exists'() {
        given:
            userRepository.findAll() >> []
        expect:
            service.findAll().size() == 0
    }

    def 'We can find user by name'() {
        given:
            userRepository.findByName(_ as String) >> Optional.of(new User(name: 'scott', email: 'scott@demo.com', phone: '+1(430)1234 1234'))
        when:
            def user = service.findByName('scott')
        then:
            user.name == 'scott'
    }

    def 'We can not find user by name when user not exists'() {
        given:
            userRepository.findByName(_ as String) >> Optional.empty()
        when:
            service.findByName('scott')
        then:
            thrown(UserNotFoundException)
    }
}
