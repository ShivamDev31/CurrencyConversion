package com.shivamdev.transactionviewer.utils;

/**
 * Created by shivam on 28/1/17.
 */

public class CommonUtils {

    public static double decimalUptoTwoDigits(double number) {
        return Math.round((number * 100) / 100.0d);
    }
}