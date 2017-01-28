package com.shivamdev.transactionviewer.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shivam on 28/1/17.
 */

@Module
public class AppModule {
    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return app;
    }
}