package com.mihaelanegoita.recash.ui.add;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.mihaelanegoita.recash.data.net.ApiService;
import com.mihaelanegoita.recash.databinding.AddBottomSheetBinding;
import com.mihaelanegoita.recash.data.dao.TransactionType;
import com.mihaelanegoita.recash.ui.categorybrowser.CategoryBrowserFragment;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddFragment extends BottomSheetDialogFragment {

    private AddViewModel model;
    private AddBottomSheetBinding binding;

    @Inject
    public ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(AddViewModel.class);

        binding = AddBottomSheetBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CategoryBrowserFragment categoryBrowser = new CategoryBrowserFragment(this.apiService);

        categoryBrowser.setCategorySelectedListener((category) -> {
            Log.d("CHIPCLICKLISTENER", category.name + " " + category.id);
            model.setCategory(category);
            binding.transactionAddSelectCategory.setText(category.name);
            categoryBrowser.dismiss();
        });

        binding.addExpenseButton.setOnClickListener((e) -> {
            model.setTransactionType(TransactionType.EXPENSE);
        });

        binding.addIncomeButton.setOnClickListener((e) -> {
            model.setTransactionType(TransactionType.INCOME);
        });

        binding.transactionAddSelectCategory.setOnClickListener((e) -> {
            categoryBrowser.setTransactionType(model.getTransactionType().getValue());
            categoryBrowser.show(getParentFragmentManager(), categoryBrowser.getTag());
        });

        model.setDate(System.currentTimeMillis());

        binding.transactionAddSelectDate.setOnClickListener((e) -> {
            MaterialDatePicker date = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();

            date.addOnPositiveButtonClickListener((d) -> {
                Date selection = new Date((long) date.getSelection());
                String formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(selection.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                model.setDate((long) date.getSelection());

                binding.transactionAddSelectDate.setText(formattedDate);
            });

            date.show(getParentFragmentManager(), date.getTag());
        });

        binding.transactionAddButton.setOnClickListener((e) -> {
            String note = binding.transactionAddNote.getText().toString().trim();

            try {
                String amountString = binding.transactionAddAmount.getText().toString().trim();
                if(amountString.length() == 0) {
                    throw new Exception();
                }

                double amount = Double.parseDouble(amountString);
                model.saveTransaction(amount, note);

                this.dismiss();
            } catch (Exception ex) {
                Snackbar.make(getView(), "Amount is required", Snackbar.LENGTH_LONG).show();
            }
        });

        return root;
    }
}