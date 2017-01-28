package com.shivamdev.transactionviewer;

import com.shivamdev.transactionviewer.data.TransactionsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivam on 28/1/17.
 */

public class DummyData {

    private static final List<TransactionsData> transactionList = new ArrayList<>();

    public static final String ratesJson = "[\n" +
            "  {\n" +
            "    \"from\": \"USD\",\n" +
            "    \"rate\": \"0.77\",\n" +
            "    \"to\": \"GBP\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"from\": \"GBP\",\n" +
            "    \"rate\": \"1.3\",\n" +
            "    \"to\": \"USD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"from\": \"USD\",\n" +
            "    \"rate\": \"1.09\",\n" +
            "    \"to\": \"CAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"from\": \"CAD\",\n" +
            "    \"rate\": \"0.92\",\n" +
            "    \"to\": \"USD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"from\": \"GBP\",\n" +
            "    \"rate\": \"0.83\",\n" +
            "    \"to\": \"AUD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"from\": \"AUD\",\n" +
            "    \"rate\": \"1.2\",\n" +
            "    \"to\": \"GBP\"\n" +
            "  }\n" +
            "]";

    public static final String productsJson = "[{\n" +
            "    \"amount\": \"33.0\",\n" +
            "    \"sku\": \"R9704\",\n" +
            "    \"currency\": \"USD\"\n" +
            "  }]";

    public static final String productData =
            " {\n" +
                    "    \"amount\": \"33.0\",\n" +
                    "    \"sku\": \"R9704\",\n" +
                    "    \"currency\": \"USD\"\n" +
                    "  }";

    public static List<TransactionsData> getTransactionList() {
        TransactionsData data = new TransactionsData();
        data.toAmount = "21.0";
        data.fromAmount = "25.2";
        data.fromCurrency = "CAD";
        data.toCurrency = "GBP";
        transactionList.clear();
        transactionList.add(data);
        return transactionList;
    }
}