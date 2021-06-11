package com.stockquotemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockquotemanager.entity.StockQuote;

public interface StockQuoteRepository extends JpaRepository<StockQuote, Long>{
	
	public Optional<StockQuote> findById(String id);

}
