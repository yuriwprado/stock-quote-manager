package com.stockquotemanager.controller;

import static com.stockquotemanager.utils.StockQuoteConstraints.STOCK_CACHE_PATH;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.stockquotemanager.utils.StockCache;

@RunWith(SpringRunner.class)
@WebMvcTest(StockCacheController.class)
public class StockCacheControllerTest {
	
	@MockBean
	StockCache stockCache;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void delete_SuccessTest() throws Exception {

		mvc.perform(
				MockMvcRequestBuilders.delete(STOCK_CACHE_PATH))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		
	}

}
