package com.hfad.deutsch;

import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.AdapterView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.res.Configuration;

public class MainActivity extends Activity {

    public final String LOG_TAG = "mylog";
    private ListView drawerList;
    private String titles[];
    public String[] capLenin = {"Начало революционной деятельности"," Участие в революции","Покушение Каплан"};
    public int[] imageLenin = {R.drawable.nach_deyat_lenin,R.drawable.uchast_v_rev,R.drawable.pokushenie};
    public String[] capStalin = {"Знакомство с Лениным","Борьба за власть","Смерть"};
    public int[] imageStalin = {R.drawable.znak_s_leninim, R.drawable.borba_za_vlast, R.drawable.smert_stalin};
    public String[] capTrozkij = {"Начало революционной деятельности","Организация революции","Убийство"};
    public int[] imageTrozkij = {R.drawable.nachalo, R.drawable.organiz_rev, R.drawable.ubijstvo};
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;//слушатель для кнопки HOME

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG,"CreateActivity");
        titles = getResources().getStringArray(R.array.list);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            //Вызывается при переходе выдвижной панели в полностью закрытое состояние.
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            //Вызывается при переходе выдвижной панели в полностью открытое состояние.
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        return true;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            //выполняется при щелчке по выдвижному списку
            //загрузка фрагмента
            itemClick(position);
            //изменение заголовка
            setActionBarTitle(position);
        }
    }

    public void itemClick(int id) {
        DetailFragment df = new DetailFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (id){
            case 0:
                df.setWorkout( capLenin, imageLenin, 0);
                break;
            case 1:
                df.setWorkout(capStalin,imageStalin, 1);
                break;
            case 2:
                df.setWorkout(capTrozkij, imageTrozkij, 2);
        }
        ft.replace(R.id.content_frame, df);
        ft.addToBackStack(null);
        ft.commit();
        Log.d(LOG_TAG,"ReplaceFragment");
        drawerLayout.closeDrawer(drawerList);
    }

    private void setActionBarTitle(int position){
        String title;
        if(position==0){
            title = getResources().getString(R.string.app_name);
        }else{
            title = titles[position];
        }
        if(getActionBar()!=null) {
            getActionBar().setTitle(title);
        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

}
