package com.example.mysights;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity extends AppCompatActivity {
    private SpinnerListener spinnerListener = new SpinnerListener();
    private String filterSelection = "";

    private static final String TAG = StartActivity.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_categories, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(spinnerListener);

        if (savedInstanceState!=null) {
            filterSelection=savedInstanceState.getString("filter");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void moveToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("filter",filterSelection);
        startActivity(intent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.filterSelection =  savedInstanceState.getString("filter");
        Log.d(TAG, "onRestoreInstanceState");
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("filter",this.filterSelection);
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");

    }

    private class SpinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            filterSelection = (String) adapterView.getItemAtPosition(i);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            //do nothing
        }
    }

}