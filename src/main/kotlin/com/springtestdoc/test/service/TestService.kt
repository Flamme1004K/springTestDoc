package com.springtestdoc.test.service

import com.springtestdoc.test.controller.ResponseTest
import org.springframework.stereotype.Service

@Service
class TestService {

    val list = listOf<ResponseTest>(
        ResponseTest(1L, "하이", "하이타입"),
        ResponseTest(1L, null, "하이타입"),
        ResponseTest(1L, "하이", null),
        ResponseTest(1L, null, null),
    )

    fun getTestData(id : Long) = list.random()
}