package com.besysoft.practica.controllers.handlercontroller;

import com.besysoft.practica.dto.ExceptionDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
@Log4j2
public class ApiControllerAdvice {


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO exceptionHandler(MethodArgumentNotValidException ex){
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }
}
