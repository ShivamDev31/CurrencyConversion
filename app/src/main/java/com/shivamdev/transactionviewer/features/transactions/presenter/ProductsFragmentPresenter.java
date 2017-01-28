package com.shivamdev.transactionviewer.features.transactions.presenter;

import com.shivamdev.transactionviewer.common.mvp.BasePresenter;
import com.shivamdev.transactionviewer.constants.Constants;
import com.shivamdev.transactionviewer.data.ProductData;
import com.shivamdev.transactionviewer.data.RatesData;
import com.shivamdev.transactionviewer.data.TransactionsData;
import com.shivamdev.transactionviewer.features.transactions.contract.ProductsView;
import com.shivamdev.transactionviewer.utils.CommonUtils;
import com.shivamdev.transactionviewer.utils.ConversionUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by shivam on 28/1/17.
 */

public class ProductsFragmentPresenter extends BasePresenter<ProductsView> {

    @Inject
    ProductsFragmentPresenter() {

    }

    public void initView(ProductsView view) {
        super.initView(view);
    }

    public void loadTransactionsDataIntoUi(List<ProductData> products) {
        checkViewAttached();
        if (products.size() > 0) {
            aggregateProducts(products);
        } else {
            getView().showListError();
        }
    }

    private void aggregateProducts(List<ProductData> productsList) {
        List<ProductData> aggregateList = new ArrayList<>();
        for (ProductData trans : productsList) {
            if (!aggregateList.contains(trans)) {
                trans.noOfTransactions++;
                aggregateList.add(trans);
            } else {
                int oldIndex = aggregateList.indexOf(trans);
                ProductData oldTrans = aggregateList.get(oldIndex);
                oldTrans.noOfTransactions++;
                aggregateList.remove(oldIndex);
                aggregateList.add(oldIndex, oldTrans);
                getView().updateProductsList(aggregateList);
            }
        }
    }

    public void convertProductCurrency(List<RatesData> ratesList, List<ProductData> productsList,
                                       ProductData productData) {
        List<TransactionsData> transactionsList = new ArrayList<>();
        for (ProductData product : productsList) {
            if (product.equals(productData)) {
                TransactionsData transactionData = new TransactionsData();
                transactionData.fromCurrency = product.currency;
                transactionData.toCurrency = Constants.Currency.GBP;
                transactionData.fromAmount = product.amount;
                double to = CommonUtils.decimalUptoTwoDigits(ConversionUtil.convertCurrency(ratesList,
                        product.currency, Double.valueOf(product.amount)));
                transactionData.toAmount = String.valueOf(to);
                transactionsList.add(transactionData);
            }
        }

        getView().updateTransactionsData(transactionsList);
    }
}