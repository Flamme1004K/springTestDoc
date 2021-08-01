package com.springtestdoc.testController

import com.fasterxml.jackson.databind.ObjectMapper
import com.springtestdoc.restDoc.ApiDocumentUtils.Companion.documentRequest
import com.springtestdoc.restDoc.ApiDocumentUtils.Companion.documentResponse
import com.springtestdoc.test.controller.RequestTest
import com.springtestdoc.test.controller.ResponseTest
import com.springtestdoc.test.controller.TestController
import com.springtestdoc.test.service.TestService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(TestController::class)
@AutoConfigureRestDocs
class TestDocController{

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper
    @MockBean
    private lateinit var testService : TestService

    @Test
    fun callTest() {
        //given
        val responseObject = ResponseTest(1L, "테스트", "")
        given(testService.getTestData(1L)).willReturn(responseObject)

        //when
        val result = this.mockMvc.perform(
            post("/test")
                .content(objectMapper.writeValueAsString(RequestTest(1L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
        //then
        result.andExpect(status().isOk)
            .andDo(document("{class-name}/{method-name}",
                requestFields(
                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                ),
                responseFields(
                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                    fieldWithPath("type").type(JsonFieldType.STRING).description("타입"),
                )
            ))

    }

}