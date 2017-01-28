package com.shivamdev.transactionviewer.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shivam on 28/1/17.
 */

public class ProductData {

    @SerializedName("amount")
    public String amount;

    @SerializedName("sku")
    public String sku;

    @SerializedName("currency")
    public String currency;

    public int noOfTransactions;

    @Override
    public String toString() {
        return "ProductData{" +
                "amount='" + amount + '\'' +
                ", sku='" + sku + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductData that = (ProductData) o;

        return sku != null ? sku.equals(that.sku) : that.sku == null;

    }

    @Override
    public int hashCode() {
        return (sku != null ? sku.hashCode() : 0);
    }
}