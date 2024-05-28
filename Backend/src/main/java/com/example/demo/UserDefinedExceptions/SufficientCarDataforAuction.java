package com.example.demo.UserDefinedExceptions;

public class SufficientCarDataforAuction extends RuntimeException{
	public SufficientCarDataforAuction(String message)
	{
      super(message);
	}
}
