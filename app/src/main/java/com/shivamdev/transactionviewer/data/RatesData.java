package com.shivamdev.transactionviewer.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shivam on 28/1/17.
 */

public class RatesData {

    @SerializedName("from")
    public String from;

    @SerializedName("rate")
    public String rate;

    @SerializedName("to")
    public String to;
}