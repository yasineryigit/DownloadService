package com.ossovita.downloadservice;

import android.app.AsyncNotedAppOp;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class DownloadService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AsyncTasking asyncTaskingClass  = new AsyncTasking();
        try {
            asyncTaskingClass.execute("https://codessional.com/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    class AsyncTasking extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            URL url;
            HttpURLConnection httpURLConnection = null;

            try{
                url = new URL(strings[0]);
                //gelen url'ye bir bağlantı aç dedik
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //okunacak data kalmayınca data =  -1 değerini alır
                int data = inputStreamReader.read();
                while(data!=-1){
                    //gelen datayı karaktere döüştür(char dediğimiz bir string)
                    char current = (char) data;
                    //current datayı al result'a ekle
                    result += current;
                    //döngünün içinde sürekli okumaya devam etmesi için
                    data = inputStreamReader.read();
                 }
                return result;
            }catch (Exception e){
                return "Failed cause: " + e.getLocalizedMessage();
            }



        }
        //Doingbackgrounddan gelen string buraya parametre 's' olarak geliyor
        @Override
        protected void onPostExecute(String s) {
            System.out.println("Result: " + s);
            super.onPostExecute(s);
        }
    }


}
