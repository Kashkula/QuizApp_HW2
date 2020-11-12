package com.aziz.ui.fragment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.aziz.R;
import com.aziz.data.pagerAdapter.FragmentAdapter;
import com.aziz.ui.fragment.activity.question.QuestionActivity;
import com.aziz.ui.fragment.history.HistoryFragment;
import com.aziz.ui.fragment.main.MainFragment;
import com.aziz.ui.fragment.main.OnClickListenerMF;
import com.aziz.ui.fragment.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListenerMF {

    private BottomNavigationView bottomNavigationView;
    protected ViewPager viewPager;
    protected List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        init();
        fillFragment();
        bottomNavView();
        setPageAdapter();
    }

    private void setPageAdapter() {
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
    }

    private void fillFragment() {
        if (list != null) {
            list.add(new MainFragment());
            list.add(new HistoryFragment());
            list.add(new SettingFragment());
        }
    }

    private void bottomNavView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_main:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_history:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_settings:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

    private void init() {
        list = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.viewPager);
    }

    private void openMainF(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void openActivity() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, QuestionActivity.class));
    }
}