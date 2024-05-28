package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.bidHistory;

public interface bidHistoryRepo extends JpaRepository<bidHistory, Integer>{
   List<bidHistory> findAllByName(String carName);
}
