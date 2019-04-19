package com.hfad.deutsch;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class DetailFragment extends Fragment
{

    final String LOG_TAG = "mylog";
    private int image[];
    private String cap[];
    private int category;

    public DetailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            cap = savedInstanceState.getStringArray("cap");
            image = savedInstanceState.getIntArray("image");
        }
        RecyclerView pizzaRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_detail, container, false);
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(cap, image);
        pizzaRecycler.setAdapter(adapter);
        //установка вертикальной ориентации для RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        pizzaRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_CATEGORY, category);
                intent.putExtra(DetailActivity.EXTRA_POZITION, position);
                getActivity().startActivity(intent);
            }
        });

        return pizzaRecycler;
    }

    public void setWorkout(String cap[], int image[], int category) {
        this.cap = cap;
        this.image = image;
        this.category = category;
        Log.d(LOG_TAG,"setId");
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putStringArray("cap",cap);
        savedInstanceState.putIntArray("image",image);
    }

}
