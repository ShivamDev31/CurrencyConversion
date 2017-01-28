package com.shivamdev.transactionviewer.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.shivamdev.transactionviewer.common.TransApplication;
import com.shivamdev.transactionviewer.di.component.ActivityComponent;
import com.shivamdev.transactionviewer.di.component.DaggerActivityComponent;
import com.shivamdev.transactionviewer.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shivam on 28/1/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        setupActivityComponent();
    }

    abstract int getLayout();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent
                    .builder()
                    .appComponent(TransApplication.getInstance().getComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}