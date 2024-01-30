package com.app.reservasR.application.Exception;

import com.app.reservasR.application.lasting.EMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotFoundException extends Exception{

    private final HttpStatus status;
    private final String message;

    public EntityNotFoundException(EMessage eMessage){
        this.status = eMessage.getStatus();
        this.message = eMessage.getMessage();
    }
}
