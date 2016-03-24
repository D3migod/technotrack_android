package com.example.bulatgaliev.task1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SecondActivity extends AppCompatActivity {
    private static final int NUMBER_OF_ROWS = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, NUMBER_OF_ROWS);
        recyclerView.setAdapter(adapter);
    }
}
