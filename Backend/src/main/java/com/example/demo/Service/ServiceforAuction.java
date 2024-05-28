package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.demo.UserDefinedExceptions.emptyAuctionList;
import com.example.demo.UserDefinedExceptions.NoCarExcptn;
import com.example.demo.UserDefinedExceptions.SufficientCarDataforAuction;
import com.example.demo.model.Cars;
@Service
public class ServiceforAuction {

	@Autowired
    private CrudRepository<Cars,Integer> repo;
         // Auction
    
    public Cars Addcar(Cars cr)
    {
    	try {
    		if(cr.getCarname().isBlank() || cr.getPrice()>0 || cr.getModelYear().isBlank())
    			throw new SufficientCarDataforAuction("Provide the mandatory detail for Car to be in Auction");
    	}
    	catch(Exception ex){
           System.out.println(ex.getMessage());
    	}
    	return (Cars) repo.save(cr);   
    }
    public List<Cars> ShowCars() {
        List<Cars> carsList = (List<Cars>) repo.findAll();
        if (carsList.isEmpty()) {
            throw new emptyAuctionList("No car is available for auction");
        }
        return carsList;
    }
    public Cars Findcar(int id)
    {
 	   Cars cr=(Cars) repo.findById(id).orElse(null);
 	   if(cr==null)
 	   {
 		   throw new NoCarExcptn("No Such Car Exists for Auction");
 	   }
 		 else
 		 {
 			 return cr;
 		 }		 
    }
    public String dltCar(int id)
    {
 	   if(repo.findById(id)!=null)
 	   {
 		   repo.deleteById(id);
 		   return "Car Gets Deleted";
 	   }
 	   else
 		  return "No such Car Exist";
    }
   public String updtCar(int id,String Carname , String Capacity)
   {
	   Cars curr=repo.findById(id).orElse(null);
	   if(curr!=null)
	   {
		     curr.setCarname(Carname);
		     curr.setEngineCapacity(Capacity);
		       repo.save(curr);
		   return " Entry Updated ";
	   }
	   else
		   return "No such Car Exist";
   }  
}
