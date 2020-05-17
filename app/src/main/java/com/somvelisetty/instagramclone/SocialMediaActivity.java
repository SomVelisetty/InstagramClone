package com.somvelisetty.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class SocialMediaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private TabAdaptor tabAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        setTitle("Social Media App!!!");

        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        viewpager = findViewById(R.id.viewPager);
        tabAdaptor = new TabAdaptor(getSupportFragmentManager());
        viewpager.setAdapter(tabAdaptor);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewpager, false);


    }
}
