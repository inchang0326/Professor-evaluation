package com.example.haeyoungjoo.hackerthon;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Me on 2017-04-23.
 */

public class AccessWebService {
    // URL이 2개일때와 3개일때 스레드가 다르다.
    public static void accessWebService(AsyncTask<String, Void, String> task, String url){
        Log.d("AccessWebService", "accessWebService");
        task.execute(new String[]{ url });
    }
}
