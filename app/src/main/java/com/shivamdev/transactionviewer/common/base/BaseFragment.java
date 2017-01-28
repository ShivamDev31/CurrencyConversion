package com.shivamdev.transactionviewer.common.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shivamdev.transactionviewer.common.TransApplication;
import com.shivamdev.transactionviewer.di.component.DaggerFragmentComponent;
import com.shivamdev.transactionviewer.di.component.FragmentComponent;
import com.shivamdev.transactionviewer.di.module.FragmentModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shivam on 28/1/17.
 */

public abstract class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setupFragmentComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getLayout();

    private void setupFragmentComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = DaggerFragmentComponent
                    .builder()
                    .appComponent(TransApplication.getInstance().getComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    protected void showProgressDialog(String message, boolean isCancellable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
        }
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(isCancellable);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}