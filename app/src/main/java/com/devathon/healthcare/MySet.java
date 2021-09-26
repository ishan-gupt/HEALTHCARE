package com.devathon.healthcare;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class MySet  {

    private static MySet mySet;
    private RequestQueue requestQueue;
    private static Context context;

    private static final String TAG = "MySingleton";

    private MySet(Context context) {
        this.context = context;
        this.requestQueue=getRequestQueue();
    }

    public  RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        Log.e(TAG, "getRequestQueue : "+requestQueue );

        return requestQueue;
    }

    static synchronized MySet getInstance(Context context)
    {
        if(mySet==null)
        {
            Log.e(TAG, "getInstance: "+context );
            mySet=new MySet(context);
        }
        Log.e(TAG, "getInstance: IN :"+mySet );
        return mySet;
    }

    public  <T> void addRequestQue(Request<T> r)
    {
        requestQueue.add(r);
    }
}
