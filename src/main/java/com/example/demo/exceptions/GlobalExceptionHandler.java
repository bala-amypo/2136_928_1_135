// package com.example.demo.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(ResourceNotFoundException.class)
//     public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
//         return ResponseEntity
//                 .status(HttpStatus.NOT_FOUND)
//                 .body(ex.getMessage());
//     }

//     @ExceptionHandler(BadRequestException.class)
//     public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
//         return ResponseEntity
//                 .status(HttpStatus.BAD_REQUEST)
//                 .body(ex.getMessage());
//     }

//     @ExceptionHandler(OperationNotAllowedException.class)
//     public ResponseEntity<String> handleForbidden(OperationNotAllowedException ex) {
//         return ResponseEntity
//                 .status(HttpStatus.FORBIDDEN)
//                 .body(ex.getMessage());
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<String> handleOther(Exception ex) {
//         return ResponseEntity
//                 .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .body(ex.getMessage());
//     }
// }


package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return ResponseEntity.status(404).body(body);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return ResponseEntity.status(400).body(body);
    }
}