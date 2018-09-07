package com.wetertertertertert.asdasdasdasd.sdfsdgdfgdfgdfgdfgdfg;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {


    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Admob Banner
        MobileAds.initialize(this, "ca-app-pub-9026840340673035~9445396711");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        final ListView L = (ListView)findViewById(R.id.L);
        final EditText T = (EditText)findViewById(R.id.T);
        LinearLayout Layout = (LinearLayout)findViewById(R.id.keys);
        for(int i = 0; i < Layout.getChildCount(); i++) {
            View v = Layout.getChildAt(i);
            Button b = (Button) v;
            final String textb = b.getText().toString();
            b.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View view) {
                    T.setText(T.getText().toString() + textb.toLowerCase());
                    T.setSelection(T.getText().length());
                }
            });
        }
        final Context that = this;
        final My db = new My(this);
        Cursor employees;
        T.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable r) {
                final String s = T.getText().toString();


                AsyncTask<Cursor, Void, Cursor> a = new AsyncTask<Cursor, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Cursor... strings) {

                        Cursor employees = db.getEmployees(s); // you would not typically call this on the main thread
                        return employees;
                    }
                    @Override
                    protected void onPostExecute(Cursor result) {
                        //textView.setText(result);
                        ListAdapter adapter = new SimpleCursorAdapter(that,
                                android.R.layout.simple_list_item_1,
                                result,
                                new String[] {"esl","_id"},
                                new int[] {android.R.id.text1});
                        L.setAdapter(adapter);
                    }
                };
                a.execute();
            }
        });



    }
}
