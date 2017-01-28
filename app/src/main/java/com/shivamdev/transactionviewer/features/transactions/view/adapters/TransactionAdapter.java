package com.shivamdev.transactionviewer.features.transactions.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shivamdev.transactionviewer.R;
import com.shivamdev.transactionviewer.data.TransactionsData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shivam on 28/1/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    private List<TransactionsData> transactionsList;

    public TransactionAdapter() {
        transactionsList = new ArrayList<>();
    }

    @Override
    public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionHolder holder, int position) {
        TransactionsData transactionsData = transactionsList.get(position);
        String fromAmount = transactionsData.fromCurrency + " " + transactionsData.fromAmount;
        String toAmount = transactionsData.toCurrency + " " + transactionsData.toAmount;
        holder.tvFromAmount.setText(fromAmount);
        holder.tvToAmount.setText(toAmount);
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    public void updateTransactions(List<TransactionsData> data) {
        transactionsList.clear();
        transactionsList.addAll(data);
        notifyDataSetChanged();
    }

    class TransactionHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvFromAmount;

        @BindView(R.id.tv_description)
        TextView tvToAmount;

        public TransactionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}