package com.example.demo.UserDefinedExceptions;

public class emptyAuctionList extends RuntimeException{
    private String message;
    public emptyAuctionList(String message){
    	this.setMessage(message);
    }
    public emptyAuctionList(){}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Auction List is empty";
	} 
}
