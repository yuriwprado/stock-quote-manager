package com.stockquotemanager.listener;

import static com.stockquotemanager.utils.StockQuoteConstraints.NOTIFICATION_HOST;
import static com.stockquotemanager.utils.StockQuoteConstraints.NOTIFICATION_PORT;
import static com.stockquotemanager.utils.StockQuoteConstraints.STOCK_MANAGER_REGISTER_FOR_NOTIFICATION_URL;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.stockquotemanager.dto.RegisterForNotificationDTO;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override 
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	RegisterForNotificationDTO dto = RegisterForNotificationDTO.builder().port(NOTIFICATION_PORT).host(NOTIFICATION_HOST).build();
    	new RestTemplate().postForLocation(STOCK_MANAGER_REGISTER_FOR_NOTIFICATION_URL, dto);
    }
}