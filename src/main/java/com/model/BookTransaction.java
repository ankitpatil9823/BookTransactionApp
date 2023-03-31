package com.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class BookTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long book_transaction_id;

	private String book_entry_name;

	private long building_id;

	private String date;

	private String description;

	private long landlord_id;

	private long unit_id;

	private String bookTransaction_Status = "ACTIVE";

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bt_id", referencedColumnName = "book_transaction_id")
	private List<BookEntry> bookEntry;

}
