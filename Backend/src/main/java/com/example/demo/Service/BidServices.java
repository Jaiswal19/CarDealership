package com.example.demo.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BidPrice;
import com.example.demo.model.Cars;
import com.example.demo.model.bidHistory;
import com.example.demo.repository.BidRepository;
import com.example.demo.repository.CrudRepository;
import com.example.demo.repository.bidHistoryRepo;

@Service
public class BidServices {
 @Autowired
   private BidRepository repository;
     @Autowired
	  public CrudRepository Ac;
     @Autowired
      public bidHistoryRepo bHR;
       @Autowired 
       UserServiceImpl uSI;
     public String AddBid(String Customername, int CarId,int CurrentBid, String email)
     {
  	   BidPrice currBid= repository.findById(CarId).orElse(null);
  	 
  	   if(currBid != null)
  	   {
  		
  		 Cars crs= Ac.findById(CarId).orElse(null);
  		 if(CurrentBid > currBid.getMaxbid())
  		 {
  			 currBid.setCustomerName(Customername);
  		     currBid.setMaxbid(CurrentBid);
  		     if(crs!=null) {
  		     crs.setPrice(CurrentBid);
  		     Ac.save(crs);
  		     }
  			 currBid.setEmail(email);
  			 System.out.println(uSI.getValidate(email).getFirstName());
  			 bidHistory bH=new bidHistory(Customername,email,CurrentBid,uSI.getValidate(email).getFirstName());
  		     repository.save(currBid);
  		     bHR.save(bH);
  		     System.out.println(bH);
  		     return "Bid Applied";
  		 }
  		 else
  		 {
  			 return "Your amount for bid is not sufficient";
  		 }
  	    
  	  }
  	  else
  	  {
  		 
  		   Intialize(CarId,CurrentBid,Customername,email);
  		   Cars crs= Ac.findById(CarId).orElse(null);
  		   crs.setPrice(CurrentBid);
  		     Ac.save(crs);
  		    bidHistory bH=new bidHistory(Customername,email,CurrentBid,uSI.getValidate(email).getFirstName());
  		    System.out.println(bH);
  		   bHR.save(bH);
  		   return "value updated";
  	  }
     }
   public String Intialize(int id ,int CurrentBid, String customerName, String name)
   {
	   
	   repository.save(new BidPrice(id,CurrentBid,customerName,"blank"));
	   return "Value Updated";
   }
   
   public List<BidPrice> getAllBid(String username)
   {
	   return repository.findAllByEmail(username);
   }
}
