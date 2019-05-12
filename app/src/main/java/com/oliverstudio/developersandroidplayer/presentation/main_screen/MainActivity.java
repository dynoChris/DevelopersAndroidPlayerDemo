package com.oliverstudio.developersandroidplayer.presentation.main_screen;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.presentation.main_screen.history_fragment.view.HistoryFragment;
import com.oliverstudio.developersandroidplayer.presentation.main_screen.home_fragment.view.HomeFragment;
import com.oliverstudio.developersandroidplayer.presentation.main_screen.videos_fragment.view.VideosFragment;

public class MainActivity extends AppCompatActivity {

    //views
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;

    //general vars
    private HomeFragment mHomeFragment;
    private VideosFragment mVideosFragment;
    private HistoryFragment mHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setSelectedItemId(R.id.nav_videos);
        mBottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new HomeFragment(), HomeFragment.FRAGMENT_TAG)
                    .add(R.id.fragment_container, new VideosFragment(), VideosFragment.FRAGMENT_TAG)
                    .add(R.id.fragment_container, new HistoryFragment(), HistoryFragment.FRAGMENT_TAG)
                    .commitNow();

            initFragments();
            showFragment(mVideosFragment);
        } else {
            initFragments();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    showFragment(mHomeFragment);
                    break;
                case R.id.nav_videos:
                    showFragment(mVideosFragment);
                    break;
                case R.id.nav_history:
                    showFragment(mHistoryFragment);
                    break;
            }
            return true;
        }
    };

    private void initFragments() {
        mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.FRAGMENT_TAG);
        mVideosFragment = (VideosFragment) getSupportFragmentManager().findFragmentByTag(VideosFragment.FRAGMENT_TAG);
        mHistoryFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(HistoryFragment.FRAGMENT_TAG);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .hide(mHomeFragment)
                .hide(mVideosFragment)
                .hide(mHistoryFragment)
                .show(fragment)
                .commit();
    }

}