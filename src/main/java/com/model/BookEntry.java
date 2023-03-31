package com.model;

import com.All_enum.NatureStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BookEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long book_entry_id;

	private long debit_amt;

	private long credit_amt;

	private String glAccount;

	@Enumerated(EnumType.STRING)
	private NatureStatusEnum nature;

	private String access_Status = "Active";

}
