package com.hfad.deutsch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

    public static final String EXTRA_POZITION = "position";
    public static final String EXTRA_CATEGORY = "category";
    private int category;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        category = getIntent().getIntExtra(EXTRA_CATEGORY,0);
        position = getIntent().getIntExtra(EXTRA_POZITION,0);
        if(savedInstanceState!=null){
            category = savedInstanceState.getInt(EXTRA_CATEGORY);
            position = savedInstanceState.getInt(EXTRA_POZITION);
        }

    }

    @Override
    public void onStart(){
        super.onStart();
        TextView textview = (TextView)findViewById(R.id.description);
        textview.setText(Data.text[category][position]);
        ImageView iv = (ImageView)findViewById(R.id.image_view);
        iv.setImageResource(Data.imageId[category][position]);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(EXTRA_CATEGORY,category);
        savedInstanceState.putInt(EXTRA_POZITION,position);
    }
}
