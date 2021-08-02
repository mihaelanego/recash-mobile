package com.mihaelanegoita.recash.core;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mihaelanegoita.recash.data.Transaction;
import com.mihaelanegoita.recash.data.dao.TransactionDao;

@Database(entities = {Transaction.class}, version = 3)
public abstract class ApplicationDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();
}
