package com.currency.transaction.app.exception;


import com.currency.transaction.app.beans.response.MessageClientResponse;
import lombok.Data;

@Data
public class CustomException extends RuntimeException{

    private MessageClientResponse clientResponse;

    public CustomException(MessageClientResponse clientResponse ) {
        this.clientResponse = clientResponse;
    }
}
