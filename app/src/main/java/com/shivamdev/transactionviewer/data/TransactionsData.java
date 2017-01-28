package com.shivamdev.transactionviewer.data;

import org.parceler.Parcel;

/**
 * Created by shivam on 28/1/17.
 */

@Parcel
public class TransactionsData {

    public String fromCurrency;
    public String fromAmount;
    public String toCurrency;
    public String toAmount;

    @Override
    public String toString() {
        return "TransactionsData{" +
                "fromCurrency='" + fromCurrency + '\'' +
                ", fromAmount='" + fromAmount + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", toAmount='" + toAmount + '\'' +
                '}';
    }
}