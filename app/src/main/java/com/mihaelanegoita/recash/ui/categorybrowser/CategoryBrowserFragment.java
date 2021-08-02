package com.mihaelanegoita.recash.ui.categorybrowser;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.common.util.concurrent.ListenableFuture;
import com.mihaelanegoita.recash.data.Transaction;
import com.mihaelanegoita.recash.data.dao.TransactionType;
import com.mihaelanegoita.recash.data.net.ApiService;
import com.mihaelanegoita.recash.data.net.CategoryRemote;
import com.mihaelanegoita.recash.databinding.FragmentCategoryBrowserBinding;
import com.mihaelanegoita.recash.ui.categories.CategoriesViewModel;
import com.mihaelanegoita.recash.ui.transactions.TransactionsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

public class CategoryBrowserFragment extends BottomSheetDialogFragment {
    private FragmentCategoryBrowserBinding binding;
    private CategoriesViewModel model;

    public interface CategorySelectedListener {
        void onSelected(CategoryRemote category);
    }

    private CategorySelectedListener listener;

    private TransactionType transactionType;

    public void setCategorySelectedListener(CategorySelectedListener listener) {
        this.listener = listener;
    }

    public void setTransactionType(TransactionType type) {
        this.transactionType = type;
    }

    private ApiService api;

    public CategoryBrowserFragment(ApiService api) {
        super();
        this.api = api;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        model =
                new ViewModelProvider(this).get(CategoriesViewModel.class);

        binding = FragmentCategoryBrowserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Context context = getContext();

        model.getCategories().observe(getViewLifecycleOwner(), (categories) -> {
            binding.categoriesChipGroup.removeAllViews();
            List<CategoryRemote> categoryList = categories.stream().filter((item) -> transactionType.equals(TransactionType.EXPENSE) ? (item.type.equals("expense")) : (item.type.equals("income"))).collect(Collectors.toList());
            for(CategoryRemote category: categoryList) {
                Chip chip = new Chip(context);
                chip.setText(category.name);

                int iconId = context.getResources().getIdentifier(category.drawableName, "drawable", context.getPackageName());
                chip.setChipIcon(context.getDrawable(iconId));

                chip.setOnClickListener((e) -> {
                    if(listener != null) {
                        listener.onSelected(category);
                    }
                });

                binding.categoriesChipGroup.addView(chip);
            }
        });




        ListenableFuture<List<CategoryRemote>> categoriesRequest = api.getCategories();
        categoriesRequest.addListener(() -> {
            try {
                List<CategoryRemote> categories = categoriesRequest.get();
                model.setCategories(categories);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, Executors.newSingleThreadExecutor());

        return root;
    }
}
