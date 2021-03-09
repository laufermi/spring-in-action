package com.example.demo

import com.example.demo.api.UserApi
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@WebMvcTest(controllers = [UserApi])
class WebMvcTestBase extends Specification {
    @Autowired
    MockMvc mockMvc

    def gson = new Gson()

    static MockHttpServletRequestBuilder httpGet(String path) {
        return get(URI.create(path))
    }

    static MockHttpServletRequestBuilder httpPost(String path) {
        return post(URI.create(path))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding('UTF-8')
    }

    static MockHttpServletRequestBuilder httpDelete(String path) {
        return delete(URI.create(path))
    }
}
