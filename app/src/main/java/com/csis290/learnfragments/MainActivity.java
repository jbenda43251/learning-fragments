package com.csis290.learnfragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView tvGreen;
    private TextView tvBlue;
    private TextView tvRed;

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
                Bundle bundle = new Bundle();
                bundle.putString("key", "This is from main!");
                GreenFragment greenFragment = new GreenFragment();
                greenFragment.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, greenFragment)
                        .addToBackStack("")
                        .commit();
            }
        });

        tvBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "this is from main going to blue");
                BlueFragment blueFragment = new BlueFragment();
                blueFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, blueFragment)
                        .addToBackStack("")
                        .commit();

            }
        });

        tvRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "this is from main going to red");
                RedFragment redFragment = new RedFragment();
                redFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, redFragment)
                        .addToBackStack("")
                        .commit();

            }
        });
    }
}
