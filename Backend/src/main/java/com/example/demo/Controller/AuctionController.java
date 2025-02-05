package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.ServiceforAuction;
import com.example.demo.model.Cars;
@CrossOrigin
@RestController
@RequestMapping("/Auction")
public class AuctionController {
   @Autowired
    private ServiceforAuction sA;
    @CrossOrigin
    @GetMapping("/getAllCar")
   public List<Cars> DisplayCars(){
	   return sA.ShowCars();
   }
    @CrossOrigin
    @GetMapping("/addCar")
    public Cars addcrr(@RequestBody Cars car)
    {
 	   return sA.Addcar(new Cars(car.getId(),car.getImage(),car.getCarname(),car.getEngineCapacity(),car.getPrice(),car.getFueltype(),car.getMileage(),car.getPower(),car.getTorque(),car.getTransmission(),car.getModelYear(),car.getKmsDriven()));
    }
    @CrossOrigin
   @GetMapping("/find")
   public Cars getCar(@RequestParam int id)
   {
	   return sA.Findcar(id);
   }
    @CrossOrigin
   @GetMapping("/dlt")
   public String remove(@RequestParam int id)
   {
	   return sA.dltCar(id);
   }
    @CrossOrigin
   @GetMapping("/updt")
   public String update(@RequestParam int id,@RequestParam String Carname,String Engine)
   {
	   return sA.updtCar(id, Carname, Engine);
   }
}
