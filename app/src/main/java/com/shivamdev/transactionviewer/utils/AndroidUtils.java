package com.shivamdev.transactionviewer.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shivam on 28/1/17.
 */

public class AndroidUtils {

    private Context context;

    public AndroidUtils(Context context) {
        this.context = context;
    }

    public String getJsonFromAssets(String filename) {
        String json;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}