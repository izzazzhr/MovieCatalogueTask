package com.example.moviecataloguetask.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.moviecataloguetask.R;
import com.example.moviecataloguetask.fragments.NowPlayingFragment;
import com.example.moviecataloguetask.fragments.TVShowFragment;
import com.example.moviecataloguetask.fragments.UpcomingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bnv_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.item_now_playing);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.item_now_playing:
                getSupportActionBar().setTitle(getResources().getString(R.string.menu_now_playing));
                fragment = new NowPlayingFragment();
                break;
            case R.id.item_tv_show:
                getSupportActionBar().setTitle(getResources().getString(R.string.menu_tv_show));
                fragment = new TVShowFragment();
                break;
            case R.id.item_upcoming:
                getSupportActionBar().setTitle(getResources().getString(R.string.menu_upcoming));
                fragment = new UpcomingFragment();
                break;

        }if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
            return true;
        }

        return false;
    }
}