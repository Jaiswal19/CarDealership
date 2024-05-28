package com.example.demo.UserDefinedExceptions;

public class NoRecordForBid extends RuntimeException{
     public NoRecordForBid(String mssg)
     {
    	 super(mssg);
     }
}
