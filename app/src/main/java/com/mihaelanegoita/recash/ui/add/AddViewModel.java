package com.mihaelanegoita.recash.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mihaelanegoita.recash.data.TransactionRepository;
import com.mihaelanegoita.recash.data.dao.TransactionType;
import com.mihaelanegoita.recash.data.net.CategoryRemote;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddViewModel extends ViewModel {
    private MutableLiveData<TransactionType> mTransactionType;
    private MutableLiveData<CategoryRemote> mCategory;
    private MutableLiveData<Long> mDate;

    private TransactionRepository mTransactionRepository;

    @Inject
    public AddViewModel(TransactionRepository transactionRepository) {
        this.mTransactionRepository = transactionRepository;

        mTransactionType = new MutableLiveData<>();
        mTransactionType.setValue(TransactionType.EXPENSE);

        mCategory = new MutableLiveData<>();
        mCategory.setValue(null);

        mDate = new MutableLiveData<>();
        mDate.setValue(0L);
    }

    public LiveData<TransactionType> getTransactionType() {
        return mTransactionType;
    };

    public MutableLiveData<CategoryRemote> getCategoryId() {
        return mCategory;
    }

    public MutableLiveData<Long> getDate() {
        return mDate;
    }

    public void setTransactionType(TransactionType type) {
        mTransactionType.postValue(type);
    }

    public void setCategory(CategoryRemote category) {
        mCategory.postValue(category);
    }

    public void setDate(long date) {
        mDate.postValue(date);
    }

    public void saveTransaction(double amount, String note) {
        mTransactionRepository.addTransaction(mTransactionType.getValue(), amount, mDate.getValue(), mCategory.getValue(), note);
    }
}
