package com.mihaelanegoita.recash.ui.transactions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mihaelanegoita.recash.data.Transaction;
import com.mihaelanegoita.recash.data.TransactionRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TransactionsViewModel extends ViewModel {

    private TransactionRepository mTransactionRepository;
    private MutableLiveData<String> mText;


    @Inject
    TransactionsViewModel(TransactionRepository transactionRepository) {
        this.mTransactionRepository = transactionRepository;

        mText = new MutableLiveData<>();
        mText.setValue("This is transactions fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return this.mTransactionRepository.getAllTransactions();
    }
}