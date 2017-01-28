package com.shivamdev.transactionviewer.common.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by shivam on 28/1/17.
 */

public abstract class BasePresenter<V extends BaseView> {
    private V view;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void initView(V view) {
        this.view = view;
    }

    public void destroyView() {
        view = null;
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.clear();
        }
    }

    public V getView() {
        return view;
    }

    public void checkViewAttached() {
        if (view == null) {
            throw new ViewNotAttachedException();
        }
    }

    public void addSubscription(Subscription subs) {
        compositeSubscription.add(subs);
    }

    private static class ViewNotAttachedException extends RuntimeException {
        ViewNotAttachedException() {
            super("View is not attached ");
        }
    }
}