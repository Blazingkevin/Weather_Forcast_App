 package com.nivek.kevoweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
       return WeatherListFragment.newInsance() ;
    }
}
