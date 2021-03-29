package com.dmitrysukhov.testapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity implements FragmentCallback {

    public static final String FRAGMENT_TAG = "fragment";
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager_main);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        viewPagerAdapter.addFragment(new MyFragment(getIntent().getIntExtra(FRAGMENT_TAG, 1)));
    }

    @Override
    public void createNewFragment() {
        viewPagerAdapter.addFragment(new MyFragment(++ViewPagerAdapter.countOfFragments));
        viewPager.setCurrentItem(ViewPagerAdapter.countOfFragments, true);
    }

    @Override
    public void deleteFragment(int id) {
        if (ViewPagerAdapter.countOfFragments > 0) {
            viewPager.setCurrentItem(0); //бывает оно опять показывает тот что удаляем, это какой-то глюк UI(?)
            viewPagerAdapter.removeFragment(id);
        }
    }
}