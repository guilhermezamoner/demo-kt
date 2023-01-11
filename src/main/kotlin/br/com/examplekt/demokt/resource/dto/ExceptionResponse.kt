package br.com.examplekt.demokt.resource.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import java.util.Date

@JsonInclude(NON_NULL)
data class ExceptionResponse(
   val status: Int,
   val error: String,
   val details: List<String?>,
   val timeStamp: Date,
   val path: String?
)
