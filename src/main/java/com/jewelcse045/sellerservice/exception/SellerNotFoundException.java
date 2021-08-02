package com.jewelcse045.sellerservice.exception;

public class SellerNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public SellerNotFoundException(String msg){

        super(msg);

    }
}
