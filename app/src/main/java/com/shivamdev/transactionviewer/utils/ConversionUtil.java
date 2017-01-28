package com.shivamdev.transactionviewer.utils;

import com.shivamdev.transactionviewer.constants.Constants;
import com.shivamdev.transactionviewer.data.RatesData;

import java.util.List;

/**
 * Created by shivam on 28/1/17.
 */

public class ConversionUtil {

    public static double convertCurrency(List<RatesData> ratesList, String currency, double amount) {
        if (currency.equalsIgnoreCase(Constants.Currency.GBP)) {
            return CommonUtils.decimalUptoTwoDigits(amount);
        }
        for (RatesData rate : ratesList) {
            if (rate.from.equalsIgnoreCase(currency)) {
                amount = amount * Double.valueOf(rate.rate);
                if (rate.to.equalsIgnoreCase(Constants.Currency.GBP)) {
                    break;
                }
                amount = convertCurrency(ratesList, rate.to, amount);
            }
        }
        return CommonUtils.decimalUptoTwoDigits(amount);
    }

}