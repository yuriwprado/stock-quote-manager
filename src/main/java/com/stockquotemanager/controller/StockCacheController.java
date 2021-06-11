package com.stockquotemanager.controller;

import static com.stockquotemanager.utils.StockQuoteConstraints.STOCK_CACHE_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockquotemanager.utils.StockCache;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping(path = STOCK_CACHE_PATH)
public class StockCacheController {
	
	@Autowired
	private StockCache stockCache;
	
	@DeleteMapping
	public ResponseEntity<Void> delete() {
		stockCache.clearStockIds();
		return ResponseEntity.ok().build();
	}

}
