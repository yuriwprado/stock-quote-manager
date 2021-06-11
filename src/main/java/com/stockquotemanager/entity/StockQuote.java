package com.stockquotemanager.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockQuote {
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long identifier;
	
	@NotBlank
	private String id;
	
	@NotEmpty
	private HashMap<LocalDate, String> quotes;

}
