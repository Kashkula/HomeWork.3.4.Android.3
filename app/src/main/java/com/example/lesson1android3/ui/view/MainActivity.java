package com.example.lesson1android3.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.interfaces.OpenActivityListener;
import com.example.lesson1android3.data.local.PreferenceUtils;
import com.example.lesson1android3.ui.adapter.FragmentAdapter;
import com.example.lesson1android3.ui.fragment.favorite.FavoriteFragment;
import com.example.lesson1android3.ui.fragment.post.PostFragment;
import com.example.lesson1android3.data.interfaces.SetCurrentItemListener;
import com.example.lesson1android3.ui.fragment.user.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OpenActivityListener, SetCurrentItemListener {

    protected BottomNavigationView bottomNavigationView;
    protected ViewPager viewPager;
    protected List<Fragment> fragmentList;
    protected OpenActivityListener listener;
    protected SetCurrentItemListener setCurrentItemListener;

    /**
     * Добрый день, Мой Верный друг в ютубе остановились на 23:00, сюжет - " после создания фрагментов
     * до скорой встречи, милый!!
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onlyCode();
        if (PreferenceUtils.getLoginLine().isEmpty() && PreferenceUtils.getPasswordLine().isEmpty())
            viewPager.setCurrentItem(1);

    }

    private void onlyCode() {
        init();
        fillFragment();
        viewPagerSetAdapter();
        bottomNavSetOnNavigation();

    }

    private void bottomNavSetOnNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_post)
                viewPager.setCurrentItem(0);
            else if (item.getItemId() == R.id.action_user)
                viewPager.setCurrentItem(1);
            else
                viewPager.setCurrentItem(2);
            return true;
        });
    }



    private void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        viewPager = findViewById(R.id.viewPager);
        fragmentList = new ArrayList<>();
        listener = this;
        setCurrentItemListener = this;
        PreferenceUtils.init(this);
    }

    private void viewPagerSetAdapter() {
        viewPager.setAdapter(new FragmentAdapter(fragmentList, getSupportFragmentManager()));
        viewPagerSetOnPage();
    }

    private void viewPagerSetOnPage() {
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    private void fillFragment() {
        fragmentList.add(new PostFragment());
        fragmentList.add(new UserFragment());
        fragmentList.add(new FavoriteFragment());
    }

    @Override
    public void openSendActivity() {
        startActivity(new Intent(this, SendActivity.class));
    }

    @Override
    public void openChangeActivity(Integer id) {
        Intent intent = new Intent(this, ChangeActivity.class);
        intent.putExtra("po", id);
        startActivity(intent);
    }

    @Override
    public void openPostFragment() {
        viewPager.setCurrentItem(0);
    }
}
