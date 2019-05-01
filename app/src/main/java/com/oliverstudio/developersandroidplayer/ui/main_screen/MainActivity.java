package com.oliverstudio.developersandroidplayer.ui.main_screen;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.view.HistoryFragment;
import com.oliverstudio.developersandroidplayer.ui.main_screen.home_fragment.view.HomeFragment;
import com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.view.VideosFragment;

public class MainActivity extends AppCompatActivity {

    //view
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

            fetchFragmentsFromFragmentManager();

            getSupportFragmentManager().beginTransaction()
                    .hide(mHomeFragment)
                    .hide(mVideosFragment)
                    .hide(mHistoryFragment)
                    .show(mVideosFragment)
                    .commit();
        } else {
            fetchFragmentsFromFragmentManager();
        }
    }

    private void fetchFragmentsFromFragmentManager() {
        mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.FRAGMENT_TAG);
        mVideosFragment = (VideosFragment) getSupportFragmentManager().findFragmentByTag(VideosFragment.FRAGMENT_TAG);
        mHistoryFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(HistoryFragment.FRAGMENT_TAG);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction()
                            .show(mHomeFragment)
                            .hide(mVideosFragment)
                            .hide(mHistoryFragment)
                            .commit();
                    break;
                case R.id.nav_videos:
                    getSupportFragmentManager().beginTransaction()
                            .hide(mHomeFragment)
                            .show(mVideosFragment)
                            .hide(mHistoryFragment)
                            .commit();
                    break;
                case R.id.nav_history:
                    getSupportFragmentManager().beginTransaction()
                            .hide(mHomeFragment)
                            .hide(mVideosFragment)
                            .show(mHistoryFragment)
                            .commit();
                    break;
            }
            return true;
        }
    };

}