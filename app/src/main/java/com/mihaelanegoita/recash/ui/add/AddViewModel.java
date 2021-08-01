package com.mihaelanegoita.recash.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mihaelanegoita.recash.dto.transaction.TransactionType;

public class AddViewModel extends ViewModel {
    private MutableLiveData<String> mInfoText;
    private MutableLiveData<TransactionType> mTransactionType;

    public AddViewModel() {
        mInfoText = new MutableLiveData<>();
        mInfoText.setValue("You will be adding a new 'Expense'");

        mTransactionType = new MutableLiveData<>();
        mTransactionType.setValue(TransactionType.EXPENSE);
    }

    public LiveData<String> getInfoText() {
        return mInfoText;
    }

    public LiveData<TransactionType> getTransactionType() { return mTransactionType; };

    public void setTransactionType(TransactionType type) {
        mTransactionType.postValue(type);
        mInfoText.postValue("You will be adding a new '" + (type.equals(TransactionType.EXPENSE) ? "Expense" : "Income") + "'");
    }
}
