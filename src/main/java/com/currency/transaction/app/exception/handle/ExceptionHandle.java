package com.currency.transaction.app.exception.handle;

import com.currency.transaction.app.beans.response.MessageClientResponse;
import com.currency.transaction.app.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MessageClientResponse> customException(CustomException ex) {
        MessageClientResponse clientResponse = ex.getClientResponse();
        return new ResponseEntity<>(clientResponse, HttpStatus.BAD_REQUEST);
    }
}
