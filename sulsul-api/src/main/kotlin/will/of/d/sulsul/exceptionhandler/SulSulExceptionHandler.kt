package will.of.d.sulsul.exceptionhandler

import jakarta.validation.ConstraintViolationException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import will.of.d.sulsul.exception.Unauthorized
import will.of.d.sulsul.exception.UserNotFoundException

@RestControllerAdvice
class SulSulExceptionHandler {

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

    @ExceptionHandler(Exception::class)
    fun exception(): ResponseEntity<Any> {
        return ResponseEntity.internalServerError().build()
    }
}
