package com.shivamdev.transactionviewer.di.component;

import com.shivamdev.transactionviewer.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shivam on 28/1/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

}