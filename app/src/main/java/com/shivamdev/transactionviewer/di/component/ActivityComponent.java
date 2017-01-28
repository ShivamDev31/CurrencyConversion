package com.shivamdev.transactionviewer.di.component;

import com.shivamdev.transactionviewer.di.module.ActivityModule;
import com.shivamdev.transactionviewer.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by shivam on 28/1/17.
 */

@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

}