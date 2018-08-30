package morabem.controllers;

import org.hibernate.LazyInitializationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorsController {

    @ExceptionHandler(BindException.class)
    public @ResponseBody String bindError(BindException e, HttpServletRequest request, HttpServletResponse response) {
        return "BindException: " + e.getMessage() + "\r\n";
    }

    @ExceptionHandler(TemplateInputException.class)
    public @ResponseBody String templateInputException(TemplateInputException e, HttpServletRequest request, HttpServletResponse response) {
        return "TemplateInputException: " + e.getMessage() + "\r\n";
    }

        /*@ExceptionHandler(LazyInitializationException.class)
        public @ResponseBody String lazyInitializationException(LazyInitializationException e, HttpServletRequest request, HttpServletResponse response) {
            return "LazyInitializationException: " + e.getMessage() + "\r\n";
        }*/
}
