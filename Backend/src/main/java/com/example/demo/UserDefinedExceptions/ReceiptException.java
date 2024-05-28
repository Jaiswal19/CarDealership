package com.example.demo.UserDefinedExceptions;

public class ReceiptException extends RuntimeException{
    public ReceiptException(String mssg)
    {
    	super(mssg);
    }
}
