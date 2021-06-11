package com.stockquotemanager.dto;

import java.time.LocalDate;
import java.util.HashMap;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStockQuoteDTO {
	
	@NotBlank
	private String id;
	
	@NotEmpty
	private HashMap<LocalDate, String> quotes;

}
