package com.csis290.learnfragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView tvGreen;
    private TextView tvBlue;
    private TextView tvRed;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            Bundle bundle = new Bundle();
            bundle.putString("key", "This is from main!");
            GreenFragment greenFragment = new GreenFragment();
            greenFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, greenFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("")
                    .commit();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGreen = (TextView) findViewById(R.id.tv_green);
        tvBlue = (TextView) findViewById(R.id.tv_blue);
        tvRed = (TextView) findViewById(R.id.tv_red);

        tvGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(r, 1500);
            }
        });

        tvBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BlueTransactionTask(new Handler()).execute();

            }
        });

        tvRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RedTransactionTask().execute();

            }
        });
    }

    // AsyncTask<Params, Progress, Result>
    class BlueTransactionTask extends AsyncTask<Void, Void, Void>
    {
        private Handler handler;
        Runnable blueRunnable = new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("key", "this is from main going to blue");
                BlueFragment blueFragment = new BlueFragment();
                blueFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, blueFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("")
                        .commit();
            }
        };

        public BlueTransactionTask(Handler h)
        {
            this.handler = h;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("tag", "onPrexecute()");
        }

        @Override
        protected Void doInBackground(Void... params) {

            handler.postDelayed(blueRunnable, 2000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("tag","onPostExecute()");
        }
    }

    // AsyncTask<Params, Progress, Result>
    class RedTransactionTask extends AsyncTask<Void, Void, Void>
    {
        private Handler handler;
        Runnable redRunnable = new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("key", "this is from main going to red");
                RedFragment redFragment = new RedFragment();
                redFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, redFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("")
                        .commit();
            }
        };

        @Override
        protected void onPreExecute() {
            handler = new Handler();
            super.onPreExecute();
            Log.d("tag", "onPrexecute()");
        }

        @Override
        protected Void doInBackground(Void... params) {

            handler.postDelayed(redRunnable, 3000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("tag","onPostExecute()");
        }
    }
}
