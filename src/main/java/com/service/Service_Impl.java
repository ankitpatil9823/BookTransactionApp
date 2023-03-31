package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.BookEntry;
import com.model.BookTransaction;
import com.repository.BT_Repo;

@Service
public class Service_Impl implements IService {

	@Autowired
	private BT_Repo bT_Repo;

// New Book_Transaction_Entry save into DB	

	public String save(BookTransaction bookTransaction) {
		java.util.List<BookEntry> bookEntry1 = bookTransaction.getBookEntry();

		int debit = 0;
		int credit = 0;

		for (int i = 0; i < bookEntry1.size(); i++) {

			debit = (int) (debit + bookEntry1.get(i).getDebit_amt());

			credit = (int) (credit + bookEntry1.get(i).getCredit_amt());
		}

		if (debit == credit) {

			BookTransaction bookTransaction1 =bT_Repo.save(bookTransaction);
			System.out.println("Ankit"+bookTransaction1);

			return "saved";
		} else {

			return "The ammount of debit or credit are not equal";
		}

	}

// Get Book_Transaction_Entry data  from DB by using book_transaction_Id 

	@Override
	public BookTransaction getById(long id) {
		// TODO Auto-generated method stub
		java.util.Optional<BookTransaction> optional = bT_Repo.findById(id);

		BookTransaction bookTransaction = optional.get();

		List<BookEntry> b1 = bookTransaction.getBookEntry();

		List<BookEntry> bookEntryNew = new ArrayList<BookEntry>();

		for (int i = 0; i < b1.size(); i++) {
			BookEntry singleBookEntryObject = b1.get(i);

			String statusOfSingleBookEntryObject = singleBookEntryObject.getAccess_Status();

			if (statusOfSingleBookEntryObject.equals("Active")) {

				bookEntryNew.add(singleBookEntryObject);
			}

		}

		bookTransaction.setBookEntry(bookEntryNew);

		return bookTransaction;
	}

// Updated Object
	
	@SuppressWarnings("unused")
	@Override
	public String updateById(BookTransaction bookTransaction_uiList) {

		Long id = bookTransaction_uiList.getBook_transaction_id();
		java.util.Optional<BookTransaction> existing_Db = bT_Repo.findById(id);
		BookTransaction existing_Database = existing_Db.get();

		existing_Database.setBook_transaction_id(bookTransaction_uiList.getBook_transaction_id());
		existing_Database.setBook_entry_name(bookTransaction_uiList.getBook_entry_name());
		existing_Database.setBuilding_id(bookTransaction_uiList.getBuilding_id());
		existing_Database.setDate(bookTransaction_uiList.getDate());
		existing_Database.setDescription(bookTransaction_uiList.getDescription());
		existing_Database.setLandlord_id(bookTransaction_uiList.getLandlord_id());
		existing_Database.setUnit_id(bookTransaction_uiList.getUnit_id());

		List<BookEntry> bookEntryAllUiList = bookTransaction_uiList.getBookEntry();

		List<BookEntry> bookEntryAllDbList = existing_Database.getBookEntry();

		int BEAUL_Size = bookEntryAllUiList.size();

		int BEADBL_size = bookEntryAllDbList.size();

		boolean found = false;

		for (int i = 0; i < BEADBL_size; i++) {

			BookEntry bookEntryDbListByIndex = bookEntryAllDbList.get(i);

			found = false;

			for (int j = 0; j < BEAUL_Size; j++) {

				BookEntry bookEntryUiListByIndex = bookEntryAllUiList.get(j);

				if (0 == bookEntryUiListByIndex.getBook_entry_id()) {

					BookEntry bookEntryNewAdded = new BookEntry();

					bookEntryNewAdded.setBook_entry_id(bookEntryUiListByIndex.getBook_entry_id());
					bookEntryNewAdded.setGlAccount(bookEntryUiListByIndex.getGlAccount());
					bookEntryNewAdded.setNature(bookEntryUiListByIndex.getNature());
					bookEntryNewAdded.setCredit_amt(bookEntryUiListByIndex.getCredit_amt());
					bookEntryNewAdded.setDebit_amt(bookEntryUiListByIndex.getDebit_amt());

					bookEntryAllDbList.add(bookEntryNewAdded);
				}

				if (bookEntryUiListByIndex.getBook_entry_id() == bookEntryDbListByIndex.getBook_entry_id()) {

					found = true;

					bookEntryDbListByIndex.setBook_entry_id(bookEntryUiListByIndex.getBook_entry_id());
					bookEntryDbListByIndex.setCredit_amt(bookEntryUiListByIndex.getCredit_amt());
					bookEntryDbListByIndex.setDebit_amt(bookEntryUiListByIndex.getDebit_amt());
					bookEntryUiListByIndex.setGlAccount(bookEntryDbListByIndex.getGlAccount());
					bookEntryUiListByIndex.setNature(bookEntryDbListByIndex.getNature());

					bookEntryAllDbList.add(j, bookEntryDbListByIndex);
					// break;
				}

			}
			if (found == false) {

				bookEntryDbListByIndex.setAccess_Status("Not Active");
			}

		}

		existing_Database.setBookEntry(bookEntryAllDbList);

		int z = 0;

		for (BookEntry b1 : bookEntryAllDbList) {
			if (b1.getAccess_Status().equals("Not Active")) {
				z++;
			}
		}

		int k = bookEntryAllDbList.size();

		if (k == z) {
			existing_Database.setBookTransaction_Status("NOT ACTIVE");
		} else {
			existing_Database.setBookTransaction_Status("ACTIVE");
		}

		System.out.println(z);
		System.out.println(bookEntryAllDbList.size());

		bT_Repo.save(existing_Database);
		return "Updated";

	}

}
