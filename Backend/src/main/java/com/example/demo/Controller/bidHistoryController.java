package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.bidHistoryService;
import com.example.demo.model.Cars;
import com.example.demo.model.bidHistory;

@RestController
@RequestMapping("/history")
public class bidHistoryController {
      @Autowired
    private bidHistoryService bHS;
      @CrossOrigin
    @PostMapping("/getHistory")
    List<bidHistory> getAllHistory( @RequestBody Cars bH)
    { 
    	System.out.println(bH.getCarname());
    	return bHS.getHistory(bH.getCarname());
    }
}
