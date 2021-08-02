package com.mihaelanegoita.recash.ui.categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mihaelanegoita.recash.data.net.CategoryRemote;

import java.util.ArrayList;
import java.util.List;

public class CategoriesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<List<CategoryRemote>> mCategories;

    public CategoriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is categories fragment");

        mCategories = new MutableLiveData<>();
        mCategories.setValue(new ArrayList<>());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<CategoryRemote>> getCategories() { return mCategories; }

    public void setCategories(List<CategoryRemote> categories) {
        mCategories.postValue(categories);
    }
}