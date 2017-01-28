package com.shivamdev.transactionviewer.di.component;

import com.shivamdev.transactionviewer.di.module.FragmentModule;
import com.shivamdev.transactionviewer.di.scope.PerFragment;
import com.shivamdev.transactionviewer.features.transactions.view.ProductsFragment;
import com.shivamdev.transactionviewer.features.transactions.view.TransactionsFragment;

import dagger.Component;

/**
 * Created by shivam on 28/1/17.
 */

@PerFragment
@Component(dependencies = {AppComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(ProductsFragment fragment);

    void inject(TransactionsFragment fragment);
}