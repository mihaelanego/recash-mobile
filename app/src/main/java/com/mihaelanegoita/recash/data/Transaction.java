package com.mihaelanegoita.recash.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mihaelanegoita.recash.data.dao.TransactionType;

import javax.annotation.Nullable;

@Entity
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "type", defaultValue = "1")
    public int type;

    @ColumnInfo(name = "category_id", defaultValue = "0")
    public String categoryId;

    @ColumnInfo(name = "category_name", defaultValue = "Other")
    public String categoryName;

    @ColumnInfo(name = "category_drawable", defaultValue = "ic_expense_other_black_24dp")
    public String categoryDrawable;

    @ColumnInfo(name = "created_ts", defaultValue = "0")
    public long createdTimestamp;

    @ColumnInfo(name = "amount", defaultValue = "0")
    public double amount;

    @ColumnInfo(name = "note", defaultValue = "")
    public String note;
}
