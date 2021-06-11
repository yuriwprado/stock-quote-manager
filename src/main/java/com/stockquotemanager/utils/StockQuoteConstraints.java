package com.stockquotemanager.utils;

public class StockQuoteConstraints {
	
	public static final String STOCK_MANAGER_GET_STOCKS_URL = "http://localhost:8080/stock";
	public static final String STOCK_MANAGER_REGISTER_FOR_NOTIFICATION_URL = "http://localhost:8080/notification";
	
	public static final String ERROR_NULL_CREATE_STOCK_QUOTE = "No data informed to create a stock quote";
	public static final String ERROR_NON_EXISTENT_STOCK = "Non-existent stock informed to create a quote";
	
	public static final String NOTIFICATION_HOST = "localhost";
	public static final int NOTIFICATION_PORT = 8080;
	
	public static final String STOCK_CACHE_PATH = "/stockcache";
	public static final String STOCK_QUOTE_PATH = "/api/stockquote/v1";
	

}
