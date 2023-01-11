package br.com.examplekt.demokt.resource

import br.com.examplekt.demokt.resource.dto.ExceptionResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ExceptionResponse?>? {
        val details: MutableList<String?> = ArrayList()
        for (error in ex.bindingResult.allErrors) {
            details.add(error.defaultMessage)
        }
        val httpStatus = HttpStatus.BAD_REQUEST
        return ResponseEntity.status(httpStatus).body(
            ExceptionResponse(httpStatus.value(), httpStatus.reasonPhrase, details.toList(), Date(), request.requestURI.toString())
        )
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleNotFoundException(ex: Exception, request: HttpServletRequest): ResponseEntity<ExceptionResponse?>?{
        val httpStatus = HttpStatus.NOT_FOUND
        return ResponseEntity.status(httpStatus).body(
            ExceptionResponse(httpStatus.value(), httpStatus.reasonPhrase, listOf(ex.message), Date(), request.requestURI.toString())
        )
    }
}