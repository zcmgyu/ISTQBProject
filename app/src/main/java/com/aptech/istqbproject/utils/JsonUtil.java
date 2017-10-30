package com.aptech.istqbproject.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zcmgyu on 10/30/17.
 */

public class JsonUtil {

    /**
     * Load JsonObject from Json file in raw
     * @param context
     * @param file
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject loadJsonObjectFile(Context context, int file) throws IOException,
            JSONException {
        return new JSONObject(readStringFromRaw(context, file));
    }

    /**
     * * Load JsonArray from Json file in raw
     * @param context
     * @param file
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray loadJsonArrayFile(Context context, int file) throws IOException,
            JSONException {
        return new JSONArray(readStringFromRaw(context, file));
    }


    /**
     *
     * @param context
     * @param file
     * @return
     * @throws IOException
     */
    private static String readStringFromRaw(Context context, int file) throws IOException {
        InputStream is = context.getResources().openRawResource(file);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer);
    }

}
