package com.example.alex.myzoo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinner;
    private ListView mListView;
    private ImageView mImageView;
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    private void findViews() {
        mSpinner = (Spinner) findViewById(R.id.sp_typeAnimals);
        mListView = (ListView) findViewById(R.id.lv_animals);
        mImageView = (ImageView) findViewById(R.id.iv_imageAnimals);
    }

    private void setListeners() {
        mSpinner.setOnItemSelectedListener(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                mImageView.setImageResource(getImageResource(textView.getText().toString()));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                setListView(R.array.birds);
                break;
            case 1:
                setListView(R.array.dogs);
                break;
            case 2:
                setListView(R.array.cats);
                break;
            case 3:
                setListView(R.array.snakes);
                break;
        }
    }

    private void setListView(int resource) {
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(resource));
        mListView.setAdapter(mArrayAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private int getImageResource(String name) {
        return getResources().getIdentifier(name.toLowerCase(), "drawable", this.getPackageName());
    }
}