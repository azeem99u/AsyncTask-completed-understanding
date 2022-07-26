package com.dmp.asynktask;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private BackgroundTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("AsyncTask");

        textView = findViewById(R.id.txtProgress);

        findViewById(R.id.button).setOnClickListener(view -> {
            task = new BackgroundTask();
            task.execute(10);
        });

        findViewById(R.id.button2).setOnClickListener(view -> {
            task.cancel(true);
        });

    }



    class BackgroundTask extends AsyncTask<Integer,Integer,Integer>{
        private int customCounter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            customCounter = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            for (int i = 1; i <= integers[0]; i++) {
                try {
                    Thread.sleep(1000);
                    customCounter++;
                    publishProgress(customCounter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return customCounter;


        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            task.cancel(true);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(""+values[0]);

        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textView.setText(""+integer);
        }
    }

}