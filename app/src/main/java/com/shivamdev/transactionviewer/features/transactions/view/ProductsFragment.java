package com.shivamdev.transactionviewer.features.transactions.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shivamdev.transactionviewer.MainActivity;
import com.shivamdev.transactionviewer.R;
import com.shivamdev.transactionviewer.common.base.BaseFragment;
import com.shivamdev.transactionviewer.data.ProductData;
import com.shivamdev.transactionviewer.data.RatesData;
import com.shivamdev.transactionviewer.data.TransactionsData;
import com.shivamdev.transactionviewer.features.transactions.contract.ProductsView;
import com.shivamdev.transactionviewer.features.transactions.presenter.ProductsFragmentPresenter;
import com.shivamdev.transactionviewer.features.transactions.view.adapters.ProductsAdapter;
import com.shivamdev.transactionviewer.utils.AndroidUtils;
import com.shivamdev.transactionviewer.utils.GsonUtil;
import com.shivamdev.transactionviewer.utils.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by shivam on 28/1/17.
 */

public class ProductsFragment extends BaseFragment implements ProductsView {

    private static final String TAG = ProductsFragment.class.getSimpleName();

    private static final String TRANSACTIONS_JSON = "transactions.json";
    private static final String RATES_JSON = "rates.json";

    @Inject
    ProductsFragmentPresenter presenter;

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @BindView(R.id.tv_error_products)
    TextView tvProductsError;

    private ProductsAdapter adapter;
    private List<RatesData> ratesList = new ArrayList<>();

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_products;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentComponent().inject(this);
        presenter.initView(this);
        setupAdapter();
        readTransactionsFromJson();
    }

    private void readTransactionsFromJson() {
        List<ProductData> productsList = getProductsFromAssets();
        presenter.loadTransactionsDataIntoUi(productsList);
        getRatesFromAssets();
        setupAdapterListener(productsList);
    }

    @NonNull
    private List<ProductData> getProductsFromAssets() {
        String productsJson = new AndroidUtils(getActivity())
                .getJsonFromAssets(TRANSACTIONS_JSON);
        ProductData[] products = GsonUtil.getInstance()
                .fromJson(productsJson, ProductData[].class);
        List<ProductData> productsList = Arrays.asList(products);
        return productsList;
    }

    private void getRatesFromAssets() {
        String ratesJson = new AndroidUtils(getActivity())
                .getJsonFromAssets(RATES_JSON);
        RatesData[] rates = GsonUtil.getInstance().fromJson(ratesJson, RatesData[].class);
        ratesList = Arrays.asList(rates);
    }

    @Override
    public void showListError() {
        rvProducts.setVisibility(View.GONE);
        tvProductsError.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateProductsList(List<ProductData> aggregateList) {
        rvProducts.setVisibility(View.VISIBLE);
        tvProductsError.setVisibility(View.GONE);
        adapter.updateProducts(aggregateList);
    }

    private void setupAdapterListener(List<ProductData> productsList) {
        Subscription subs = adapter.productClickObservable()
                .subscribe(new Subscriber<ProductData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error on product click ", e);
                        Logger.toast(getString(R.string.error_product_click));
                    }

                    @Override
                    public void onNext(ProductData product) {
                        showProgressDialog(getString(R.string.loading), false);
                        presenter.convertProductCurrency(ratesList, productsList, product);
                    }
                });
        presenter.addSubscription(subs);
    }

    @Override
    public void updateTransactionsData(List<TransactionsData> transactionsList) {
        MainActivity activity = (MainActivity) getActivity();
        TransactionsFragment transactionsFragment = TransactionsFragment.newInstance(transactionsList);
        activity.replaceFragment(transactionsFragment, TAG);
        hideProgressDialog();
    }

    private void setupAdapter() {
        adapter = new ProductsAdapter();
        rvProducts.setHasFixedSize(true);
        rvProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProducts.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroyView();
    }
}