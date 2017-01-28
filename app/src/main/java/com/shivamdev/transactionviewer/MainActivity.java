package com.shivamdev.transactionviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.shivamdev.transactionviewer.features.transactions.view.ProductsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addProductsFragment();
    }

    private void addProductsFragment() {
        ProductsFragment productsFragment = ProductsFragment.newInstance();
        replaceFragment(productsFragment, TAG);
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment productsFragment = manager.findFragmentByTag(TAG);
        if (productsFragment != null) {
            ft.hide(productsFragment);
        }
        ft.add(R.id.fragment, fragment, tag);
        ft.addToBackStack(null).commit();
    }
}
