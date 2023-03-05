package com.besysoft.practica.controllers.handlercontroller;

import com.besysoft.practica.dto.ExceptionDTO;
import com.besysoft.practica.exceptions.GeneroDoesntExistsException;
import com.besysoft.practica.exceptions.GeneroExistsException;
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
        log.info("advice: "+ex.getMessage());
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO exceptionHandler(GeneroDoesntExistsException ex){
        log.info("advice: "+ex.getMessage());
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO exceptionHandler(GeneroExistsException ex){
        log.info("advice: "+ex.getMessage());
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }
}
