package com.shivamdev.transactionviewer.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by shivam on 28/1/17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {

}