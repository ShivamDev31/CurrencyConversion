package com.shivamdev.transactionviewer.features.transactions.presenter;

import com.shivamdev.transactionviewer.DummyData;
import com.shivamdev.transactionviewer.data.ProductData;
import com.shivamdev.transactionviewer.data.RatesData;
import com.shivamdev.transactionviewer.features.transactions.contract.ProductsView;
import com.shivamdev.transactionviewer.utils.ConversionUtil;
import com.shivamdev.transactionviewer.utils.GsonUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

/**
 * Created by shivam on 28/1/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProductsFragmentPresenterTest {

    private ProductsFragmentPresenter presenter;

    @Mock
    ProductsView view;

    @Before
    public void setUp() throws Exception {
        presenter = new ProductsFragmentPresenter();
        presenter.initView(view);
    }

    @Test
    public void showErrorWhenTheProductsListIsEmpty() {
        presenter.loadTransactionsDataIntoUi(new ArrayList<>());
        verify(view).showListError();
    }

    @Ignore
    @Test
    public void showProductsOnUiWhenListIsNotEmpty() {
        presenter.loadTransactionsDataIntoUi(getProductsList());
        verify(view).updateProductsList(getProductsList());
    }

    @Test
    public void convertProductCurrencyAndShowOnUi() throws Exception {
        presenter.convertProductCurrency(getRatesList(), getProductsList(), getProduct());
        verify(view).updateTransactionsData(anyList());
    }

    @Test
    public void checkIfCurrencyConversionIsGivingCorrectValues() {
        double convertedValue = ConversionUtil.convertCurrency(getRatesList(), "CAD", 30);
        double expectedAmount = Double.valueOf(DummyData.getTransactionList().get(0).toAmount);
        Assert.assertEquals(expectedAmount, convertedValue, 0.9);

    }

    @After
    public void tearDown() throws Exception {
        presenter.destroyView();
    }

    private List<RatesData> getRatesList() {
        RatesData[] data = GsonUtil.getInstance().fromJson(DummyData.ratesJson, RatesData[].class);
        return Arrays.asList(data);
    }

    private List<ProductData> getProductsList() {
        ProductData[] data = GsonUtil.getInstance().fromJson(DummyData.productsJson,
                ProductData[].class);
        return Arrays.asList(data);
    }

    private ProductData getProduct() {
        return GsonUtil.getInstance().fromJson(DummyData.productData, ProductData.class);
    }

}