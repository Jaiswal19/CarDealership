package com.example.demo.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.UserDefinedExceptions.NoCarExcptn;
import com.example.demo.UserDefinedExceptions.NoRecordForBid;
import com.example.demo.UserDefinedExceptions.ReceiptException;
import com.example.demo.UserDefinedExceptions.SufficientCarDataforAuction;
import com.example.demo.UserDefinedExceptions.emptyAuctionList;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(emptyAuctionList.class)
    public ResponseEntity<String> handleNotFoundException(emptyAuctionList ex) {
    	System.out.println(ex.getMessage());
    	  return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SufficientCarDataforAuction.class)
    public ResponseEntity<String> handleException(SufficientCarDataforAuction ex) {
    	System.out.println(ex.getMessage());
    	  return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoCarExcptn.class)
    public ResponseEntity<String> handleExceptionforSearch(NoCarExcptn ex) {
    	System.out.println(ex.getMessage());
    	  return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ReceiptException.class)
    public ResponseEntity<String> handleExceptionforReceipt(ReceiptException ex) {
    	System.out.println(ex.getMessage());
    	  return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoRecordForBid.class)
    public ResponseEntity<String> handleExceptionforHistory(NoRecordForBid ex) {
    	System.out.println(ex.getMessage());
    	  return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
