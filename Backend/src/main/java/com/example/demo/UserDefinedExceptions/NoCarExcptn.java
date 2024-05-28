package com.example.demo.UserDefinedExceptions;

public class NoCarExcptn extends RuntimeException{
	public NoCarExcptn(String message)
	{
      super(message);
	}
}
