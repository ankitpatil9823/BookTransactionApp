package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.model.BookEntry;
import com.model.BookTransaction;

public interface BT_Repo extends CrudRepository<BookTransaction, Long> {

	void save(List<BookEntry> bookEntryAllDbList);
    
}
