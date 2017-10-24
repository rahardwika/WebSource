package com.rahardwika.websource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by rahardwika on 22 Okt 2017.
 */

public class CekKoneksi {
    Context ctx;

    public CekKoneksi (Context ct) {
        this.ctx = ct;
    }
            public boolean isConnected(){
                ConnectivityManager myconnection = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (myconnection != null){
                        NetworkInfo info = myconnection.getActiveNetworkInfo();
                        if (info != null){
                            if (info.getState() == NetworkInfo.State.CONNECTED);{
                                return true;
                            }
                        }
                    }
                return false;
             }
}
