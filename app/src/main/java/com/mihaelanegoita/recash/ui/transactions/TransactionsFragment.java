package com.mihaelanegoita.recash.ui.transactions;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mihaelanegoita.recash.data.Transaction;
import com.mihaelanegoita.recash.data.TransactionRepository;
import com.mihaelanegoita.recash.databinding.FragmentTransactionsBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TransactionsFragment extends Fragment {

    private TransactionsViewModel model;
    private FragmentTransactionsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        model =
                new ViewModelProvider(this).get(TransactionsViewModel.class);

        binding = FragmentTransactionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.transactionsList.setHasFixedSize(true);
        binding.transactionsList.setLayoutManager(new LinearLayoutManager(getContext()));

        LiveData<List<Transaction>> transactions = model.getAllTransactions();
        if(transactions != null) {
            transactions.observe(getViewLifecycleOwner(), (data) -> {
                binding.transactionsList.setAdapter(new TransactionsListAdapter(data, getContext()));
            });
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}