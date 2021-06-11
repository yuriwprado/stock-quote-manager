package com.stockquotemanager.controller;

import static com.stockquotemanager.utils.StockQuoteConstraints.STOCK_QUOTE_PATH;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockquotemanager.dto.CreateStockQuoteDTO;
import com.stockquotemanager.dto.StockQuoteDTO;
import com.stockquotemanager.service.StockQuoteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping(path = STOCK_QUOTE_PATH)
public class StockQuoteController {

	@Autowired
	private StockQuoteService service;

	@PostMapping(consumes="application/json")
	public ResponseEntity<Void> createStockQuote(@RequestBody CreateStockQuoteDTO dto) {
		this.service.createStockQuote(dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/find/{quoteId}")
	public ResponseEntity<StockQuoteDTO> findById(@PathVariable String quoteId) {
		Optional<StockQuoteDTO> stockQuoteDTOOpt = this.service.findById(quoteId);
		if(!stockQuoteDTOOpt.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(stockQuoteDTOOpt.get());
	}

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<StockQuoteDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}

}
