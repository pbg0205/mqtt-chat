package com.example.chatsubject.account.exception.advice;

import com.example.chatsubject.account.exception.DuplicatedUserException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SignUpControllerAdvice {

    @ExceptionHandler(DuplicatedUserException.class)
    public void handleDuplicatedUserException(Model model, Exception exception) {
        model.addAttribute("error", exception);
    }

}
