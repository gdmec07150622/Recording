package com.me.android.recording;

;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by admin on 2017/3/5.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

 protected abstract Fragment createFragment();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fragment);
            FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.Fragment fragment=fm.findFragmentById(R.id.fragment_container);
            if(fragment==null){
                fragment=createFragment();
                fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
            }
        }
    }


