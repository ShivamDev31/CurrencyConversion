package com.shivamdev.transactionviewer.features.transactions.presenter;

import com.shivamdev.transactionviewer.DummyData;
import com.shivamdev.transactionviewer.features.transactions.contract.TransactionsView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by shivam on 28/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionsFragmentPresenterTest {

    @Mock
    TransactionsView view;

    private TransactionsFragmentPresenter presenter;

    @Before
    public void setUp() {
        presenter = new TransactionsFragmentPresenter();
        presenter.initView(view);
    }

    @Test
    public void showTransactionsDataOnUi() {
        presenter.updateTransactionsDataOnUi(DummyData.getTransactionList());
        verify(view).updateTransactionsData(DummyData.getTransactionList().get(0).toAmount,
                DummyData.getTransactionList());
    }

    @After
    public void tearup() {
        presenter.destroyView();
    }

}