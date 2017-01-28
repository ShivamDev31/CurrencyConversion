package com.shivamdev.transactionviewer.features.transactions.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shivamdev.transactionviewer.R;
import com.shivamdev.transactionviewer.data.ProductData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by shivam on 28/1/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsHolder> {

    private List<ProductData> aggregatedList;

    private PublishSubject<ProductData> clickSubject;

    public ProductsAdapter() {
        aggregatedList = new ArrayList<>();
        clickSubject = PublishSubject.create();
    }

    @Override
    public ProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ProductsHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsHolder holder, int position) {
        holder.tvProductTitle.setText(aggregatedList.get(position).sku);
        holder.tvProductTransactions
                .setText(holder.itemView.getContext()
                        .getString(R.string.no_of_transactions,
                                aggregatedList.get(position).noOfTransactions));
    }

    @Override
    public int getItemCount() {
        return aggregatedList.size();
    }

    public void updateProducts(List<ProductData> products) {
        aggregatedList.clear();
        aggregatedList.addAll(products);
        notifyDataSetChanged();
    }


    public Observable<ProductData> productClickObservable() {
        return clickSubject;
    }

    class ProductsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvProductTitle;

        @BindView(R.id.tv_description)
        TextView tvProductTransactions;

        ProductsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                clickSubject.onNext(aggregatedList.get(getLayoutPosition()));
            });
        }
    }
}