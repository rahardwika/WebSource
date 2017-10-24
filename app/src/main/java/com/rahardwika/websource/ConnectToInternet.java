package com.rahardwika.websource;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by rahardwika on 22 Okt 2017.
 */

public class ConnectToInternet extends AsyncTask<String, Void, String>{

    private Context ct;
    public ConnectToInternet (Context ctx){
        ct = ctx;
    }
    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;
        try{
            URL url = new URL(s1);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setReadTimeout(10000);
            connect.setConnectTimeout(20000);
            connect.setRequestMethod("GET");
            connect.connect();
            in = connect.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuffer myBuf = new StringBuffer();
            String line = " ";

            while ((line = bufferedReader.readLine()) != null){
                myBuf.append(line +"\n");
            }

            bufferedReader.close();
            in.close();

            return myBuf.toString();

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s){
        if (s.length() > 0){
            MainActivity.textresult.setText(s);
        }
        else{
            MainActivity.textresult.setText("NOT FOUND");
        }
    }
}
