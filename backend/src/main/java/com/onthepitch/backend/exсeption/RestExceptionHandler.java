package com.onthepitch.backend.exсeption;

import com.onthepitch.shared.model.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchUserException.class)
    protected ResponseEntity<MessageResponse> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new MessageResponse("There is no such user"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchPostException.class)
    protected ResponseEntity<MessageResponse> handleIsNoSuchPostException(){
        return new ResponseEntity<>(new MessageResponse("There is no such post"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchClubException.class)
    protected ResponseEntity<MessageResponse> handleIsNoSuchClubException(){
        return new ResponseEntity<>(new MessageResponse("There is no such club"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailIsAlreadyExists.class)
    protected ResponseEntity<?> handleEmailIsAlreadyExists(){
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!!"));
    }

    @ExceptionHandler(UsernameIsAlreadyTaken.class)
    protected ResponseEntity<?> handleUsernameIsAlreadyTaken(){
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!!"));
    }

    @ExceptionHandler(UserBannedException.class)
    protected ResponseEntity<MessageResponse> handleUserBannedException(){
        return new ResponseEntity<>(new MessageResponse("You banned"), HttpStatus.FORBIDDEN);
    }
}
