package in.arjun.userservice.exception;

import in.arjun.userservice.domain.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ApiResponse<Void> handleUserNotFoundException(UserNotFoundException e) {
        return ApiResponse.notFound(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception e) {
        return ApiResponse.internalServerError(e.getMessage());
    }
}
