package com.shivamdev.transactionviewer.features.transactions.presenter;

import com.shivamdev.transactionviewer.common.mvp.BasePresenter;
import com.shivamdev.transactionviewer.data.TransactionsData;
import com.shivamdev.transactionviewer.features.transactions.contract.TransactionsView;
import com.shivamdev.transactionviewer.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by shivam on 28/1/17.
 */

public class TransactionsFragmentPresenter extends BasePresenter<TransactionsView> {

    @Inject
    TransactionsFragmentPresenter() {
    }

    @Override
    public void initView(TransactionsView view) {
        super.initView(view);
    }

    public void updateTransactionsDataOnUi(List<TransactionsData> transactionsData) {
        checkViewAttached();
        getView().updateTransactionsData(getTotalAmount(transactionsData), transactionsData);
    }

    private String getTotalAmount(List<TransactionsData> transactionsData) {
        double total = 0;
        for (TransactionsData trans : transactionsData) {
            total += Double.valueOf(trans.toAmount);
        }
        return String.valueOf(CommonUtils.decimalUptoTwoDigits(total));
    }
}