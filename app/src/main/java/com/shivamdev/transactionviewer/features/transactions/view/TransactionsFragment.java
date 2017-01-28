package com.shivamdev.transactionviewer.features.transactions.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shivamdev.transactionviewer.R;
import com.shivamdev.transactionviewer.common.base.BaseFragment;
import com.shivamdev.transactionviewer.data.TransactionsData;
import com.shivamdev.transactionviewer.features.transactions.contract.TransactionsView;
import com.shivamdev.transactionviewer.features.transactions.presenter.TransactionsFragmentPresenter;
import com.shivamdev.transactionviewer.features.transactions.view.adapters.TransactionAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by shivam on 28/1/17.
 */

public class TransactionsFragment extends BaseFragment implements TransactionsView {

    private static final String TRANSACTIONS_KEY = "transactions_key";

    @BindView(R.id.rv_transactions)
    RecyclerView rvTransactions;

    @BindView(R.id.tv_total_amount)
    TextView tvTotalAmount;

    private TransactionAdapter adapter;

    @Inject
    TransactionsFragmentPresenter presenter;

    public static TransactionsFragment newInstance(List<TransactionsData> transactionsList) {
        TransactionsFragment fragment = new TransactionsFragment();
        Bundle args = new Bundle();
        args.putParcelable(TRANSACTIONS_KEY, Parcels.wrap(transactionsList));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_transactions;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentComponent().inject(this);
        presenter.initView(this);
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new TransactionAdapter();
        rvTransactions.setHasFixedSize(true);
        rvTransactions.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTransactions.setAdapter(adapter);
        presenter.updateTransactionsDataOnUi(Parcels.unwrap(getArguments()
                .getParcelable(TRANSACTIONS_KEY)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroyView();
    }

    @Override
    public void updateTransactionsData(String totalAmount, List<TransactionsData> transactionsData) {
        tvTotalAmount.setText(getString(R.string.total_gbp, totalAmount));
        adapter.updateTransactions(transactionsData);

    }
}