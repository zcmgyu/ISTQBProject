package com.aptech.istqbproject.util;

import android.content.res.Resources;

import com.aptech.istqbproject.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zcmgyu on 10/24/17.
 */

public class LoadJsonLocal {

    public static String loadJson() {
        String json;
        try {
            InputStream is = Resources.getSystem().openRawResource(R.raw.file);
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
