package com.example.esppad.fansy4.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.esppad.fansy4.R;
import com.example.esppad.fansy4.fragments.BackupFragement;
import com.example.esppad.fansy4.fragments.CategoriesFragment;
import com.example.esppad.fansy4.fragments.HomeFragment;
import com.example.esppad.fansy4.fragments.OrdersFragment;
import com.example.esppad.fansy4.fragments.ProfileFragement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

final Fragment homeFragment =new HomeFragment();
final Fragment categoriesFragment = new CategoriesFragment();
final Fragment ordersFragment = new OrdersFragment();
final Fragment profileFragment = new ProfileFragement();
final Fragment backupFragment = new BackupFragement();
final FragmentManager fm = getSupportFragmentManager();
Fragment activeFragment = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bottom_home);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fm.beginTransaction().add(R.id.main_container,backupFragment,"5").hide(backupFragment).commit();
        fm.beginTransaction().add(R.id.main_container,profileFragment,"4").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.main_container,ordersFragment,"3").hide(ordersFragment).commit();
        fm.beginTransaction().add(R.id.main_container,categoriesFragment,"2").hide(categoriesFragment).commit();
        fm.beginTransaction().add(R.id.main_container,homeFragment,"1").commit();


    }

private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        =new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
switch (item.getItemId()){
    case R.id.navigation_home:
        fm.beginTransaction().hide(activeFragment).show(homeFragment).commit();
        activeFragment = homeFragment;
        return true;
    case R.id.navigation_categories:
        fm.beginTransaction().hide(activeFragment).show(categoriesFragment).commit();
        activeFragment = categoriesFragment;
        return true;
    case R.id.navigation_orders:
        fm.beginTransaction().hide(activeFragment).show(ordersFragment).commit();
        activeFragment = ordersFragment;
        return true;
    case R.id.navigation_profile:
        fm.beginTransaction().hide(activeFragment).show(profileFragment).commit();
        activeFragment = profileFragment;
        return true;
    case R.id.navigation_backup:
        fm.beginTransaction().hide(activeFragment).show(backupFragment).commit();
        activeFragment = backupFragment;
        return true;
}
        return false;
    }
};




}
