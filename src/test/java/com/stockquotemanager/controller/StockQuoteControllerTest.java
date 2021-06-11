package com.stockquotemanager.controller;

import static com.stockquotemanager.utils.StockQuoteConstraints.STOCK_QUOTE_PATH;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.empty;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.stockquotemanager.dto.CreateStockQuoteDTO;
import com.stockquotemanager.dto.StockQuoteDTO;
import com.stockquotemanager.service.StockQuoteService;

@RunWith(SpringRunner.class)
@WebMvcTest(StockQuoteController.class)
public class StockQuoteControllerTest {
	
	private static final String QUOTE_VALUE = "35";
	private static final LocalDate QUOTE_DATE = LocalDate.of(2020, 3, 15);
	private static final String STOCK_ID = "STOCK_ID_1";
	
	@MockBean
	StockQuoteService stockQuoteService;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void create_SuccessTest() throws Exception {
		
		CreateStockQuoteDTO createDTO = buildCreateStockQuoteDTO();
		
		mvc.perform(
				MockMvcRequestBuilders.post(STOCK_QUOTE_PATH)
					.content(new Gson().toJson(createDTO))
					.contentType(APPLICATION_JSON)
					.characterEncoding(UTF_8.displayName())
					.accept(APPLICATION_JSON)
					)
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void create_NoParameterTest() throws Exception {

		CreateStockQuoteDTO createDTO = null;
		
		mvc.perform(
				MockMvcRequestBuilders.post(STOCK_QUOTE_PATH)
					.content(new Gson().toJson(createDTO))
					.contentType(APPLICATION_JSON)
					.characterEncoding(UTF_8.displayName())
					.accept(APPLICATION_JSON)
					)
		.andDo(print())
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void findById_SuccessTest() throws Exception {
		
		Mockito.when(stockQuoteService.findById(Mockito.any())).thenReturn(Optional.of(buildStockQuoteDTO()));
		
		mvc.perform(
				MockMvcRequestBuilders.get(STOCK_QUOTE_PATH + "/find/1")
					.accept(MediaType.APPLICATION_JSON)
					)
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void findById_NoParameterTest() throws Exception {
		
		mvc.perform(
				MockMvcRequestBuilders.get(STOCK_QUOTE_PATH + "/find/")
					.accept(MediaType.APPLICATION_JSON)
					)
		.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void findById_QuoteNotFoundTest() throws Exception {

		Mockito.when(stockQuoteService.findById(Mockito.any())).thenReturn(empty());
		
		mvc.perform(
				MockMvcRequestBuilders.get(STOCK_QUOTE_PATH + "/find/1")
					.accept(MediaType.APPLICATION_JSON)
					)
		.andDo(print())
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void findAll_SuccessTest() throws Exception {
		
		List<StockQuoteDTO> serviceReturnedList = Lists.newArrayList();
		serviceReturnedList.add(buildStockQuoteDTO());
		Mockito.when(stockQuoteService.findAll()).thenReturn(serviceReturnedList);
		
		mvc.perform(
				MockMvcRequestBuilders.get(STOCK_QUOTE_PATH + "/findAll")
					.accept(MediaType.APPLICATION_JSON)
					)
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	private CreateStockQuoteDTO buildCreateStockQuoteDTO() {
		return CreateStockQuoteDTO.builder().id(STOCK_ID).quotes(buildQuotes()).build();
	}
	
	private StockQuoteDTO buildStockQuoteDTO() {
		return StockQuoteDTO.builder().id(STOCK_ID).quotes(buildQuotes()).build();
	}
	
	private HashMap<LocalDate, String> buildQuotes(){
		HashMap<LocalDate, String> quotes = new HashMap<LocalDate, String>();
		quotes.put(QUOTE_DATE, QUOTE_VALUE);
		return quotes;
	}
}
