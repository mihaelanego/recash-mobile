package com.mihaelanegoita.recash.data;

import androidx.lifecycle.LiveData;

import com.mihaelanegoita.recash.data.dao.TransactionDao;
import com.mihaelanegoita.recash.data.dao.TransactionType;
import com.mihaelanegoita.recash.data.net.CategoryRemote;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class TransactionRepository {

    private TransactionDao transactionDao;

    @Inject
    TransactionRepository(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return this.transactionDao.getAllTransactions();
    }

    public Transaction addTransaction(TransactionType type, double amount, long date, CategoryRemote category, @Nullable String note) {
        Transaction transaction = new Transaction();

        transaction.type = type.ordinal();
        transaction.amount = amount;
        transaction.createdTimestamp = date;
        transaction.categoryId = category.id;
        transaction.categoryDrawable = category.drawableName;
        transaction.categoryName = category.name;

        if(note != null) transaction.note = note;

        this.transactionDao.insertTransaction(transaction);

        return transaction;
    }
}
