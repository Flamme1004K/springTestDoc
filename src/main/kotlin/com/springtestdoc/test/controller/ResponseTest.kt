package com.springtestdoc.test.controller

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseTest(
    @get: JsonProperty("id") val id : Long,
    @get: JsonProperty("name") val name : String?,
    @get: JsonProperty("type") val type : String?
)
