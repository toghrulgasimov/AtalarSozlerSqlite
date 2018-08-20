package com.zeylik.gasimov.learnsql;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListView L = (ListView)findViewById(R.id.L);
        final EditText T = (EditText)findViewById(R.id.T);

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
                String s = T.getText().toString();
                Cursor employees = db.getEmployees(s); // you would not typically call this on the main thread
                ListAdapter adapter = new SimpleCursorAdapter(that,
                        android.R.layout.simple_list_item_1,
                        employees,
                        new String[] {"esl","_id"},
                        new int[] {android.R.id.text1});

                L.setAdapter(adapter);

            }
        });



    }
}
