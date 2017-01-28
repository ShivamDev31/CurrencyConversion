package com.shivamdev.transactionviewer.common;

import android.app.Application;

import com.shivamdev.transactionviewer.di.component.AppComponent;
import com.shivamdev.transactionviewer.di.component.DaggerAppComponent;

/**
 * Created by shivam on 28/1/17.
 */

public class TransApplication extends Application {

    private static TransApplication instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static TransApplication getInstance() {
        return instance;
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                    .builder().build();
        }
        return appComponent;
    }
}