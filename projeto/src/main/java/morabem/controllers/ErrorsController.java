package morabem.controllers;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorsController {

    @ExceptionHandler(BindException.class)
    public @ResponseBody String bindError(BindException e, HttpServletRequest request, HttpServletResponse response) {
        return "\r\nBindException: " + e.getMessage() + "\r\n";
    }
}
