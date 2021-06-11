package com.stockquotemanager.service;

import static com.stockquotemanager.utils.StockQuoteConstraints.ERROR_NON_EXISTENT_STOCK;
import static com.stockquotemanager.utils.StockQuoteConstraints.ERROR_NULL_CREATE_STOCK_QUOTE;
import static com.stockquotemanager.utils.StockQuoteConstraints.STOCK_MANAGER_GET_STOCKS_URL;
import static com.stockquotemanager.utils.StockQuoteMapper.mapToDTO;
import static com.stockquotemanager.utils.StockQuoteMapper.mapToEntity;
import static java.util.Objects.isNull;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.stockquotemanager.dto.CreateStockQuoteDTO;
import com.stockquotemanager.dto.StockDTO;
import com.stockquotemanager.dto.StockQuoteDTO;
import com.stockquotemanager.entity.StockQuote;
import com.stockquotemanager.repository.StockQuoteRepository;
import com.stockquotemanager.utils.StockCache;

import lombok.Data;

@Service
@Transactional
@Data
public class StockQuoteService {

	@Autowired
	private StockQuoteRepository repository;

	@Autowired
	private StockCache stockCache;

	public void createStockQuote(CreateStockQuoteDTO dto) {
		this.validateCreateStockDTO(dto);
		StockQuote stockQuote = mapToEntity(dto);
		this.repository.save(stockQuote);
	}

	public Optional<StockQuoteDTO> findById(String quoteId) {
		Optional<StockQuote> stockQuote = this.repository.findById(quoteId);
		return stockQuote.isPresent() ? Optional.of(mapToDTO(stockQuote.get())) : empty();
	}

	public List<StockQuoteDTO> findAll() {
		List<StockQuote> stockQuoteList = this.repository.findAll();
		return stockQuoteList.stream().map((stockQuote) -> mapToDTO(stockQuote)).collect(toList());
	}

	private void validateCreateStockDTO(CreateStockQuoteDTO dto) {
		if (isNull(dto))
			throw new IllegalArgumentException(ERROR_NULL_CREATE_STOCK_QUOTE);

		if (!stockExists(dto.getId()))
			throw new IllegalArgumentException(ERROR_NON_EXISTENT_STOCK);
	}

	private boolean stockExists(String stockId) {
		List<String> availableStockIds = !stockCache.isStockCacheEmpty() ? stockCache.getStockIds() : requestAndAddToCacheAvailableStockIds();
		return availableStockIds.stream().anyMatch(stock -> stock.equals(stockId));
	}

	private List<String> requestAndAddToCacheAvailableStockIds() {
		
		ResponseEntity<? extends ArrayList> responseEntity = new RestTemplate()
				.getForEntity(STOCK_MANAGER_GET_STOCKS_URL, Lists.<StockDTO>newArrayList().getClass());

		List<String> availableStockIds = new ObjectMapper()
				.convertValue(responseEntity.getBody(), new TypeReference<List<StockDTO>>() {
				}).stream().map(StockDTO::getId).collect(toList());

		stockCache.addStockIds(availableStockIds);

		return availableStockIds;
	}

}
