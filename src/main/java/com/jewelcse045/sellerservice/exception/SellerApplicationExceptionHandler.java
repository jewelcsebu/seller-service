package com.jewelcse045.sellerservice.exception;


import com.jewelcse045.sellerservice.util.MethodUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SellerApplicationExceptionHandler {

    @ExceptionHandler(value=ApplicationException.class)
    public ResponseEntity<String> applicationException(ApplicationException exception){
        HttpStatus status=HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status,exception),status);
    }

    @ExceptionHandler(value=SellerNotFoundException.class)
    public ResponseEntity<String> productNotFoundExceptionException(SellerNotFoundException exception){
        HttpStatus status=HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status,exception),status);
    }

}
