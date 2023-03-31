package com.service;

import com.model.BookTransaction;

public interface IService {

	public String save(BookTransaction bookTransaction);

	public BookTransaction getById(long id);

	public String updateById(BookTransaction bookTransaction);

}
