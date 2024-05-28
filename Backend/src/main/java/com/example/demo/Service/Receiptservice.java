package com.example.demo.Service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.UserDefinedExceptions.NoCarExcptn;
import com.example.demo.UserDefinedExceptions.ReceiptException;
import com.example.demo.model.BidPrice;
import com.example.demo.model.Cars;
import com.example.demo.model.Receipt;
import com.example.demo.model.User;


@Service
public class Receiptservice {
	
	@Autowired
	public UserServiceImpl urs;
	@Autowired
	public BidServices bd;
	@Autowired
	public ServiceforAuction Ac;
   public List<Receipt> getreceipt(String email)
   {
	   try {
	    List<BidPrice> Alllist=bd.getAllBid(email);
	    if(Alllist.isEmpty())
	    throw new ReceiptException("No Bid placed till now");
	    List<Receipt> ans= new ArrayList<Receipt>();
	    User user=urs.getValidate(email);
	  for( BidPrice value :Alllist)
	  {
		  Receipt rp= new Receipt();
		  Cars crs= Ac.Findcar(value.getCarId());
		  rp.setFirstName(user.getFirstName());
		  rp.setLastName(user.getLastName());
		     if(crs!=null)
		  rp.setCarName(crs.getCarname());
		  rp.setEmail(email);
		  rp.setSalePrice(value.getMaxbid());
		  ans.add(rp);
	  }
	   return ans;
      }
	 catch(Exception ex)
	 {
		 ex.getMessage();
		 throw new ReceiptException("No data is available for this car");
	 }
}
   
}
