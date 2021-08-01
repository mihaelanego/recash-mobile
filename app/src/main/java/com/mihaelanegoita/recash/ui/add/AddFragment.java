package com.mihaelanegoita.recash.ui.add;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.mihaelanegoita.recash.databinding.AddBottomSheetBinding;
import com.mihaelanegoita.recash.dto.transaction.TransactionType;
import com.mihaelanegoita.recash.ui.categorybrowser.CategoryBrowserFragment;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class AddFragment extends BottomSheetDialogFragment {

    private AddViewModel model;
    private AddBottomSheetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(AddViewModel.class);

        binding = AddBottomSheetBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CategoryBrowserFragment categoryBrowser = new CategoryBrowserFragment();

        binding.addExpenseButton.setOnClickListener((e) -> {
            model.setTransactionType(TransactionType.EXPENSE);
        });

        binding.addIncomeButton.setOnClickListener((e) -> {
            model.setTransactionType(TransactionType.INCOME);
        });

        binding.transactionAddSelectCategory.setOnClickListener((e) -> {
            categoryBrowser.show(getParentFragmentManager(), categoryBrowser.getTag());
        });

        binding.transactionAddSelectDate.setOnClickListener((e) -> {
            MaterialDatePicker date = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();

            date.addOnPositiveButtonClickListener((d) -> {
                Date selection = new Date((long) date.getSelection());
                String formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(selection.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                binding.transactionAddSelectDate.setText(formattedDate);
            });

            date.show(getParentFragmentManager(), date.getTag());
        });

        return root;
    }
}