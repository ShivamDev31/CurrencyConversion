package com.shivamdev.transactionviewer.di.module;

import android.app.Activity;
import android.content.Context;

import com.shivamdev.transactionviewer.di.scope.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shivam on 28/1/17.
 */

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @ActivityContext
    @Provides
    Context provideActivityContext() {
        return activity;
    }
}