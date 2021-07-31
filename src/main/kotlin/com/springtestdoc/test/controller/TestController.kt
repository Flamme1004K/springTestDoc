package com.springtestdoc.test.controller

import com.springtestdoc.test.service.TestService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testService: TestService
) {

    @PostMapping("/test")
    fun callTest(@RequestBody requestTest: RequestTest) = testService.getTestData(requestTest.id)
}