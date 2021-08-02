package com.mihaelanegoita.recash.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;
import com.mihaelanegoita.recash.data.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM `Transaction` ORDER BY 'created_ts' ASC")
    LiveData<List<Transaction>> getAllTransactions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Long> insertTransaction(Transaction transaction);


}