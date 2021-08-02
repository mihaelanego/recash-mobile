package com.mihaelanegoita.recash.ui.transactions;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mihaelanegoita.recash.R;
import com.mihaelanegoita.recash.data.Transaction;
import com.mihaelanegoita.recash.data.dao.TransactionType;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionsListViewHolder> {
    private List<Transaction> transactionList;
    private Context mContext;

    public static class TransactionsListViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryInfoView;
        public TextView amountView;
        public TextView dateView;

        public TransactionsListViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryInfoView = itemView.findViewById(R.id.card_category_info);
            amountView = itemView.findViewById(R.id.card_amount);
            dateView = itemView.findViewById(R.id.card_date);

        }

        @SuppressLint("SetTextI18n")
        public void bindData(Transaction transaction, Context context) {
            categoryInfoView.setText(transaction.categoryName);
            categoryInfoView.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getIdentifier(transaction.categoryDrawable, "drawable", context.getPackageName()), 0, 0, 0);
            amountView.setText((transaction.type == TransactionType.EXPENSE.ordinal() ? "- " : "+ ") + transaction.amount);
            if(transaction.type == TransactionType.INCOME.ordinal()) {
                amountView.setTextColor(Color.GREEN);
            } else {
                amountView.setTextColor(Color.RED);
            }
            dateView.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(new Date(transaction.createdTimestamp).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        }
    }

    public TransactionsListAdapter(List<Transaction> transactionList, Context context) {
        this.transactionList = transactionList;
        mContext = context;
    }

    @NonNull
    @Override
    public TransactionsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_list_item, parent, false);

        return new TransactionsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsListViewHolder holder, int position) {
        holder.bindData(transactionList.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}