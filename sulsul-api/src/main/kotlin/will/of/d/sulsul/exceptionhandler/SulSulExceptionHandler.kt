package will.of.d.sulsul.exceptionhandler

import jakarta.validation.ConstraintViolationException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import will.of.d.sulsul.exception.ReportNotFoundException
import will.of.d.sulsul.exception.SulsulValidException
import will.of.d.sulsul.exception.Unauthorized
import will.of.d.sulsul.exception.UserNotFoundException
import will.of.d.sulsul.log.Logger

@RestControllerAdvice
class SulSulExceptionHandler {

    companion object : Logger

    @ExceptionHandler(SulsulValidException::class, MissingServletRequestParameterException::class)
    fun badRequestException(e: Exception): ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun invalidRequestException(exception: ConstraintViolationException): ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(exception.message)
    }

    @ExceptionHandler(NotFoundException::class)
    fun responseNotFoundException(exception: NotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFoundException(exception: UserNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @ExceptionHandler(Unauthorized::class)
    fun unauthorizedException(exception: Unauthorized): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.message)
    }

    @ExceptionHandler(ReportNotFoundException::class)
    fun reportNotFoundException(exception: ReportNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): ResponseEntity<Any> {
        log.error("Unexpected error", e)
        return ResponseEntity.internalServerError().body(e.message)
    }
}
