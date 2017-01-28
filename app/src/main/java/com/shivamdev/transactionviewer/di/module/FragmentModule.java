package com.shivamdev.transactionviewer.di.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.shivamdev.transactionviewer.di.scope.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shivam on 28/1/17.
 */

@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideActivity() {
        return fragment;
    }

    @ActivityContext
    @Provides
    Context provideActivityContext() {
        return fragment.getActivity();
    }
}