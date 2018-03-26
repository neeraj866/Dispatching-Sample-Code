package com.demoapp.services;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by android5 on 4/7/16.
 */
public class CustomStringRequest extends StringRequest {

    private Map<String, String> params;

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }


    public CustomStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        Log.e("url", ": " + url);
    }

    public CustomStringRequest(int method, String url, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.params = params;
        Log.e("url", ": " + url);
    }

    public CustomStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }


}
