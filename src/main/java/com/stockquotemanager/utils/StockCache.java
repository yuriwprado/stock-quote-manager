package com.stockquotemanager.utils;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.synchronizedList;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class StockCache {
	
	private List<String> cachedStockIds = synchronizedList(Lists.<String>newArrayList());
	
	public boolean isStockCacheEmpty() {
		return cachedStockIds.isEmpty();
	}
	
	public List<String> getStockIds() {
		return newArrayList(cachedStockIds);
	}
	
	public void clearStockIds() {
		this.cachedStockIds.clear();
	}
	
	public void addStockIds(Collection<String> stockIds) {
		this.cachedStockIds.addAll(stockIds);
	}

}
