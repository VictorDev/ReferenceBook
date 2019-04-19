package com.hfad.deutsch;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainFragment extends Fragment {

    final String LOG_TAG = "mylog";
     interface MainListener{
         void itemClick(long id);
    }

    private MainListener listener;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(LOG_TAG, "CreateMainFragment");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(listener!=null){
                    listener.itemClick(l);
                }
            }
        };
        View view = getView();
        if(view!=null) {
            ListView listView = (ListView) view.findViewById(R.id.list_view);
            listView.setOnItemClickListener(itemClickListener);
            Log.d(LOG_TAG,"set listener");
        }
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (MainListener)activity;
        Log.d(LOG_TAG,"getListener");
    }


}
