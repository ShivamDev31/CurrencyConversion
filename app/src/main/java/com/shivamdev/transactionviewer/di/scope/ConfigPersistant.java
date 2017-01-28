package com.shivamdev.transactionviewer.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by shivam on 28/1/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigPersistant {

}