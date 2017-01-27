package com.massive.readbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.massive.readbook.Fragments.BaseFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().replace(R.id.container,new BaseFragment()).commit();
    }
}
