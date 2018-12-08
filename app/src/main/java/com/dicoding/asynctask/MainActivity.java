package com.dicoding.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements AsyncCB{

    TextView tvStatus;
    TextView tvDesc;
    final static String INPUT_STRING = "Halo Ini Demo AsyncTask!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tv_status);
        tvDesc = findViewById(R.id.tv_desc);

        DemoAsync demoAsync = new DemoAsync();
        demoAsync.execute("Halo Ini Demo AsyncTask");

    }

    @Override
    public void onPreExecute() {
    }
    @Override
    public void onPostExecute(String result) {
    }
    private class DemoAsync extends AsyncTask<String, Void, String> {
        static final String LOG_ASYNC = "DemoAsync";
        WeakReference<AsyncCB> myListener;

        public DemoAsync() {
            this.myListener = new WeakReference<AsyncCB>((AsyncCB) myListener);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(LOG_ASYNC, "status : onPreExecute");
            AsyncCB myListener = this.myListener.get();
            if (myListener != null) {
                myListener.onPreExecute();
            }
            tvStatus.setText(R.string.status_pre);
            tvDesc.setText(INPUT_STRING);
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_ASYNC, "status : doInBackground");
            String output = null;
            try {
                String input = params[0];
                output = input + " Selamat Belajar!!";
                Thread.sleep(5000);
            } catch (Exception e) {
                Log.d(LOG_ASYNC, e.getMessage());
            }
            return output;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_ASYNC, "status : onPostExecute");
            AsyncCB myListener = this.myListener.get();
            if (myListener != null) {
                myListener.onPostExecute(result);
            }
            tvStatus.setText(R.string.status_post);
            if (result != null) {
                tvDesc.setText(result);
            }
        }
    }
}
