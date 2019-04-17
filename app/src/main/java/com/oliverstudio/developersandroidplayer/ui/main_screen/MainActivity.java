package com.oliverstudio.developersandroidplayer.ui.main_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.view.HistoryFragment;
import com.oliverstudio.developersandroidplayer.ui.main_screen.home_fragment.view.HomeFragment;
import com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.view.VideosFragment;

public class MainActivity extends AppCompatActivity {

    //view
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //init views
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setSelectedItemId(R.id.nav_videos);
        mBottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new VideosFragment(), VideosFragment.FRAGMENT_TAG)
                    .commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    String fragmentTag = "";
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            fragmentTag = HomeFragment.FRAGMENT_TAG;
                            selectedFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
                            if (selectedFragment == null) selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_videos:
                            fragmentTag = VideosFragment.FRAGMENT_TAG;
                            selectedFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
                            if (selectedFragment == null) selectedFragment = new VideosFragment();
                            break;
                        case R.id.nav_history:
                            fragmentTag = HistoryFragment.FRAGMENT_TAG;
                            selectedFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
                            if (selectedFragment == null) selectedFragment = new HistoryFragment();
                            break;
                    }

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment, fragmentTag)
                            .commit();

                    return true;
                }
            };

}