package ir.techco.review.exception.handler;

import ir.techco.review.exception.NotAllowedException;
import ir.techco.review.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestResponseExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleArgumentException(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        List<String> fieldErrors = result.getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage()).toList();
        String message = fieldErrors.stream().findFirst().orElse("");
        ErrorResponse response = new ErrorResponse(message,ErrorCode.InternalErrorCode, HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalError(Exception ex){
       String message = "Internal server error occurred";
       ErrorResponse response = new ErrorResponse(message,ErrorCode.InternalErrorCode,HttpStatus.INTERNAL_SERVER_ERROR.name());
       return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<Object> handleNotAllowedException(NotAllowedException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage(),ErrorCode.NotAllowedErrorCode,HttpStatus.FORBIDDEN.name());
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage(),ErrorCode.NotFoundErrorCode,HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


}
