package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.UserDefinedExceptions.NoRecordForBid;
import com.example.demo.model.bidHistory;
import com.example.demo.repository.bidHistoryRepo;
@Service
public class bidHistoryService {
	@Autowired
    private bidHistoryRepo bH;
   public List<bidHistory> getHistory(String carName)
   {
	   List<bidHistory> hist=bH.findAllByName(carName);
	   if(hist.isEmpty())
		   throw new NoRecordForBid("No bid is placed till now");
	   return hist;
   }
}
