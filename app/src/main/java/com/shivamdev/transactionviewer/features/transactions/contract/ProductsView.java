package com.shivamdev.transactionviewer.features.transactions.contract;

import com.shivamdev.transactionviewer.common.mvp.BaseView;
import com.shivamdev.transactionviewer.data.ProductData;
import com.shivamdev.transactionviewer.data.TransactionsData;

import java.util.List;

/**
 * Created by shivam on 28/1/17.
 */

public interface ProductsView extends BaseView {

    void showListError();

    void updateProductsList(List<ProductData> aggregateList);

    void updateTransactionsData(List<TransactionsData> transactionsList);
}