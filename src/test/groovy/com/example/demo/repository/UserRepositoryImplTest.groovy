package com.example.demo.repository

import com.example.demo.DataJpaTestBase
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import

@Import(UserRepositoryImpl)
class UserRepositoryImplTest extends DataJpaTestBase {
    @Autowired
    UserRepository userRepository

    def 'We can save user when given user details'() {
        given:
            def user = new User(name: 'scott', email: 'scott@demo.com', phone: '+1(430)1234 1234')
        when:
            userRepository.save(user)
        then:
            userRepository.findByName('scott').isPresent()
    }

    def 'We can find all users'() {
        given:
            testEntityManager.persist(new UserPo(name: 'scott1', email: 'scott1@test.com', phone: '+1(430)1234 1234'))
            testEntityManager.persist(new UserPo(name: 'scott2', email: 'scott2@test.com', phone: '+1(430)1234 1234'))
            testEntityManager.persist(new UserPo(name: 'scott3', email: 'scott3@test.com', phone: '+1(430)1234 1234'))
        when:
            def users = userRepository.findAll()
        then:
            users.size() == 3
            users.get(0).name == 'scott1'
            users.get(0).email == 'scott1@test.com'
            users.get(0).phone == '+1(430)1234 1234'
            users.get(1).name == 'scott2'
            users.get(1).email == 'scott2@test.com'
            users.get(1).phone == '+1(430)1234 1234'
            users.get(2).name == 'scott3'
            users.get(2).email == 'scott3@test.com'
            users.get(2).phone == '+1(430)1234 1234'
    }

    def 'We can delete user by name'() {
        given:
            testEntityManager.persist(new UserPo(name: 'scott', email: 'scott@test.com', phone: '+1(430)1234 1234'))
            testEntityManager.persist(new UserPo(name: 'tiger', email: 'tiger@test.com', phone: '+1(430)1234 1234'))
        when:
            userRepository.delete(new User(name: 'tiger'))
        then:
            userRepository.findAll().size() == 1
    }

    def 'We can delete user by name that ignore case'() {
        given:
            testEntityManager.persist(new UserPo(name: 'scott', email: 'scott@test.com', phone: '+1(430)1234 1234'))
            testEntityManager.persist(new UserPo(name: 'tiger', email: 'tiger@test.com', phone: '+1(430)1234 1234'))
        when:
            userRepository.delete(new User(name: 'TiGer'))
        then:
            userRepository.findAll().size() == 1
    }

    def 'We can delete nothing when given name of user that user not exists'() {
        given:
            testEntityManager.persist(new UserPo(name: 'scott', email: 'scott@test.com', phone: '+1(430)1234 1234'))
            testEntityManager.persist(new UserPo(name: 'tiger', email: 'tiger@test.com', phone: '+1(430)1234 1234'))
        when:
            userRepository.delete(new User(name: 'brown'))
        then:
            userRepository.findAll().size() == 2
    }

    def 'We can find user by name'() {
        given:
            testEntityManager.persist(new UserPo(name: 'scott', email: 'scott@test.com', phone: '+1(430)1234 1234'))
            testEntityManager.persist(new UserPo(name: 'tiger', email: 'tiger@test.com', phone: '+1(430)1234 1234'))
        when:
            def user = userRepository.findByName('scott').get()
        then:
            user.name == 'scott'
            user.email == 'scott@test.com'
            user.phone == '+1(430)1234 1234'
    }
    def 'We can find user by name that ignore case'() {
        given:
            testEntityManager.persist(new UserPo(name: 'scott', email: 'scott@test.com', phone: '+1(430)1234 1234'))
            testEntityManager.persist(new UserPo(name: 'tiger', email: 'tiger@test.com', phone: '+1(430)1234 1234'))
        when:
            def userOpt = userRepository.findByName('ScoTt')
        then:
            userOpt.isPresent()
            def user = userOpt.get()
            user.name == 'scott'
            user.email == 'scott@test.com'
            user.phone == '+1(430)1234 1234'
    }

    def 'We can find noting when given name of user that user not exists'() {
        given:
            testEntityManager.persist(new UserPo(name: 'scott', email: 'scott@test.com', phone: '+1(430)1234 1234'))
            testEntityManager.persist(new UserPo(name: 'tiger', email: 'tiger@test.com', phone: '+1(430)1234 1234'))
        expect:
            userRepository.findByName('brown').isEmpty()
    }
}
