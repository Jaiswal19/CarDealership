package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class bidHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String name;
   private String email;
   private int bidPrice;
   private String customerName;
   public bidHistory(String name, String email, int bidPrice,String customerName) {
	   this.name = name;
	   this.email = email;
	   this.bidPrice = bidPrice;
	   this.customerName=customerName;
   }
   public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public bidHistory() {
   }
public int getCarId() {
	return id;
}
public void setCarId(int carId) {
	this.id = carId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getBidPrice() {
	return bidPrice;
}
public void setBidPrice(int bidPrice) {
	this.bidPrice = bidPrice;
}
}
