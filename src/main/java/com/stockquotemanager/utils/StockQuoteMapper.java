package com.stockquotemanager.utils;

import static java.util.Objects.isNull;

import com.stockquotemanager.dto.CreateStockQuoteDTO;
import com.stockquotemanager.dto.StockQuoteDTO;
import com.stockquotemanager.entity.StockQuote;

//@Mapper
public class StockQuoteMapper {
	
	public static StockQuote mapToEntity(CreateStockQuoteDTO dto) {
		if(isNull(dto))
			return null;
		return StockQuote.builder().id(dto.getId()).quotes(dto.getQuotes()).build();
	}
	
	public static StockQuoteDTO mapToDTO(StockQuote entity) {
		if(isNull(entity))
			return null;
		return StockQuoteDTO.builder().id(entity.getId()).quotes(entity.getQuotes()).build();
	}
}
