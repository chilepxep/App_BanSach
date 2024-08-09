package com.example.appbansach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.appbansach.fragment.AccountFragment;
import com.example.appbansach.fragment.CartFragment;
import com.example.appbansach.fragment.HomeFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mnBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mnBottom = findViewById(R.id.navMenu);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Main");
        //actionBar.setDisplayHomeAsUpEnabled(true);
        mnBottom.setOnItemSelectedListener(getListener());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return true;
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fmNew;
                switch (item.getItemId()){
                    case R.id.mnHome:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new HomeFragment();
                        loadFragment(fmNew);
                        return  true;
                    case R.id.mnCart:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new CartFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnAccount:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new AccountFragment();
                        loadFragment(fmNew);
                        return true;
                }
                return true;
            }
        };
    }


    void loadFragment(Fragment fmNew){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
}
