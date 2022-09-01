package com.nagarro.account.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nagarro.account.model.StatementRequest;
import com.nagarro.account.util.Constant;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        logger.error("Exception occured", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
    
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final SQLException exception, final Model model) {
        logger.error("Exception occured", exception);
        String errorMessage = (exception != null ? exception.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ConstraintViolationException(final ConstraintViolationException exception, final Model model) {
        logger.error("Exception occured", exception);
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
        	errors.add( violation.getMessage());
        }
        model.addAttribute("statementRequest", new StatementRequest());
        model.addAttribute("errors", errors);
        return Constant.VIEW_ACCOUNT_STATEMENT;
    }

}