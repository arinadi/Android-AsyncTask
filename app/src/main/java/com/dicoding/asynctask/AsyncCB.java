package com.dicoding.asynctask;

public interface AsyncCB {
    void onPreExecute();
    void onPostExecute(String text);
}
